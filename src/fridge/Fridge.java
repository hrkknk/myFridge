package fridge;

import java.util.ArrayList;

import food.Food;
import food.Meat;
import food.Vegetable;

/*
 * 冷蔵庫クラス
 */
public class Fridge {
	
	/** 冷蔵庫の中身 */
	private ArrayList<Food> stockList;
	
	public Fridge() {
		this.stockList = new ArrayList<Food>();
		
		//テスト(本来はユーザが食材を追加する)
		Vegetable v1 = new Vegetable("じゃがいも");
		Vegetable v2 = new Vegetable("にんじん");
		Vegetable v3 = new Vegetable("たまねぎ");
		Meat m1 = new Meat("豚肉");
		this.stockList.add(v1);
		this.stockList.add(v2);
		this.stockList.add(v3);
		this.stockList.add(m1);
	}
	
	public ArrayList<Food> getStockList() {
		return stockList;
	}

	public void addFood(Food food) {
		this.stockList.add(food);
	}
	
}
