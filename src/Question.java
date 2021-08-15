import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Question implements QuestionMethods , Serializable{
	
	LinkedList<Question> questions = new LinkedList<Question>();
	
	private String userAnswer;
	private String question;
	private boolean isCorrect = false;
	private boolean isAnswered = false;
	private String rightAnswer;
	private int points = 0;
	Question(){
		
	}
	public Question(String question, boolean isCorrect, int points,boolean isAnswered, String rightAnswer,String userAnswer) {
		this.question = question;
		this.isCorrect = isCorrect;
		this.isAnswered = isAnswered;
		this.points = points;
		this.rightAnswer = rightAnswer;
		this.userAnswer = userAnswer;
	}


	
	public void setQuestions(LinkedList<Question> questions) {
		this.questions = questions;
	}
	
	public LinkedList<Question> getQuestions() {
		return this.questions;
	}
	
	public String getRightAnswer() {
		return this.rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getUserAnswer() {
		return this.userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) throws EmptyFieldException{
		if(question.equals("")) {
			throw new EmptyFieldException();
		}
		this.question = question;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public boolean isAnswered() {
		return isAnswered;
	}
	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	public String toString() {
		return "Question: "+this.question;
	}
	
	//saves questions into the file
	public void saveQuestions() {
		//File that stores questions
		File questionsFile = new File("questions.txt");
		FileOutputStream output;
		ObjectOutputStream objectOutput;
		try {
			//writing object in;to the file
			output = new FileOutputStream(questionsFile);
			objectOutput = new ObjectOutputStream(output);
			
			objectOutput.writeObject(this.questions);
			
			objectOutput.close();
					
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	//loads questions from the file
	public void loadQuestions() {
		File questionsFile = new File("questions.txt");
		FileInputStream input;
		ObjectInputStream objectInput;
		LinkedList<Question> questionList = new LinkedList<Question>() ;
		//reading object from the file
		try {
			input = new FileInputStream(questionsFile);
			objectInput = new ObjectInputStream(input);
		
			questionList = (LinkedList<Question>) objectInput.readObject();
			
			this.setQuestions(questionList);
			input.close();
			objectInput.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	//clears all questions in the file
	public void clearQuestions() {
		try {
			
			FileWriter writer = new FileWriter("questions.txt");
			
			writer.write("");
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public void removeQuestion(int index) {
		questions.remove(index);
		saveQuestions();
	}
	
	public int getTotalPoints() {
		int sum = 0;
		for(Question question : this.questions) {
			sum += question.points;
		}
		
		return sum;
	}
	public LinkedList<String> getCorrectAnswers(){
		LinkedList<String> correct = new LinkedList<String>();
		for(Question question: this.questions) {
			if(question.isCorrect) {
				correct.add(question.question);
			}
		}
		return correct;
	}
	
	public LinkedList<String> getWrongAnswers(){
		LinkedList<String> wrong = new LinkedList<String>();
		for(Question question: this.questions) {
			if(!question.isCorrect) {
				wrong.add(question.question);
			}
		}
		return wrong;
	}
	
}
