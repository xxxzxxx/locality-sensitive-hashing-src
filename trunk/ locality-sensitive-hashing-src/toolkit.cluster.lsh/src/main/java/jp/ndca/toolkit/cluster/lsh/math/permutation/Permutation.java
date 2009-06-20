package jp.ndca.toolkit.cluster.lsh.math.permutation;

import java.math.BigInteger;

public class Permutation {
	
	/**
	 * combinationを計算する。
	 * @param leftfix
	 * @param rightfix
	 * @return
	 */
	public static double combination(int leftfix,int rightfix ){
		double left   = factorial( BigInteger.valueOf(leftfix) );
		double middle = factorial( BigInteger.valueOf(leftfix - rightfix) );
		double right  = factorial( BigInteger.valueOf(rightfix) );
		return left / ( middle * right );
	}

	/**
	 * マルチスレッドで階乗を計算する。 尚、n!! = n * (n-2) * (n-4)　…　である。
	 * @param    val    階乗を計算する値
	 * @return   val!
	 */
	public static double factorial(BigInteger val) {
	    
	    final BigInteger TWO = new BigInteger("2");
	    // 階乗計算スレッドクラス
	    class FactorialThread extends Thread {
	        BigInteger ret, val;
	        FactorialThread(BigInteger val) {
	            this.val = val;
	        }
	        public void run() {
	            ret = BigInteger.ONE;
	            while (val.compareTo(BigInteger.ONE) > 0) {
	                ret = ret.multiply(val);
	                val = val.subtract(TWO);
	            }
	        }
	    }
	    // (n-1)!!を計算させる。
	    FactorialThread ft = new FactorialThread(val.subtract(BigInteger.ONE));
	    ft.start();
	    
	    // n!!を計算する。
	    BigInteger ret = BigInteger.ONE;
	    while ( val.compareTo(BigInteger.ONE) > 0 ) {
	        ret = ret.multiply(val);
	        val = val.subtract(TWO);
	    }
	    
	    try{ 
	    	ft.join();
	    }
	    catch (Exception e) {}
	    
	    // n!! * (n-1)!! = n!
	    return ret.multiply( ft.ret ).doubleValue();
	}

}
