package chef;

import java.util.ArrayList;

import food.Food;
import fridge.Fridge;
import recipe.CookInfo;
import recipe.Recipe;

/**
 * 料理人クラス
 */
public class Chef {
	
	/** 冷蔵庫 */
	private Fridge fridge = null;
	/** レシピリスト */
	private ArrayList<Recipe> recipes = null;

	public Chef(Fridge fridge, ArrayList<Recipe> recipes) {
		this.fridge = fridge;
		this.recipes = recipes;
	}
	
	/**
	 * 冷蔵庫の中身を調べる
	 */
	public void scanFridge() {
		ArrayList<Food> stockFoods = fridge.getStockList();
		
		System.out.println("冷蔵庫に");
		for (Food food: stockFoods) {
			System.out.println("[" + food.getName() + "]");
		}
		System.out.println("が入っています");
		System.out.println("");
				
		for(int i = 0; i < this.recipes.size(); i++) {
			Recipe recipe = this.recipes.get(i);
			showCookingInfo(recipe);
		}
	}
	
	/**
	 * 作れるメニュー、足りない食材を示す
	 * @param recipe レシピ
	 */
	private void showCookingInfo(Recipe recipe) {
		CookInfo cookInfo = compareFridgeAndRecipe(recipe);
		//足りない食材があれば全て表示
		if(cookInfo.additionalFoods.size() > 0) {
			System.out.println(recipe.getName() + "を作るには");
			for(String additionlFood: cookInfo.additionalFoods) {
				System.out.println("[" + additionlFood + "]");
			}
			System.out.println("が必要です");
		} else {
			System.out.println("[" + recipe.getName() + "]" + "を作るための食材が揃っています");
		}
	}
	
	/**
	 * 冷蔵庫の中身とレシピを比較し、足りない食材を探す
	 * @param recipe レシピ
	 * @return 料理情報(足りない食材)
	 */
	private CookInfo compareFridgeAndRecipe(Recipe recipe) {
		CookInfo cookInfo = new CookInfo();
		//冷蔵庫の中身がレシピに載っているかサーチし、見つけたら"found"を設定する
		for(int i = 0; i < this.fridge.getStockList().size(); i++) {
			for(Food ingredient: recipe.getIngredients()) {
				if(this.fridge.getStockList().get(i).getName().equals(ingredient.getName())) {
					ingredient.setFound();
					break;
				}
			}
		}
		
		//"found"にならなかった食材を、足りない食材として料理情報に記録
		for(int k = 0; k < recipe.getIngredients().size(); k++) {
			if(!(recipe.getIngredients().get(k).isFound())) {
				cookInfo.additionalFoods.add(recipe.getIngredients().get(k).getName());
			}
		}
		return cookInfo;
	}

}
