
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
	
	//removes the added characters from the strings
	public static String replaceChars(String string) {
		string = string.replace("&quot;", "").replace("&oacute;", "").replace("&#039;", "");
		
		return string;
	}
	
	public static int checker(int opt1, int opt2) throws InvalidChoiceException{
		Scanner scan = new Scanner(System.in);
		int cont = 1;
		int input = opt1;
		do {
			try {
				input = scan.nextInt();
				if(input != opt1 && input!= opt2) {
					throw new InvalidChoiceException("Wrong input. Input must be "+opt1+" or "+opt2);
				}
				cont = 0;
				
			}catch(InvalidChoiceException e) {
				System.out.println(e.getMessage());
			}
		}while(cont == 1);
		
		return input;
	}

	public static void main(String[] args) throws EmptyFieldException, InvalidChoiceException{
		// TODO Auto-generated method stub
		Question questions = new Question();
		Scanner scan = new Scanner(System.in);
		int playType;
		int continueSession;
		
		do {
		//whole game loop
		questions.loadQuestions();	
			
		//decides whether to play online or locally
		System.out.println("Do you want to play:");
		System.out.println("1) Locally");
		System.out.println("2) online");
		playType = checker(1,2);
		
		
			if(playType == 1) {
				

				int choice =0;
				
				continueSession = 0;
				scan = new Scanner(System.in);
				System.out.println("1) create");
				System.out.println("2) quiz");
				
				//create exception for choice and input mismatch
				
				choice = checker(1,2);
				
					if(choice == 1) {
						
						//Gets the type of the question
						
						int questionType = 1;
						System.out.println("What is the type of your question: ");
						System.out.println("1) Multiple Choice.");
						System.out.println("2) True/False.");
						questionType = checker(1,2);
						
						
						//We create a question
						Question q;
						
						//initialize its parameters
						String question;
						int points = 0;
						String correctAnswer;
						
						System.out.print("Enter the question: ");
						question = scan.nextLine();
						
						if(questionType == 1) {
							//If the question is a Multiple Choice
							q = new MultipleChoice();
							
							String[] options = new String[4];
							System.out.println("Enter 4 options: ");
							for(int i =0; i<4 ; i++) {
								System.out.print("Option"+i+": ");
								options[i] = scan.nextLine();
							}
							((MultipleChoice)q).setOptions(options);
						}else {
							//If the question is a True/False
							q = new TrueFalse();
						}
						
						System.out.print("Specify the correct answer: ");
						correctAnswer = scan.nextLine();
						System.out.print("Enter the points for this question: ");
						points = scan.nextInt();
						
						q.setPoints(points);
						q.setQuestion(question);
						q.setRightAnswer(correctAnswer);
						
						questions.addQuestion(q);
						
						questions.saveQuestions();
						
					}else if(choice==2){
						
						//Take the quiz
						int Score = 0;
						String answer;
						for(Question question : questions.getQuestions()) {
								System.out.println(question);
								answer = scan.nextLine();
								if(question.getRightAnswer().equalsIgnoreCase(answer)) {
									question.setCorrect(true);
									Score += question.getPoints();
								}else {
									question.setCorrect(false);
								}
								
					}
						System.out.println("-----------------------");
						System.out.println("Your total score: "+Score+"/"+questions.getTotalPoints());
						System.out.println("Wrong Answers: "+questions.getWrongAnswers());
						System.out.println("-----------------------");
						
					}
					
				
				
		}else {
			
			
			
			URL url;
			try {
				url = new URL("https://opentdb.com/api.php?amount=10&type=multiple");
				
				HttpURLConnection con;
				
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				
				int response = con.getResponseCode();
				String inline = "";
				if(response != 200)
					throw new RuntimeException("HttpResponseCode: " +response);
				else{
					
					Scanner sc = new Scanner(url.openStream());
					while(sc.hasNext())
					{
						
					inline+=sc.nextLine();
					}
					
			
					JSONParser parse = new JSONParser();
					JSONObject object = (JSONObject) parse.parse(inline);
					int points = 0;
					JSONArray resultsArray = (JSONArray) object.get("results");
					String[] allAnswersArray = new String[4];
					int randomIndex = new Random().nextInt(3);
					
					JSONObject wholeQuestionElement = new JSONObject();
					
					
					for(int i=0; i<resultsArray.size();i++) {
						wholeQuestionElement= (JSONObject) resultsArray.get(i);
						
						String questionStatement = (String)wholeQuestionElement.get("question");
						questionStatement = replaceChars(questionStatement);
						
						String correctAnswer = (String) wholeQuestionElement.get("correct_answer");
						//correctAnswer = replaceChars(correctAnswer);
						
						JSONArray wrongAnswersArray = (JSONArray) wholeQuestionElement.get("incorrect_answers");
						
						for(int j=0;j<wrongAnswersArray.size();j++) {
							allAnswersArray[j] = (String) wrongAnswersArray.get(j);
							allAnswersArray[j] = replaceChars(allAnswersArray[j]);
						}
						
						allAnswersArray[3] =allAnswersArray[randomIndex]; 
						allAnswersArray[randomIndex] =(String)correctAnswer;
						
						
						System.out.println(questionStatement);
						
						for(int k=0; k<allAnswersArray.length;k++) {
							System.out.println((k+1)+") "+allAnswersArray[k]);
						}
						
						System.out.print("Your answer (number): ");
						int userAnswer = scan.nextInt();
						if(userAnswer == (randomIndex+1)) {
							points += 1;
						}
						
					}
					System.out.println("--------------------------");
					System.out.println("Your score: "+points+"/10");
					
					sc.close();
					
				}
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			System.out.println("Again ?\n 1-Yes\n 2-No");
			continueSession = checker(1,2);
	}while(continueSession == 1);
		
		
		
		
		scan.close();
	}

}
