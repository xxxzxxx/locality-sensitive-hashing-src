package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import java.util.Map;
import java.util.Set;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;

@SuppressWarnings("serial")
public class Cosine_HashFunction implements HashFunction{
	
	double[] r;
	
	public Cosine_HashFunction( double[] r ){
		this.r = r;
	}

	@Override
	public int getHashValueByHamming(short[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += r[x[i]];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByHamming(Short[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += r[x[i]];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByHamming(int[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += r[x[i]];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByHamming(Integer[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += r[x[i]];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByDense(float[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * r[i];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByDense(Float[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * r[i];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}
	
	@Override
	public int getHashValueByDense(Double[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * r[i];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int getHashValueByDense(double[] x) {
		double result = 0.0d;
		for(int i=0 ; i < x.length ; i++){
			result += x[i] * r[i];
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}
	
	@Override	
	public int getHashValueBySparse(Map<Integer, ? extends Number> x) {
		double result = 0.0d;
		Set<Integer> keys = x.keySet();
		for(Integer key:keys){
			result += r[key]*x.get(key).doubleValue();
		}
		if(result >= 0)
			return 1;
		else
			return 0;
	}

	
}
