package chef;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import food.Fish;
import food.Meat;
import food.Vegetable;
import fridge.Fridge;
import recipe.Recipe;
import server.ServerManager;

/**
 * メインクラス
 */
public class Main {

	public static void main(String[] args) throws SQLException {
		
		ServerManager serverManager = ServerManager.getIncetance();
		Connection sqlConnection;
		try {
			sqlConnection = serverManager.getSqlConnection();
		}
		catch(SQLException e) {
			System.out.println("SQLサーバに接続できませんでした");
			return;
		}
		System.out.println("SQLサーバに接続しました");
		
        //レシピテーブルから全レシピを取得
        Statement statement = sqlConnection.createStatement();
        String sql = "select * from recipe";
        ResultSet rs = statement.executeQuery(sql);
        
		//レシピリストの作成
        ArrayList<Recipe> recipes;
        try {
            recipes = makeRecipes(rs);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        //シェフに冷蔵庫とレシピを渡す
		Fridge fridge = new Fridge();
		Chef chef = new Chef(fridge, recipes);
		chef.scanFridge();
	}  

	/**
	 * データベースからレシピリストを取得する
	 * @param resultSet SQLのResultSet
	 * @return レシピリスト
	 * @throws SQLException
	 * TODO Recipe
	 */
	private static ArrayList<Recipe> makeRecipes(ResultSet resultSet) throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        while(resultSet.next()) {
        		Recipe recipe = new Recipe(resultSet.getString("name"));
        		
        		int cnt = 1;
        		String foodStuff = resultSet.getString("stuff" + cnt);
        		String stuffKind = foodStuff.split("_")[0];
        		String stuffName = foodStuff.split("_")[1];
        		while(foodStuff != null) {
        			stuffKind = foodStuff.split("_")[0];
        			stuffName = foodStuff.split("_")[1];
        			//TODO リファクタリング:switch文
        			if(stuffKind.equals("肉")) {
        				recipe.addIngredients(new Meat(stuffName));
        			}
        			if(stuffKind.equals("魚")) {
        				recipe.addIngredients(new Fish(stuffName));
        			}
        			if(stuffKind.equals("野菜")) {
        				recipe.addIngredients(new Vegetable(stuffName));
        			}
        			cnt++;
        			foodStuff = resultSet.getString("stuff" + cnt);
        		}
   			recipes.add(resultSet.getRow() - 1, recipe);
        }
		return recipes;
	}
}
