package jp.ndca.toolkit.cluster.lsh.math.permutation;

import java.util.HashSet;
import java.util.Set;

public class ApproximateRandomPermutationFunction {
	
	private int prime;
	private double a;
	private double b;
	
	public ApproximateRandomPermutationFunction(int prime){
		this.prime = prime;
		double aa = prime * Math.random();
		while( aa==0 || aa==prime ){
			aa = prime * Math.random();
		}
		this.a = aa;
		double bb = (int)( prime * Math.random() );
		while( bb==prime ){
			bb = prime * Math.random();
		}
		this.b = bb;
	}
	
	public Integer[] execute(Integer[] permutation){
		Set<Integer> set = new HashSet<Integer>();
		for( int i =0 ; i < permutation.length ; i++ ){
			int tmp = (int)( ((a * permutation[i]) + b )/prime );
			set.add(tmp);
		}
		return set.toArray(new Integer[set.size()]);
	}

}
