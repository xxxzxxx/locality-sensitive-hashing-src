package jp.ndca.toolkit.cluster.lsh.hash.simHash.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import jp.ndca.toolkit.cluster.lsh.hash.simHash.SimHashHandlerWrapperLong;

public class SimHashedLongDataStoreTest {

	@Test
	public void testSearchWithinThreshold() throws Exception{

		List<Map<Integer,Short>> vectorList = makeSortData();

		
		//検索対象データの元次元数
		int demension = 500;

		
		//検索ベクトルデータをsimHashした後のlongデータ
		long[] resultList = new long[ vectorList.size() ];


		/**
　　　　* simHashの実行
　　　　*/
		SimHashHandlerWrapperLong simHash = new SimHashHandlerWrapperLong( demension );
		for( int i=0 ; i < resultList.length ; i++ ){
			Map<Integer,Short> vectorX = vectorList.get(i);
			resultList[i] = simHash.getSimHashByLong( vectorX );
		}
		SimHashedLongDataStore data = new SimHashedLongDataStore(resultList);


		//テストクエリを作成
		String[] strQuery = new String[]{ "1" , "105", "119", "152", "177", "196", "215", "258", "315", "343", "413", "448" };
		long query = simHash.getSimHashByLong( StringArrayToIntegerArray(strQuery) );

		/**
		 *　検索
　　　　*/
		int threshold = 15;
		long start = System.currentTimeMillis();
		int[] candidateIds = data.searchWithinThreshold( query, threshold );
		long end = System.currentTimeMillis();
		long diff  = end - start;
		
		/**
		 * 結果の表示
		 */
		System.out.println( candidateIds.length );
		for(int candidate :candidateIds)
			System.out.println(candidate);
		System.out.println( diff + "ms" );
		
	}

	
	/**
	 * 検索データを作成
	 * @return
	 * @throws IOException
	 */
	private List<Map<Integer,Short>> makeSortData() throws IOException{
		
		List< Map<Integer,Short> > vectorList = new ArrayList< Map<Integer,Short> >();
		
		InputStream is = 
			Thread.currentThread().getContextClassLoader().getResourceAsStream("lsh.txt");
		
		BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
		
		while( br.ready() ){
			String line = br.readLine();
			line = line.substring(1);                  //[を除去
			line = line.substring(0, line.length()-1); //]を除去
			String[] numbers = line.split(",");
			if( numbers.length != 0 )
				vectorList.add( StringArrayToIntegerArray(numbers) );
		}
		return vectorList;
	}
	
	
	/**
	 * Stringデータをベクトルデータに変換。
	 * @param array
	 * @return
	 */
	private static Map<Integer, Short> StringArrayToIntegerArray( String[] array ){
		
		Map<Integer, Short> resultMap = new HashMap<Integer, Short>();	
		for( int i = 0 ; i < array.length ; i++){
			Integer key  = null;
			if(array[i].equals(""))
				continue;
			key = Integer.parseInt( array[i].trim() );
			short num = resultMap.get(key) == null ? 0 : (short)(resultMap.get(key));
			resultMap.put(key, ++num);
		}
		return resultMap;
		
	}

}