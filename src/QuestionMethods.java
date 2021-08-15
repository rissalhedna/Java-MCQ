import java.util.LinkedList;

public interface QuestionMethods {
	public void saveQuestions();
	public void loadQuestions();
	public void clearQuestions();
	public LinkedList<String> getCorrectAnswers();
	public LinkedList<String> getWrongAnswers();
	public void addQuestion(Question question);
	public void removeQuestion(int index);
}
