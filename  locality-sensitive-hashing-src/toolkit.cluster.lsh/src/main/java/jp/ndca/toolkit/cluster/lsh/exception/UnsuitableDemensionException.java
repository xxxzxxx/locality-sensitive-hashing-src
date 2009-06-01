package jp.ndca.toolkit.cluster.lsh.exception;

@SuppressWarnings("serial")
public class UnsuitableDemensionException extends RuntimeException{
	
	public UnsuitableDemensionException(){}
	
	public UnsuitableDemensionException(String message){
		super(message);
	}
	
	public UnsuitableDemensionException(Throwable cause){
		super(cause);
		
	}
	
	public UnsuitableDemensionException(String message , Throwable cause){
		super(message,cause);
	}
	
}
