package jp.ndca.toolkit.cluster.lsh.math.random;

public class CauchyRandomNumber {
	
	/**
	 * 逆関数法によるコーシー分布乱数を取得
	 * @return　コーシー分布乱数
	 */
	public double getCauchyRandomNumber(){
		
		double y = Math.random();
		double x = Math.tan( Math.PI * y );
		return x;
		
	}

}
