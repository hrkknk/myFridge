package recipe;

import java.util.ArrayList;

/**
 * 料理情報クラス
 * ※リファクタリング検討(レシピクラスにまとめられるかも)
 */
public class CookInfo {
	/** 足りない食材リスト */
	public ArrayList<String> additionalFoods;

	public CookInfo() {
		additionalFoods = new ArrayList<String>();
	}
}
