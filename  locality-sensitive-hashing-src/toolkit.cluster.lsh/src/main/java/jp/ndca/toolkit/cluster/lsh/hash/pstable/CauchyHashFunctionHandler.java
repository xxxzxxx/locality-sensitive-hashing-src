package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.ArrayList;
import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;
import jp.ndca.toolkit.cluster.lsh.math.random.CauchyRandomNumber;

/**
 * ND_HashFunction を使ってハッシュ関数を生成したり、LSHの変換パラメータL,Kを取得するHandlerクラスです。
 * 
 * @author hattori_tsukasa
 *
 */
public class CauchyHashFunctionHandler implements PstableHandler{

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
	
	private static CauchyRandomNumber crn = new CauchyRandomNumber();
	
	
	/**
	 * @param p1 : GoodHash　probability
	 * @param p2 : BadHash　probability
	 * @param dimension : データの次元
	 * @param n : 対象データの総数(例:検索の場合なら全検索対象データ)
	 * @param r : ハッシュ関数 (a*x+b)/r で用いられるr
	 */
	public CauchyHashFunctionHandler( double p1 , double p2 , int n , int dimension , double r ){
		
		this.p1  = p1;
		this.p2  = p2;
		this.r   = r;
		this.rho = Math.log(1/p1) /Math.log(1/p2);
		this.dimension = dimension;
		
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
	 * 安定分布(Cauchy分布)のハッシュ関数を取得します。取得できるハッシュ関数は毎回乱数で生成しているため異なるモノが取得できます。
	 */
	@Override
	public HashFunction newInstanceHashFunction(){
		double[] a = makeHashFunction_Vector_a();
		return new CauchyHashFunction( a, makeHashFunction_Scholar_b(), r );
	}
	
	
	/**
	 * hash関数　(a*x+b)/r に用いるコーシー分布ベクトルaを取得します。aは乱数生成で毎回作成しています。
	 * @return a ベクトル(size K)
	 */
	private double[] makeHashFunction_Vector_a(){
		double[] hashList = new double[dimension];
		for( int i=0 ; i < dimension ; i++ ){
			hashList[i] = crn.getCauchyRandomNumber();
		}
		return hashList;
	}
	
	
	/**
	 * hash関数　(a*x+b)/r に用いる、乱数スカラーb [0,r] を取得します。
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
	public List<HashFunctionVectorGenerator> generateHashFunctionVectorGeneratorList( int K, int L ) {
		List<HashFunctionVectorGenerator> resultList = new ArrayList<HashFunctionVectorGenerator>();
		for( int i=0 ; i < L ; i++ ){
			List<HashFunction> hfl = generateHashFunctionList(K);
			resultList.add( new HashFunctionVectorGenerator(hfl) );
		}
		return resultList;
	}
	
}
