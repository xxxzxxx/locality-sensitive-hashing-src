package jp.ndca.toolkit.cluster.lsh.hash.simHash.data;

public interface RecalulateMethod {

	/**
	 * id_Xとid_Y のCosine類似度を再計算するメソッド。閾値を超えていたら、trueを返してください。
 	 * @param id_X
	 * @param id_Y
	 * @return
	 */
	public abstract boolean caluculate( int id_X, int id_Y , double threshold);

}
