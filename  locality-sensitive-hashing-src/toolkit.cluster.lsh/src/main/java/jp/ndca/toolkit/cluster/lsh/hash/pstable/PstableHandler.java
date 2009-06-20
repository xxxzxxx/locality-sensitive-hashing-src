package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionGenerator;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionVectorGenerator;

/**
 * ハッシュ関数ベクトルの要素となるハッシュ関数を生成とLSHの変換パラメータL,K,ρを取得を管理するためのインターフェイスです。
 * @author hattori_tsukasa
 *
 */
public interface PstableHandler extends HashFunctionGenerator{
	
	/**
	 * ハッシュ関数ベクトルの次元を取得します。
	 */
	public int getK();
	
	/**
	 * 一データ当たりに作成するハッシュベクトルの数を取得します。
	 */	
	public int getL();
	
	/**
	 * 計算時間やL(=n^ρ)を決定するρを取得します。
	 */
	public double getRho();
	
	
	/**
	 * 	ハッシュ関数のリストを取得します。このリストは毎回ランダムに生成されます。
	 * @param K　: 次元圧縮後のデータ次元数(ハッシュ関数の個数)
	 */
	public List<HashFunction> generateHashFunctionList( int K );
	
	
	/**
	 * LSHハッシュ値を生成するHashFunctionVectorGeneratorのリストを返します。このリストは毎回ランダムに生成されます。
	 * @param K　: 次元圧縮後のデータ次元数(ハッシュ関数の個数)
	 * @param L  : 一データ辺りに生成するLSHハッシュ値の数
	 * @return
	 */
	public List<HashFunctionVectorGenerator> generateHashFunctionVectorGeneratorList( int K, int L );

}
