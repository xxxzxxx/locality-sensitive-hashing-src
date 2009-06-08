package jp.ndca.toolkit.cluster.lsh.data.simHash;

import java.util.ArrayList;
import java.util.List;

public class SimHashedLongData {
	
	private long[] simHashLongData;
		
	public SimHashedLongData(long[] simHashLongData){
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
	public int[] searchWithinThreshhold( long vectorY , int threshold ){
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
	public int[] searchBetWeenThreshhold( long vectorY , int start , int end ){
		List<Integer> resultArray = new ArrayList<Integer>();
		for(int i = 0; i < size() ; i++){
			long diff = simHashLongData[i] ^ vectorY;
			int num = bitCount(diff);
			if( num <= end && start <= num ) {
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
	 * 後で改良する予定
	 * @param diff
	 * @return
	 */
	private int bitCount(long diff){
		return Long.bitCount(diff);
	}
	
}
