package jp.ndca.toolkit.cluster.lsh.hash.simHash.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import jp.ndca.toolkit.cluster.lsh.hash.simHash.SimHashHandlerWrapperLong;

public class SimHashedLongDataStoreTest {

	@Test
	public void testSearchWithinThreshold(){

		//検索対象データの元次元数
		int demension = 5500000;

		//検索対象データ数
		int n = 1000000;

		/**
　　　　* 検索ベクトルデータをdataListへn個格納する。ここでは省略。
　　　　*/
		List< Map<Integer,Short> > dataList = new ArrayList< Map<Integer,Short> >();

		//検索ベクトルデータをsimHashした後のlongデータ
		long[] resultList = new long[n];


		/**
　　　　* simHashの実行
　　　　*/
		SimHashHandlerWrapperLong simHash = new SimHashHandlerWrapperLong(demension);
		for(int i=0 ; i < resultList.length ; i++){
			Map<Integer,Short> vectorX = dataList.get(i);
			resultList[i] = simHash.getSimHashByLong(vectorX);
		}

		SimHashedLongDataStore data = new SimHashedLongDataStore(resultList);

		//テストクエリとしてid 0番目のデータを使用。
		long query = resultList[0];

		/**
		 *　検索
　　　　*/
		int threshold = 15;
		int[] candidateIds = data.searchWithinThreshold( query, threshold );
		
		
　　　　/**
　　　　* 以下、必要であれば近傍データ候補のcandidateIdsを再計算する。
　　　　*/

　　}

}