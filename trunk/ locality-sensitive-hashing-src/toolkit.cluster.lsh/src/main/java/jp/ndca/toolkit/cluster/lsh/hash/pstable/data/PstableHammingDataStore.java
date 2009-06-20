package jp.ndca.toolkit.cluster.lsh.hash.pstable.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.ndca.toolkit.cluster.lsh.hash.pstable.PstableHandlerWrapper;

public class PstableHammingDataStore {
	
	private Map<String, List<Integer>> pstableHashMap;

	/**
	 * 引数データを元に、ハッシュ値とデータID群のmapを生成するコンストラクタです。
	 * @param vectorList : ハミングデータ
	 * @param phw
	 */
	public PstableHammingDataStore( List<int[]> vectorList, PstableHandlerWrapper  phw ){
		
		pstableHashMap = new HashMap<String, List<Integer>>();
		
		for( int i = 0 ; i < vectorList.size() ; i++ ){
			int[] vector  = vectorList.get(i);
			String[] pstableHashes = phw.getPstableHashes( vector );
			
			for( String pstableHash : pstableHashes ){
				List<Integer> factorList = pstableHashMap.get(pstableHash);
				if( factorList == null ){
					factorList = new ArrayList<Integer>();
					pstableHashMap.put(pstableHash, factorList);
				}
				factorList.add(i);
			}
		}
		
	}
	
	
	/**
	 * lshハッシュ値の総数を取得します。
	 * @return
	 */
	public int size(){
		return pstableHashMap.size();
	}

	
	/**
	 * pstableの検索を実行します。
	 * @param pstableHashes
	 * @return lshの結果。クエリに対する近傍データの候補群
	 */
	public int[] search( String[] pstableHashes ){
		
		Set<Integer> resultSet = new HashSet<Integer>();
		
		for( String pstableHash : pstableHashes ){
			List<Integer> factorList = pstableHashMap.get(pstableHash);
			for( Integer factor : factorList )
				resultSet.add(factor);
		}
		
		int[] result = new int[ resultSet.size() ];

		int i = 0;
		for( Integer num : resultSet){
			result[i] = num;
			i++;
		}

		return result;
		
	}
	
}
