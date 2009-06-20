package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionGenerator;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

public interface SimHashHandler extends HashFunctionGenerator{
	
	/**
	 * Random Projectionで使われたHashFunctionが詰まったリストを取得します。return されるリストは毎回内部でランダムに生成されています。。
	 * @return　HashFunctionリスト
	 */
	public  HashFunctionVectorGenerator generateHashFunctionVectorGenerator(int reducedDimension);
	
}
