package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.List;
import java.util.Map;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

public class PstableHandlerWrapper {
	
	//K×L個の変換行列
	private List<HashFunctionVectorGenerator> hfvgList;

	/**
	 * 
	 * @param hfvgList : K×L個の変換行列
	 */
	public PstableHandlerWrapper( List<HashFunctionVectorGenerator> hfvgList ){
		this.hfvgList = hfvgList;
	}

	
	/**
	 * pstableで変換したL個のハッシュ値を取得します。
	 * @param x　：　変換前のベクトル
	 * @return : L個のpstableハッシュ値
	 */
	public String[] getPstableHashes( Map<Integer, ? extends Number> x ){
		String[] pstableHashes = new String[ hfvgList.size() ];
		for( int i = 0 ; i < hfvgList.size() ; i++ ){
			HashFunctionVectorGenerator hfvg =  hfvgList.get(i);
			int[] hashVector = hfvg.getIntArrayHashVectorBySparse(x);
			pstableHashes[i] = PstableHashMaker.hashKeyTransForm( hashVector, i );
		}
		return pstableHashes;
	}
	

	/**
	 * pstableで変換したL個のハッシュ値を取得します。
	 * @param vectorX　：　変換前のベクトル
	 * @return : simHash値
	 */
	public String[] getPstableHashes( double[] x ){
		String[] pstableHashes = new String[ hfvgList.size() ];
		for( int i = 0 ; i < hfvgList.size() ; i++ ){
			HashFunctionVectorGenerator hfvg =  hfvgList.get(i);
			int[] hashVector = hfvg.getIntArrayHashVectorByDense(x);
			pstableHashes[i] = PstableHashMaker.hashKeyTransForm( hashVector, i );
		}
		return pstableHashes;
	}
	

	/**
	 * pstableで変換したL個のハッシュ値を取得します。引数ハミングベクトルは、ソートされてなければなりません。
	 * @param vectorX　：　変換前のベクトル
	 * @return : simHash値
	 */
	public String[] getPstableHashes( int[] x ){
		String[] pstableHashes = new String[ hfvgList.size() ];
		for( int i = 0 ; i < hfvgList.size() ; i++ ){
			HashFunctionVectorGenerator hfvg =  hfvgList.get(i);
			int[] hashVector = hfvg.getIntArrayHashVectorByHamming(x);
			pstableHashes[i] = PstableHashMaker.hashKeyTransForm( hashVector, i );
		}
		return pstableHashes;
	}
	

	/**
	 * pstableで変換したL個のハッシュ値を取得します。引数ハミングベクトルは、ソートされてなければなりません。
	 * @param vectorX　：　変換前のベクトル
	 * @return : simHash値
	 */
	public String[] getPstableHashes( Integer[] x ){
		String[] pstableHashes = new String[ hfvgList.size() ];
		for( int i = 0 ; i < hfvgList.size() ; i++ ){
			HashFunctionVectorGenerator hfvg =  hfvgList.get(i);
			int[] hashVector = hfvg.getIntArrayHashVectorByHamming(x);
			pstableHashes[i] = PstableHashMaker.hashKeyTransForm( hashVector, i );
		}
		return pstableHashes;
	}

}
