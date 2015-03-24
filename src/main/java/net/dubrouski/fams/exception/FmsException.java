package net.dubrouski.fams.exception;

public class FmsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public FmsException(String msg){
		super(msg);
	}
	
	public FmsException(String msg, Throwable cause){
		super(msg, cause);
	}
}
