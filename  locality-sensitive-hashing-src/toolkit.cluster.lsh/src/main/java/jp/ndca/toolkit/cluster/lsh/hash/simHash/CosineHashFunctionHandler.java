package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import java.util.ArrayList;
import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;
import jp.ndca.toolkit.cluster.lsh.math.random.NormalRandomNumber;

/**
 * Random Projection (LSH hash family)　"hr(u) = if(u*r>=) 1 else 0"　によって、
 * 元データをハミングベクトルにデータを置き換えるクラスです。
 * この変換されたハミングベクトル同士の距離は、元データでのコサイン距離を反映します。
 * 
 * @author hattori_tsukasa
 *
 */
public class CosineHashFunctionHandler implements SimHashHandler{
	
	protected NormalRandomNumber nrn = new NormalRandomNumber();

	protected int dimension;
	
	
	/**
	 * @param dimension ：データの元次元
	 */
	public CosineHashFunctionHandler( int dimension ){
		this.dimension = dimension;
	}

	
	@Override
	public HashFunction newInstanceHashFunction() {
		double[] r = makeHashFunction_Vector_r();
		return new CosineHashFunction( r );
	}

	
	/**
	 * hash関数　 if(r*u>=0) 1 else 0 に用いる正規分布乱数ベクトルrを取得します。rは乱数生成で毎回作成しています。
	 * @return r ベクトル(size data_Dimension)
	 */
	private double[] makeHashFunction_Vector_r(){
		double[] hashList = new double[dimension];
		for( int i=0 ; i < dimension ; i++ ){
			hashList[i] = nrn.getNormalRandomNumber();
		}
		return hashList;
	}

	
	@Override
	public HashFunctionVectorGenerator generateHashFunctionVectorGenerator( int reducedDimension ) {
		List<HashFunction> hfl = new ArrayList<HashFunction>();
		for( int i=0 ; i < reducedDimension ; i++){
			HashFunction hf = newInstanceHashFunction();
			hfl.add( hf );
		}		
		return new HashFunctionVectorGenerator(hfl);
	}

}
