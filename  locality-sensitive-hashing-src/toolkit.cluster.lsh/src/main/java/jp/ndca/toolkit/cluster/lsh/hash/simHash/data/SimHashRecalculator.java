package jp.ndca.toolkit.cluster.lsh.hash.simHash.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimHashRecalculator {
	
	private RecalulateMethod recalulateMethod;

	public RecalulateMethod getRecalulateMethod() {
		return recalulateMethod;
	}

	public void setRecalulateMethod(RecalulateMethod recalulateMethod) {
		this.recalulateMethod = recalulateMethod;
	}
	
	/**
	 * @param num : 取得したい近傍データの数
	 * @param queryId : クエリID
	 * @param threshold : 再計算する際のCosin類似度の閾値
	 * @param simHashResult : クエリIDでsimHashを行った結果
	 * @return
	 */
	public List<Integer> serchNearIds( int num, int queryId, double threshold, SimHashResult simHashResult ){
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add( simHashResult.getList0() );
		list.add( simHashResult.getList1() );
		list.add( simHashResult.getList2() );
		list.add( simHashResult.getList3() );
		
		List<Integer> result = new ArrayList<Integer>();

		for( List<Integer> idList : list ){
			if( idList.size() != 0 ){
				for( Integer id : idList){
					if( recalulateMethod.caluculate(id, queryId, threshold ) );
						result.add(id);
				}
				if( num <= result.size()){
					Collections.sort(result);
					return result.subList(0, num - 1);
				}
			}
		}
		return result;
	}

}
