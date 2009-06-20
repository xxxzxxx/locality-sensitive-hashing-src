package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import jp.ndca.toolkit.cluster.lsh.hash.HashProbability;
import jp.ndca.toolkit.cluster.lsh.math.integral.impl.Gaussian;

/**
 * GoodHash_Probability(p1)とBadHash_Probability(p2)を計算するクラスです。p1=p(1)およびp2=p(c)は
 * 『Locality-Sensitive Hashing Scheme Based on p-stable Distributions (Datar 2004)』
 * を元に作成されています。
 * 
 * @author hattori_tsukasa
 *
 */
public class NormalHashProbability implements HashProbability{
	
	private static double start = -10000.0d;

	private static double coffi = 2.0d / Math.sqrt( 2.0d * Math.PI );

	private static Gaussian gaussian = new Gaussian( 0, 1 );
	
	private double c;
	
	private double r;
		
	
	public NormalHashProbability( double c, double r ){
		this.c = c;
		this.r = r;
	}
	
	
	/**
	 * 安定分布(正規分布)のGoodHashProbability (=p1)を取得します。
	 * @return p1
	 */
	@Override
	public double getGoodHashProb(){
		return calculate_P(r);
	}
	
	
	/**
	 * 安定分布(正規分布)のBadHashProbability (=p2)を取得します。
	 * @return p2
	 */
	@Override
	public double getBadHashProb(){
		return calculate_P(r/c);
	}

	
	/**
	 * 引数を元にした安定分布(正規分布)のGoodHashProbability(=p1)を取得します。
	 * @return p1
	 */
	public double getGoodHashProb( double _r ){
		double x = _r;
		return calculate_P(x);
	}
	
	
	/**
	 * 引数を元にした安定分布(正規分布)のBadHashProbability(=p2)を取得します。
	 * @return p1
	 */	
	public double getBadHashProb( double _c, double _r ){
		double x = _r/_c;
		return calculate_P(x);
	}
	
	
	/**
	 * 安定分布(正規分布)の場合のハッシュ値の衝突確率p(c,r)を取得します。
	 * @param x
	 * @return
	 */
	private double calculate_P( double x ){	
		double end = -x;
		gaussian.setInterval( start, end, 0.01 );
		double secondTerm =  2.0d * gaussian.executeIntegral();
		double thirdTerm  =  coffi  * ( 1.0d - Math.exp( -0.5d * x * x) ) / x;
		return 1.0d - secondTerm - thirdTerm;
	}

}
