package jp.ndca.toolkit.cluster.lsh.math.integral;

/**
 * 積分の機能を表すインターフェースです。
 * @author hattori_tsukasa
 *
 */
public interface Integral {
	
	public abstract void setInterval( double start, double end, double strip );
	
	public abstract double executeIntegral();

}
