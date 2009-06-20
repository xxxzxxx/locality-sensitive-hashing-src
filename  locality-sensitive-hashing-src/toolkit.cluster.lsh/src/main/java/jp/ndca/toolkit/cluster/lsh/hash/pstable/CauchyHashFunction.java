package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.Map;
import java.util.Set;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;

@SuppressWarnings("serial")
public class CauchyHashFunction implements HashFunction{
	
	double[] a;
	double b;
	double r;
	
	
	public CauchyHashFunction( double[] a, double b, double r ){
		this.a = a;
		this.b = b;
		this.r = r;		
	}
	
	
	@Override
	public int getHashValueByHamming( int[] x ){
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += a[x[i]];
		}
		return (int)((result+b)/r);
	}
	
	
	@Override
	public int getHashValueByHamming( Integer[] x ) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += a[x[i]];
		}
		return (int)((result+b)/r);
	}
	
	
	@Override
	public int getHashValueByHamming( short[] x ) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += a[x[i]];
		}
		return (int)((result+b)/r);
	}

	
	@Override
	public int getHashValueByHamming( Short[] x ) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += a[x[i]];
		}
		return (int)((result+b)/r);
	}

	
	@Override
	public int getHashValueByDense( double[] x ){
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * a[i];
		}
		return (int)((result+b)/r);
	}
	
	
	@Override
	public int getHashValueByDense( Double[] x ){
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * a[i];
		}
		return (int)((result+b)/r);
	}

	
	@Override
	public int getHashValueByDense( float[] x ) {
		double result = 0.0d;
		for( int i=0 ; i < x.length ; i++ ){
			result += x[i] * a[i];
		}
		return (int)((result+b)/r);
	}

	
	@Override
	public int getHashValueByDense( Float[] x ) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * a[i];
		}
		return (int)((result+b)/r);
	}

	
	@Override
	public int getHashValueBySparse( Map<Integer, ? extends Number> x ){
		double result = 0.0d;
		Set<Integer> keys = x.keySet();
		for( Integer key : keys ){
			result += a[key] * x.get(key).doubleValue();
		}
		return (int)((result+b)/r);
	}

}
