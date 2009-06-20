package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * int[] で返ってくるハッシュ値に対して、スカラーの lsh Hash 値を割り当てます。
 * 
 * @author hattori_tsukasa
 *
 */
public class PstableHashMaker {

	/**
	 * 
	 * @param hashVector : HashFunctionVectorGeneratorで生成されるLSHハッシュ値
	 * @param hfvNum : HashFunctionVectorGeneratorの識別子
	 * @return
	 */
	public static String hashKeyTransForm( int[] hashVector, int hfvNum ){
		
		MessageDigest messageDigest = null;
		try{
			messageDigest= MessageDigest.getInstance( "MD5" );
		}
		catch( NoSuchAlgorithmException e ){}
		
		String a ="a";
		StringBuilder sb = new StringBuilder();
		sb.append( hfvNum + a );
		for( int value : hashVector ){
			sb.append( value + a );
		}
		byte[] binary = sb.toString().getBytes();
		byte[] hash   = messageDigest.digest( binary );
		return getHexDigest( hash );
		
	}
	
	private static String getHexDigest( byte[] digest ){
		final StringBuffer buf = new StringBuffer("");
	    for( int i=0 ; i < digest.length ; i++ ){
	        final int n = digest[i] & 0xFF;
	        if(n < 16) buf.append("0");
	        buf.append( Integer.toString(n, 16) );
	    }
	    return buf.toString().substring( 0, 24 );
	}
	
}
