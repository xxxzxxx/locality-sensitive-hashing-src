package jp.ndca.toolkit.cluster.lsh.hash;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jp.ndca.toolkit.cluster.lsh.exception.UnsuitableDemensionException;

/**
 * LSHハッシュ値を生成するクラスです。
 * @author hattori_tsukasa
 *
 */
@SuppressWarnings("serial")
public class HashFunctionVector implements Serializable{
	
	//hashFunctionVector (本来mapで管理すべだが、memory節約のためListで管理)
	List<HashFunction> hashFunctionList;
	
	//vector demension
	int K;
	
	public HashFunctionVector( List<HashFunction> hashFunctionList ){
		if( hashFunctionList.size() != K ) throw new UnsuitableDemensionException();
		this.hashFunctionList = hashFunctionList;
		this.K =hashFunctionList.size();
	}
	
	/**
	 * LSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntHashVectorByHamming(int[] x){
		int[] result = new int[K];
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByHamming(x);			
		}
		return result;
	}
	
	/**
	 * LSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntHashVectorByHamming(Integer[] x){
		int[] result = new int[K];
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByHamming(x);			
		}
		return result;
	}

	
	/**
	 * LSHハッシュ値を取得します。dense ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntHashVectorByDense(double[] x){
		int[] result = new int[K];
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueByDense(x);			
		}
		return result;
	}
	
	/**
	 * LSHハッシュ値を取得します。sparse ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public int[] getIntHashVectorBySparse(Map<Integer, ? extends Number> x){
		int[] result = new int[K];
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result[i] = func.getHashValueBySparse(x);			
		}
		return result;
	}
	
	
	/**
	 * LSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByHamming(int[] x){
		String result = "";
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByHamming(x);			
		}
		return result;
	}
	
	/**
	 * LSHハッシュ値を取得します。ハミングコード(引数の値が0,1のみ)用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByHamming(Integer[] x){
		String result = "";
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByHamming(x);			
		}
		return result;
	}

	
	/**
	 * LSHハッシュ値を取得します。dense ベクトル用です。
	 * @param x
	 * @return LSH Hash Value
	 */
	public String getStringHashVectorByDense(double[] x){
		String result = "";
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueByDense(x);			
		}
		return result;
	}
	
	public String getStringHashVectorBySparse(Map<Integer, ? extends Number> x){
		String result = "";
		for(int i = 0 ; i < K ;i++){
			HashFunction func = hashFunctionList.get(i);
			result += func.getHashValueBySparse(x);			
		}
		return result;
	}


}
