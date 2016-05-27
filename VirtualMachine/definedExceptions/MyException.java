package tp.pr5.mv.definedExceptions;

public class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public MyException(String s) {
		super();
		this.message = s;
	}
	
	public String toString() {
		return this.message;
	}
}
