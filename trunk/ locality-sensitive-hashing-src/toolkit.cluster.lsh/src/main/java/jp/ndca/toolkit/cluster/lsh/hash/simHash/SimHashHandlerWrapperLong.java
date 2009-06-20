package jp.ndca.toolkit.cluster.lsh.hash.simHash;

import java.util.Map;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

public class SimHashHandlerWrapperLong {
	
	private HashFunctionVectorGenerator hfv;
	
	/**
	 * simHash値として、longを返すHash関数ベクトルを作成するコンストラクタです。
	 * @param dimension : 元データの次元数
	 */
	public SimHashHandlerWrapperLong( int dimension ){
		SimHashHandler sh = new CosineHashFunctionHandler( dimension );
		hfv = sh.generateHashFunctionVectorGenerator( 64 );
	}
	
	/**
	 * simHashした値を取得します。
	 * @param vectorX　：　変換前のベクトル
	 * @return : simHash値
	 */
	public long getSimHashByLong( Map<Integer, ? extends Number> vectorX ){
		String str = hfv.getStringHashVectorBySparse( vectorX );
		return BinaryStringToLong(str);
	}
	
	private static long BinaryStringToLong( String str ){
		long num =0l;
		char chara = str.charAt(0);
		if( chara=='1' && str.length() == 64 ){
			str = '0' + str.substring(1);
			num = Long.parseLong( str, 2 );
			num = num ^ 9223372036854775807l;
			num = -1 * ( num+1 );
		}
		else{
			num = Long.parseLong( str, 2 );
		}
		return num;
	}
	
}
