package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionGenerator;

/**
 * Random Projection (LSH hash family)　"hr(u) = if(u*r>=) 1 else 0"　によって、
 * 元データをハミングベクトルにデータを置き換えるクラスです。
 * この変換されたハミングベクトル同士の距離は、元データでのコサイン距離を反映します。
 * 
 * @author hattori_tsukasa
 *
 */
public interface SimHashHandler extends HashFunctionGenerator{
	
	/**
	 * Random Projectionで使われたHashFunctionが詰まったリストを取得します。
	 * @return　HashFunctionリスト
	 */
	public List<HashFunction> getHashFunctionList();
	
	/**
	 * Random Projectionで使うHashFunctionのリストを内部で生成します。
	 * @param reDimension
	 */
	public void makeHashFunctionList(int reDimension);
	
}
