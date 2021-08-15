import java.io.Serializable;

public class TrueFalse extends Question implements Serializable{
	
	public TrueFalse() {
		super();
	}

	public TrueFalse(String question, boolean isCorrect, int points,boolean isAnswered, String rightAnswer,String userAnswer) {
		super(question, isCorrect, points,isAnswered,rightAnswer,userAnswer);
	}

	public String toString() {	
		return this.getQuestion()+"\n True \n False";
	}
	
}
