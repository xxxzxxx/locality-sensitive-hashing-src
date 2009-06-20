package jp.ndca.toolkit.cluster.lsh.hash;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * LSHハッシュ値を生成するクラスです。
 * @author hattori_tsukasa
 *
 */
@SuppressWarnings("serial")
public class HashFunctionVectorGenerator implements Serializable{

	List<HashFunction> hashFunctionList;
	
	//reduced vector demension
	int K;
	
	
	public HashFunctionVectorGenerator( List<HashFunction> hashFunctionList ){
		this.K = hashFunctionList.size();
		this.hashFunctionList = hashFunctionList;
	}
	
	
	/**
	 * int[] 型のLSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntArrayHashVectorByHamming( int[] x ){
		int[] result = new int[K];
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByHamming(x);			
		}
		return result;
	}
	
	
	/**
	 * int[] 型のLSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntArrayHashVectorByHamming( Integer[] x ){
		int[] result = new int[K];
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByHamming(x);			
		}
		return result;
	}

	
	/**
	 * String型のLSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByHamming( int[] x ){
		String result = "";
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByHamming(x);			
		}
		return result;
	}
	
	
	/**
	 * String型のLSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByHamming( Integer[] x ){
		String result = "";
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByHamming(x);			
		}
		return result;
	}


	/**
	 * int[] 型のLSHハッシュ値を取得します。dense ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntArrayHashVectorByDense( double[] x ){
		int[] result = new int[K];
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByDense(x);			
		}
		return result;
	}

	
	/**
	 * String 型のLSHハッシュ値を取得します。dense ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByDense( double[] x ){
		String result = "";
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByDense(x);			
		}
		return result;
	}
	
	
	/**
	 * LSHハッシュ値を取得します。sparse ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntArrayHashVectorBySparse( Map<Integer, ? extends Number> x ){
		int[] result = new int[K];
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueBySparse(x);			
		}
		return result;
	}
	
	
	public String getStringHashVectorBySparse( Map<Integer, ? extends Number> x ){
		String result = "";
		for(int i = 0 ; i < K ; i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueBySparse(x);			
		}
		return result;
	}

}
