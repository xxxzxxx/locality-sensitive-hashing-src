package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import java.util.ArrayList;
import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.math.random.NormalRandomNumber;

/**
 * Random Projection (LSH hash family)　"hr(u) = if(u*r>=) 1 else 0"　によって、
 * 元データをハミングベクトルにデータを置き換えるクラスです。
 * この変換されたハミングベクトル同士の距離は、元データでのコサイン距離を反映します。
 * 
 * @author hattori_tsukasa
 *
 */
public class Cosine_HashFunctionHandler implements SimHashHandler{
	
	protected List<HashFunction> hfl = new ArrayList<HashFunction>();
	
	protected NormalRandomNumber nrn = new NormalRandomNumber();

	protected int dimension;
	
	/**
	 * @param dimension ：データの次元
	 */
	public Cosine_HashFunctionHandler( int dimension ){
		this.dimension = dimension;
	}

	@Override
	public HashFunction newInstanceHashFunction() {
		double[] r = makeHashFunction_Vector_r();
		return new Cosine_HashFunction( r );
	}

	/**
	 * Random Projectionで使われたHashFunctionが詰まったリストを取得します。
	 * @return　HashFunctionリスト
	 */
	public List<HashFunction> getHashFunctionList(){
		return hfl;
	}
	
	@Override
	public void makeHashFunctionList( int reDimension ) {
		for( int i=0 ; i < reDimension ; i++){
			HashFunction hf = newInstanceHashFunction();
			hfl.add( hf );
		}		
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

}
