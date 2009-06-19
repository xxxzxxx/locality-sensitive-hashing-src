package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Integer[] で返ってくるハッシュ値に対して、スカラーの lsh Hash 値を割り当てます。
 * lsh Hashは主に区切り文字とMD5などで作成します。
 * @author hattori_tsukasa
 *
 */
public class PstableHashMaker {
	
	public String hashKeyTransForm(int[] hashVector, String hfvNum){
		MessageDigest md5 = null;
		try{
			md5= MessageDigest.getInstance( "MD5" );
		}
		catch( NoSuchAlgorithmException e ){
			System.out.println("MD5 is not to be");
		}
		String a ="a";
		StringBuilder sb = new StringBuilder();
		sb.append( hfvNum + a );
		for( int value : hashVector ){
			sb.append( value + a );
		}
		byte[] binary = sb.toString().getBytes();
		byte[] hash   = md5.digest( binary );
		return getHexDigest( hash );
	}
	
	public String hashKeyTransForm(int[] hashVector, Integer hfvNum){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}
		catch(NoSuchAlgorithmException e){
			System.out.println("MD5 is not to be");
		}
		String a ="a";
		StringBuilder sb = new StringBuilder();
		sb.append( hfvNum + a );
		for( int value : hashVector ){
			sb.append( value + a );
		}
		byte[] binary = sb.toString().getBytes();
		byte[] hash   = md5.digest( binary );
		
		return getHexDigest(hash);
	}
	
	private String getHexDigest( byte[] digest ){
		final StringBuffer buf = new StringBuffer("");
	    for( int i=0 ; i < digest.length ; i++ ){
	        final int n = digest[i] & 0xFF;
	        if(n < 16) buf.append("0");
	        buf.append( Integer.toString(n, 16) );
	    }
	    return buf.toString().substring( 0, 24 );
	}
	
}
