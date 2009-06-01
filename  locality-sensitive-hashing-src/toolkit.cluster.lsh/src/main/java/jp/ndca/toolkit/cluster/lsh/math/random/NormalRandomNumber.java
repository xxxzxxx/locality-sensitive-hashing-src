package jp.ndca.toolkit.cluster.lsh.math.random;

/**
 * Box-Muller transformによる正規乱数を発生させるクラスです。
 * @author hattori_tsukasa
 *
 */
public class NormalRandomNumber {
	
	private boolean isOddNum = true;
	
	private double nrn1;
	
	private double nrn2;

	/**
	 * Box-Muller transformによる正規乱数を取得します。
	 * @return　正規乱数
	 */
	public double getNormalRandomNumber(){
		if(isOddNum){
			double alpha = Math.random();
			double beta  = Math.random();
	
			nrn1 = Math.sqrt( -2.0d*Math.log(alpha) ) * Math.sin( 2.0d * Math.PI * beta );
			nrn2 = Math.sqrt( -2.0d*Math.log(alpha) ) * Math.cos( 2.0d * Math.PI * beta );
			isOddNum = false;
			return nrn1;
		}
		else{
			isOddNum = true;
			return nrn2;
		}
	}
	
	/**
	 * Box-Muller transformによる正規乱数を取得
	 * @return
	 */
	public double[] getNormalRandomNumberList(){
		
		double alpha = Math.random();
		double beta  = Math.random();

		double nrn1 = Math.sqrt( -2.0d*Math.log(alpha) ) * Math.sin( 2.0d * Math.PI * beta );
		double nrn2 = Math.sqrt( -2.0d*Math.log(alpha) ) * Math.cos( 2.0d * Math.PI * beta );
		
		double[] dd = new double[2];
		dd[0] = nrn1;
		dd[1] = nrn2;
		return dd;
	}
	
}
