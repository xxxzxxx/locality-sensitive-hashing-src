package jp.ndca.toolkit.cluster.lsh.hash.pstable;

import java.util.List;

import jp.ndca.toolkit.cluster.lsh.hash.HashFunction;
import jp.ndca.toolkit.cluster.lsh.hash.HashFunctionGenerator;

/**
 * ハッシュ関数ベクトルの要素となるハッシュ関数を生成とLSHの変換パラメータL,K,ρを取得を管理するためのインターフェイスです。
 * @author hattori_tsukasa
 *
 */
public interface PstableHandler extends HashFunctionGenerator{
	
	/**
	 * ハッシュ関数ベクトルの次元を取得します。
	 */
	public int get_K();
	
	/**
	 * 一データ当たりに作成するハッシュベクトルの数を取得します。
	 */	
	public int get_L();
	
	/**
	 * 計算時間やL(=n^ρ)を決定するρを取得します。
	 */
	public double get_rho();
	
	/**
	 * 
	 * @param K　次元圧縮後のデータ次元(ハッシュ関数の個数)
	 */
	public void makeHashFunctionList(int K);
	
	/**
	 * 
	 * @return ハッシュ関数のリスト。
	 * 
	 */
	public List<HashFunction> getHashFunctionList();
	
}
