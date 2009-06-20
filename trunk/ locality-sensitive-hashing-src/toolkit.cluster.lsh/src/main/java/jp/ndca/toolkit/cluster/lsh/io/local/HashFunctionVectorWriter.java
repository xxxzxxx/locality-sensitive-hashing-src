package jp.ndca.toolkit.cluster.lsh.io.local;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

/**
 * ローカルにHashFunctionVectorオブジェクトを書き出すクラスです。
 * 
 * @author hattori_tsukasa
 *
 */
public class HashFunctionVectorWriter {
	
	ObjectOutputStream oos = null;
	
	public void setObjectOutputStream( String filePath ) throws IOException{
		oos = new ObjectOutputStream( new FileOutputStream(filePath) );
	}
	
	public void writeObject( HashFunctionVectorGenerator func ) throws IOException{
		oos.writeObject(func);
		oos.flush();
	}
	
	public void close() throws IOException{
		if(oos !=null) oos.close();
	}
	
}
