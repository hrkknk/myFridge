package food;

/*
 * 食材クラス
 * ※要リファクタリング(抽象クラスにすべき)
 */
public abstract class Food {
	
	/** 食材名 */
	private String name;
	/** 冷蔵庫に貯蔵された日数 */
	private int stockDays; //今のところ使ってない
	/**
	 * foundフラグ
	 * レシピの必要食材が冷蔵庫内にあった場合に立てる
	 * ※リファクタリング検討(このクラスに持たせるべきではないかも)
	 */
	private boolean found;

	public Food(String name) {
		this.stockDays = 0;
		this.name = name;
		found = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getStockDays() {
		return this.stockDays;
	}
	
	public boolean isFound() {
		return found;
	}
	public void setFound() {
		found = true;
	}
}
