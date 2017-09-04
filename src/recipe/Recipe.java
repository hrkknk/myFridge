package recipe;

import java.util.ArrayList;

import food.Food;

/*
 * レシピクラス
 */
public class Recipe {
	
	/** レシピ名 */
	private String name;
	/** 食材リスト */
	private ArrayList<Food> ingredients;

	/**
	 * 可変長の引数を取れるコンストラクタ
	 * @param name 料理の名前(例：カレー)
	 * @param foods 必要な食材(可変長配列)
	 */
	public Recipe(String name, Food...foods) {
		this.name = name;
		this.ingredients = new ArrayList<Food>();
		for(Food food: foods) {
			this.ingredients.add(food);
		}
	}
	
	public void addIngredients(Food food) {
		this.ingredients.add(food);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Food> getIngredients() {
		return this.ingredients;
	}
}
