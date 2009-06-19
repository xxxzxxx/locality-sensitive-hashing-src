package jp.ndca.toolkit.cluster.lsh.hash;

import java.io.Serializable;
import java.util.Map;

/**
 * ハッシュ関数ベクトルの要素であるハッシュ関数の機能を表すインターフェイスです。
 * @author hattori_tsukasa
 *
 */
public interface HashFunction extends Serializable{
	
	/**
	 * ハッシュ値を取得します。
	 * データがハミングコードである場合このメソッドを使用すべきです。
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByHamming( int[] x );

	/**
	 * ハッシュ値を取得します。
	 * データがハミングコードである場合このメソッドを使用すべきです。
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByHamming( short[] x );

	/**
	 * ハッシュ値を取得します。
	 * データがハミングコードである場合このメソッドを使用すべきです。
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByHamming( Integer[] x );

	
	/**
	 * ハッシュ値を取得します。
	 * データがハミングコードである場合このメソッドを使用すべきです。
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByHamming( Short[] x );

	
	/**
	 * ハッシュ値を取得します。
	 * データが dense ベクトルである場合このメソッドが呼ばれます。
	 * 
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByDense( float[] x );
	
	/**
	 * ハッシュ値を取得します。
	 * データが dense ベクトルである場合このメソッドが呼ばれます。
	 * 
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByDense( Float[] x );

	/**
	 * ハッシュ値を取得します。
	 * データが dense ベクトルである場合このメソッドが呼ばれます。
	 * 
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByDense( double[] x );

	/**
	 * ハッシュ値を取得します。
	 * データが dense ベクトルである場合このメソッドが呼ばれます。
	 * 
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueByDense( Double[] x );

	/**
	 * ハッシュ値を取得します。
	 * データが sparce ベクトルである場合このメソッドが呼ばれます。
	 * @param x データ
	 * @return　ハッシュ値の要素
	 */
	public int getHashValueBySparse( Map<Integer, ? extends Number> x );

}
