import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MultipleChoice extends Question implements Serializable{
	String rightAnswer;
	String[] options = new String[4];
	public MultipleChoice() {
		super();
		
	}
	public MultipleChoice(String question, boolean isCorrect, int points, boolean isAnswered, String[] options, String rightAnswer,String userAnswer) {
		super(question, isCorrect, points,isAnswered,rightAnswer,userAnswer);
		this.options = options;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) throws EmptyFieldException {
		
		for(String option:options) {
			if(option.equals(""))
				throw new EmptyFieldException();
		}
		
		this.options = options;
	}
	
	
	public String toString() {
		
		return this.getQuestion()+"\n"+Arrays.toString(this.options);
	}
	
}
