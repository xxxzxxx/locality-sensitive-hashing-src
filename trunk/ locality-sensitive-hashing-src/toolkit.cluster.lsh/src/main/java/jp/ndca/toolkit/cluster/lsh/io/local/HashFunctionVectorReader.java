package jp.ndca.toolkit.cluster.lsh.io.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

/**
 * ローカルに直列化されているHashFunctionVectorオブジェクトを取得するクラスです。
 * @author hattori_tsukasa
 *
 */
public class HashFunctionVectorReader {
	
	ObjectInputStream ois = null;
	
	public void setObjectInputStream( String filePath ) throws IOException{
		ois = new ObjectInputStream( new FileInputStream(filePath) );
	}
	
	public HashFunctionVectorGenerator readObject() throws IOException, ClassNotFoundException{
		return (HashFunctionVectorGenerator)ois.readObject();
	}
	
	public void close() throws IOException{
		if(ois != null) ois.close();
	}

}
