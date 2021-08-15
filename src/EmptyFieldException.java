
public class EmptyFieldException extends Exception{
	String msg = "Field cannot be empty";
	
	EmptyFieldException(){
		super();
	}
	
	EmptyFieldException(String msg){
		super(msg);
	}
	
	public String getMessage() {
		return this.msg;
	}
}
