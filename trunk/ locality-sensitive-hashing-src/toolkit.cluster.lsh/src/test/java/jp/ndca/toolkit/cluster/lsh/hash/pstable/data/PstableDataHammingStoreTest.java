package jp.ndca.toolkit.cluster.lsh.hash.pstable.data;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import jp.ndca.toolkit.cluster.lsh.hash.HashProbability;
import jp.ndca.toolkit.cluster.lsh.hash.pstable.NormalHashFunctionHandler;
import jp.ndca.toolkit.cluster.lsh.hash.pstable.NormalHashProbability;
import jp.ndca.toolkit.cluster.lsh.hash.pstable.PstableHandler;
import jp.ndca.toolkit.cluster.lsh.hash.pstable.PstableHandlerWrapper;
import jp.ndca.toolkit.cluster.lsh.hash.pstable.data.PstableHammingDataStore;

public class PstableDataHammingStoreTest {
	
	
	@Test
	public void testSearch() throws IOException{
		
		/**
		 * ベクトルデータの読み込み
		 */
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("lsh.txt");
		BufferedReader br = new BufferedReader( new InputStreamReader(is) );
		
		List<int[]> vectorList = new ArrayList<int[]>();
		
		while( br.ready() ){
			String line = br.readLine();
			line = line.substring(1);                  //[を除去
			line = line.substring(0, line.length()-1); //]を除去
			String[] numbers = line.split(",");
			if(numbers.length != 0)
				vectorList.add( StringArrayToIntegerArray(numbers) );
		}
		
		/**
		 * LSHパラメータの取得
		 */
		Properties prop = new Properties();
		prop.load( Thread.currentThread().getContextClassLoader().getResourceAsStream("lsh.properties") );

		double c = Double.valueOf(prop.getProperty("c"));
		double r = Double.valueOf(prop.getProperty("r"));
			
		HashProbability pp = new NormalHashProbability( c, r );
		double p1 = pp.getGoodHashProb();
		double p2 = pp.getBadHashProb();

		
		int n = Integer.parseInt(prop.getProperty("n"));
		int dimension = Integer.parseInt(prop.getProperty("dimension"));
		
		PstableHandler ph = new NormalHashFunctionHandler( p1, p2, n, dimension, r );
		
		int K = ph.getK();
		int L = ph.getL();
		
		
		System.out.println(K);
		System.out.println(L);

		/**
		 * 検索データの変換
		 */
		PstableHandlerWrapper phw = new PstableHandlerWrapper( ph.generateHashFunctionVectorGeneratorList( K, L ) );
		PstableHammingDataStore pstableDataHammingStore = new PstableHammingDataStore( vectorList, phw );
		
		
		/**
		 * 検索の実行
		 */
		int[] query = new int[]{  1 , 105, 119, 152, 177, 196, 215, 258, 315, 343, 413, 448 };

		long start = System.currentTimeMillis();
		String[] pstableHashes =  phw.getPstableHashes(query);
		int[] result = pstableDataHammingStore.search(pstableHashes);
		long end = System.currentTimeMillis();
		long diff = end - start;
		
		assertEquals( true, classify(result, 9) );
		System.out.println(vectorList.size());
		System.out.println(result.length);
		System.out.println( diff +"ms" );
		
	}
	
	private static int[] StringArrayToIntegerArray( String[] array ){
		int[] intArray = new int[ array.length ];
			for( int i = 0 ; i < array.length ; i++){
				if(array[i].equals(""))
					continue;
				intArray[i] = Integer.parseInt( array[i].trim() );
			}
		return intArray;
	}
	
	private static boolean classify( int[] candidateIds, int id ){
		for( int candidateId : candidateIds){
			if(candidateId==id)
				return true;
		}
		return false;
	}

}
