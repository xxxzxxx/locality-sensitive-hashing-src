package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.ArrayList;
import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;
import jp.ndca.toolkit.cluster.lsh.math.random.NormalRandomNumber;

/**
 * ND_HashFunction を使ってハッシュ関数を生成したり、LSHの変換パラメータL,Kを取得するHandlerクラスです。
 * @author hattori_tsukasa
 *
 */
public class NormalHashFunctionHandler implements PstableHandler{
	
	//GoodHash probability
	double p1;
	
	//BadHash probability
	double p2;
	
	double r;

	double rho;
	
	//一データに当たりに生成するハッシュベクトルの個数
	int L;
	
	//ハッシュ関数ベクトルの次元数 (次元削減写像後の次元の数)
	int K;

	//検索対象となるデータの個数 (=n)
	int dimension;
	
	private static NormalRandomNumber nrn = new NormalRandomNumber();

	
	/**
	 * @param p1 GoodHash　probability
	 * @param p2 BadHash　probability
	 * @param n 対象データの総数(例:検索の場合なら全検索対象データ)
	 * @param r　ハッシュ関数 (a*x+b)/r で用いられるr
	 */
	public NormalHashFunctionHandler( double p1, double p2, int n, int dimension, double r ){
		
		this.p1  = p1;
		this.p2  = p2;
		this.r   = r;
		this.dimension = dimension;
		this.rho = Math.log(1/p1) / Math.log(1/p2);
		
		//切り上げ
		this.L = (int)Math.ceil( Math.pow(n, rho) );
		this.K = (int)( Math.log(n) / Math.log(1/p2) );
		
	}
	
	
	/**
	 * ハッシュ関数ベクトルの次元を取得します。
	 */
	@Override
	public int getK(){
		return K;
	}
	
	
	/**
	 * 一データ当たりに作成するハッシュベクトルの数を取得します。
	 */
	@Override
	public int getL(){
		return L;
	}
	
	
	/**
	 * 計算時間やL(=n^ρ)を決定するρを取得します。
	 */
	@Override
	public double getRho(){
		return rho;
	}
	
	
	/**
	 * 安定分布(正規分布)のハッシュ関数を取得します。取得できるハッシュ関数は毎回乱数で生成しているため異なるモノが取得できます。
	 */
	@Override
	public HashFunction newInstanceHashFunction(){
		double[] a = makeHashFunction_Vector_a();
//		System.out.println( "a=" + a.length );
		return new NormalHashFunction( a, makeHashFunction_Scholar_b(), r );
	}
	
	
	/**
	 * hash関数　(a*x+b)/r に用いる正規乱数ベクトルaを取得します。aは乱数生成で毎回作成しています。
	 * @return a ベクトル(size data_dimension)
	 */
	private double[] makeHashFunction_Vector_a(){
		double[] hashList = new double[dimension];
		for( int i=0 ; i < dimension ; i++ ){
			hashList[i] = nrn.getNormalRandomNumber();
		}
		return hashList;
	}
	
	
	/**
	 * hash関数　( a*x + b )/r に用いる、乱数スカラーb ∈ [0,r] を取得します。
	 * @return b スカラー
	 */
	private double makeHashFunction_Scholar_b(){
		return r * Math.random();
	}


	@Override
	public List<HashFunction> generateHashFunctionList(int K) {
		List<HashFunction> hfl = new ArrayList<HashFunction>();
		for( int i=0 ; i < K ; i++){
			HashFunction hf = newInstanceHashFunction();
			hfl.add(hf);
		}		
		return hfl;
	}

	
	@Override
	public List<HashFunctionVectorGenerator> generateHashFunctionVectorGeneratorList(
			int K, int L) {
		List<HashFunctionVectorGenerator> resultList = new ArrayList<HashFunctionVectorGenerator>();
		for( int i = 0  ; i < L ; i++ ){
			List<HashFunction> list = generateHashFunctionList(K);
			HashFunctionVectorGenerator hfvg = new HashFunctionVectorGenerator(list);
			resultList.add(hfvg);
		}
		return resultList;
	}

}
