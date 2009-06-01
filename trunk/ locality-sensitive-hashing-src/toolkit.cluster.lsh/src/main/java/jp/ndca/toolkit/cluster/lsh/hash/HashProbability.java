package jp.ndca.toolkit.cluster.lsh.hash;

/**
 * データ空間上でクエリ点から半径r以内の要素のハッシュ値が衝突する確率p1と、
 * 半径cr上の要素が衝突する確率p2を取得するインターフェースです。
 * @author hattori_tsukasa
 *
 */
public interface HashProbability {

	/**
	 * Good Hash Probability (=p1)を取得します。
	 * @return p1
	 */
	public double getGoodHashProb();

	/**
	 * Bad Hash Probability (=p2)を取得します。
	 * @return p2
	 */
	public double getBadHashProb();

}
