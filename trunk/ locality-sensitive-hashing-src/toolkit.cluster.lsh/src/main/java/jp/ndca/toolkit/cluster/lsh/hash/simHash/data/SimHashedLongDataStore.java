package jp.ndca.toolkit.cluster.lsh.hash.simHash.data;

import java.util.ArrayList;
import java.util.List;

public class SimHashedLongDataStore {
	
	private long[] simHashLongData;
		
	public SimHashedLongDataStore( long[] simHashLongData ){
		this.simHashLongData = simHashLongData;
	}
	
	public int size(){
		return simHashLongData.length;
	}
	
	/**
	 * クエリ ( vectorY ) に対してHamming距離がthreshold以下のデータIDの一覧を取得します。
	 * @param limit
	 * @return 
	 */
	public int[] searchWithinThreshold( long vectorY, int threshold ){
		List<Integer> resultArray = new ArrayList<Integer>();
		for(int i = 0; i < size() ; i++){
			long diff = simHashLongData[i] ^ vectorY;
			if(bitCount(diff) <= threshold) {
				resultArray.add(i);
			}
		}
		int size = resultArray.size();
		int[] result = new int[size];
		for(int i = 0 ; i < size ; i++){
			result[i] = resultArray.get(i);
		}
		return result;
	}
	
	/**
	 * クエリ ( vectorY ) に対してHamming距離がstart以上end以下に該当するデータIDの一覧を取得します。
	 * @param limit
	 * @return 
	 */
	public int[] searchBetweenThreshold( long vectorY , int start , int end ){
		List<Integer> resultArray = new ArrayList<Integer>();
		for( int i = 0 ; i < size() ; i++ ){
			long diff = simHashLongData[i] ^ vectorY;
			int num = bitCount(diff);
			if( num <= end && start <= num ) {
				resultArray.add(i);
			}
		}
		int size = resultArray.size();
		int[] result = new int[size];
		for( int i = 0 ; i < size ; i++ ){
			result[i] = resultArray.get(i);
		}
		return result;
	}
	
	/**
	 * 
	 * @param vectorY
	 * @param threshold
	 * @return
	 */
	public SimHashResult searchForSifting( long vectorY, int candidateDataNum ){
		
		List<Integer> resultArray0 = new ArrayList<Integer>();
		List<Integer> resultArray1 = new ArrayList<Integer>();
		List<Integer> resultArray2 = new ArrayList<Integer>();
		List<Integer> resultArray3 = new ArrayList<Integer>();
//		List<Integer> resultArray4 = new ArrayList<Integer>();
//		List<Integer> resultArray5 = new ArrayList<Integer>();
//		List<Integer> resultArray6 = new ArrayList<Integer>();

		for(int i = 0; i < size() ; i++){
			
			int nearDataNum = 0;
			
			long diff = simHashLongData[i] ^ vectorY;
			int hammingDistance = bitCount(diff) /10;
			
			if( hammingDistance < 3 ){
				if( hammingDistance < 1 ){
					resultArray0.add(i);
				}
				else if ( 1 < hammingDistance ){
					resultArray2.add(i);
				}
				else{
					resultArray1.add(i);
				}
				nearDataNum++;
			}		
			else if( 3 < hammingDistance )
				continue;
			else
				resultArray3.add(i);
			if( candidateDataNum == nearDataNum )
				break;
			
		}
		
		SimHashResult result = new SimHashResult();
		result.setList0( resultArray0 );
		result.setList1( resultArray1 );
		result.setList2( resultArray2 );
		result.setList3( resultArray3 );
		return result;
		
	}

	
	/**
	 * 後で改良する予定
	 * @param diff
	 * @return
	 */
	private int bitCount(long diff){
		return Long.bitCount(diff);
	}
	
}
