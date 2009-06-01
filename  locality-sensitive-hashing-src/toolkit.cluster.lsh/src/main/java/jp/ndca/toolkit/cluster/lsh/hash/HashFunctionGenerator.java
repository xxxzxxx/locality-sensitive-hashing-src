package jp.ndca.toolkit.cluster.lsh.hash;

public interface HashFunctionGenerator {
	
	/**
	 * ハッシュ関数を取得します。
	 * 取得できるハッシュ関数は毎回乱数で生成しているため異なるモノを取得します。
	 */
	public HashFunction newInstanceHashFunction();

}
