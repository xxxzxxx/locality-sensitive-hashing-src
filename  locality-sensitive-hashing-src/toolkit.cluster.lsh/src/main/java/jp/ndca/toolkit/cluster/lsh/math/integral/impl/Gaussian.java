package jp.ndca.toolkit.cluster.lsh.math.integral.impl;

import jp.ndca.toolkit.cluster.lsh.math.exception.IntegralIntervalException;
import jp.ndca.toolkit.cluster.lsh.math.integral.Integral;

/**
 * ガウス関数の区間積分を数値計算的に実行するクラスです。
 * 積分法はシンプソン法で実装されています。
 * 
 * @author hattori_tsukasa
 *
 */
public class Gaussian implements Integral{

	private double start;

	private double end;
	
	private double strip;

	//積分区間を2nとした場合のn(=pointNum)
	private double pointNum;
	
	//ガウシアンの係数。
	private static double cofficent;

	//分散
	private double variance;
	
	//平均
	private double average;
	
	public Gaussian(double average , double variance){
		this.average  =  average;
		this.variance = variance;
		cofficent = 1 / Math.sqrt( 2.0 * Math.PI ) * variance; 
	}
	
	@Override
	public void setInterval(double start, double end, double maxStrip) {
		
		if(start >= end) throw new IntegralIntervalException();
		this.start = start;
		this.end   = end;
		double length = end - start;
		
		//積分領域をmaxstrip以下まで二分割し続ける
		for( pointNum = 0 ; maxStrip <= length ; pointNum++ )
			length = length * 0.5;
		
		strip = length;
		pointNum = Math.pow( 2, pointNum - 1 );
		
	}
	
	/**
	 * シンプソン法による積分を実行します。
	 */
	@Override
	public double executeIntegral() {
		double result = 0.0d;
		double coffi = strip / 3.0d;
		result += coffi * ( GaussFanction(start) + GaussFanction(end) );
		
		int i=1;
		for( ; i <= pointNum - 1 ; i++){
			result += coffi * ( 2.0d * GaussFanction( start + 2.0d * i * strip ) 
								+ 4.0d * GaussFanction( start + (2.0d * i -1.0d) * strip )
							  );
		}
		result += coffi * 4.0d * GaussFanction( start + ( 2.0d * i - 1.0d ) * strip );
		
		return result;
	}

	private double GaussFanction( double x ){
		x = x - average;
		return cofficent * Math.exp( (-0.5 * x * x) / variance );
		
	}

}
