package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import jp.ndca.toolkit.cluster.lsh.hash.HashProbability;

public class CauchyHashProbability implements HashProbability{

	private static double coffi = 1 / Math.PI;

	private double c;
	
	private double r;
	
	
	public CauchyHashProbability( double c, double r ){
		this.c = c;
		this.r = r;
	}
	
	
	/**
	 * 安定分布(Cauchy分布)のGoodHashProbability (=p1)を取得します。
	 * @return p1
	 */
	@Override
	public double getGoodHashProb(){
		return calculate_P(r);
	}
	
	
	/**
	 * 安定分布(Cauchy分布)のBadHashProbability (=p2)を取得します。
	 * @return p2
	 */
	@Override
	public double getBadHashProb(){
		return calculate_P(r/c);
	}

	
	/**
	 * 引数を元にした安定分布(Cauchy分布)のGoodHashProbability(=p1)を取得します。
	 * @return p1
	 */
	public double getGoodHashProb( double _r ){
		double x = _r;
		return calculate_P(x);
	}
	
	
	/**
	 * 引数を元にした安定分布(Cauchy分布)のBadHashProbability(=p2)を取得します。
	 * @return p1
	 */	
	public double getBadHashProb( double _c , double _r ){
		double x = _r / _c;
		return calculate_P(x);
	}
	
	
	/**
	 * 安定分布(Cauchy分布)の場合のハッシュ値の衝突確率p(c,r)を取得します。
	 * @param x
	 * @return
	 */
	private double calculate_P( double x ){	
		double firstTerm   =  2.0d * coffi * Math.atan(x);
		double secondTerm  =  coffi * Math.log(1.0d + (x * x)) / x;
		return firstTerm - secondTerm;
	}

}
