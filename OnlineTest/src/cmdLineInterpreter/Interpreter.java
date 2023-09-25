package cmdLineInterpreter;
import java.util.Scanner;

import onlineTest.SystemManager; 
/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter  {
	
	

	public static void main(String[] args)
	{
		SystemManager manager = new SystemManager();
		String instructions = "ENTER 1 TO ADD A STUDENT\nENTER 2 TO ADD AN EXAM\nENTER 3 TO ADD A TRUE/FALSE QUESTION\nENTER 4 TO ANSWER A TRUE/FALSE QUESTION\nENTER 5 TO GET THE EXAM SCORE FOR A STUDENT\nENTER 6 TO END PROGRAM";
		
		
		System.out.println(instructions);
		Scanner scanner = new Scanner(System.in);
		int command = scanner.nextInt();
		
		while(command != 6)
		{
			if(command == 1)
			{
				String name;
				System.out.println("What is the student's name?");
				scanner.nextLine();
				name = scanner.nextLine();
				if(manager.addStudent(name))
				{
					System.out.println("Done!");
				}
				else
				{
					System.out.println("Student already added");
				}
				System.out.println(instructions);
				command = scanner.nextInt();
			}
			if(command == 2)
			{
				int examId; String name;
				System.out.println("What is the exam ID?");
				examId = scanner.nextInt();
				System.out.println("What is the exam title?");
				scanner.nextLine();
				name = scanner.nextLine();
				if(manager.addExam(examId, name))
				{
					manager.addExam(examId, name);
					System.out.println("Done!");
				}
				else
				{
					System.out.println("Exam already added");
				}
				System.out.println(instructions);
				command = scanner.nextInt();
			}
			if(command == 3)
			{
				int examId; int questionNumber; String text; double points; boolean answer;
				System.out.println("Which exam should this question be added to? (exam ID)");
				examId = scanner.nextInt();
				System.out.println("What is the question number?");
				questionNumber = scanner.nextInt();
				System.out.println("What does the question say?");
				scanner.nextLine();
				text = scanner.nextLine();
				System.out.println("What is the question worth?");
				points = scanner.nextDouble();
				System.out.println("What is the answer?");
				answer = scanner.nextBoolean();
				if(!manager.exams.containsKey(examId))
				{
					System.out.println("Exam does not exist");
				}
				else
				{
					manager.addTrueFalseQuestion(examId, questionNumber, text, points, answer);
					System.out.println("Done!");
				}
				System.out.println(instructions);
				command = scanner.nextInt();
			}
			if(command == 4)
			{
				String studentName; int examId; int questionNumber; boolean answer;
				System.out.println("Which exam is this question on?");
				examId = scanner.nextInt();
				System.out.println("What is the question number?");
				questionNumber = scanner.nextInt();
				System.out.println("Who is answering the question?");
				scanner.nextLine();
				studentName = scanner.nextLine();
				System.out.println("What is the answer?");
				answer = scanner.nextBoolean();
				
				if(!manager.exams.containsKey(examId))
				{
					System.out.println("Exam does not exist");
				}
				else if(!manager.students.containsKey(studentName))
				{
					System.out.println("Student does not exist");
				}
				else if(!manager.exams.get(examId).questions.containsKey(questionNumber))
				{
					System.out.println("Question does not exist");
				}
				else
				{
					manager.answerTrueFalseQuestion(studentName, examId, questionNumber, answer);
					System.out.println("Done!");
				}
				System.out.println(instructions);
				command = scanner.nextInt();
			}
			if(command == 5)
			{
				String studentName; int examId;
				System.out.println("Which exam do you want to grade?");
				examId = scanner.nextInt();
				System.out.println("Who's exam do you want to grade?");
				scanner.nextLine();
				studentName = scanner.nextLine();
				if(!manager.exams.containsKey(examId))
				{
					System.out.println("Exam does not exist");
				}
				else if(!manager.students.containsKey(studentName))
				{
					System.out.println("Student does not exist");
				}
				else if(!manager.students.get(studentName).examsTaken.containsKey(examId))
				{
					System.out.println("Student has not taken exam");
				}
				else
				{
					System.out.println("Score: " + manager.getExamScore(studentName, examId));
					System.out.println("Done!");
				}
				System.out.println(instructions);
				command = scanner.nextInt();	
			}
			
		}
		scanner.close();
	}
}
