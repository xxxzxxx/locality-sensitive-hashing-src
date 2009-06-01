package jp.ndca.toolkit.cluster.lsh.math.exception;

@SuppressWarnings("serial")
public class IntegralIntervalException extends RuntimeException{
	
	public IntegralIntervalException(){}
	
	public IntegralIntervalException(String message){
		super(message);
	}
	
	public IntegralIntervalException(Throwable cause){
		super(cause);
		
	}
	
	public IntegralIntervalException(String message , Throwable cause){
		super(message,cause);
	}


}
