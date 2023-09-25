package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SystemManager implements Manager, Serializable
{
	private static final long serialVersionUID = 1L;
	public HashMap<Integer,Exam> exams = new HashMap<Integer,Exam>();
	public HashMap<String,Student> students = new HashMap<String,Student>();
	public HashMap<Double,String> cutOffs = new HashMap<Double,String>();
	/**
	 * Adds the specified exam to the database.
	 * @param examId
	 * @param title
	 * @return false is exam already exists.
	 */
	public boolean addExam(int examId, String title)
	{
		if(exams.containsKey(examId))
		{
			return false;
		}
		
		HashMap<Integer,Question> questions = new HashMap<Integer,Question>();
		Exam exam = new Exam(title,questions);
		exams.put(examId,exam);
		
		return true;
	}
	
	
	/**
	 * Adds a true and false question to the specified exam.  If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer)
	{
		TrueFalse question = new TrueFalse(text, points, answer);
		exams.get(examId).questions.put(questionNumber, question);
	}
	
	
	/**
	 * Adds a multiple choice question to the specified exam.   If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		MultipleChoice question = new MultipleChoice(text, points, answer);
		exams.get(examId).questions.put(questionNumber, question);
	}
	
	
	/**
	 * Adds a fill-in-the-blanks question to the specified exam.  If the question
	 * already exits it is overwritten.  Each correct response is worth
	 * points/entries in the answer.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		FillInTheBlank question = new FillInTheBlank(text, points, answer);
		exams.get(examId).questions.put(questionNumber, question);
	}
	
	
	/**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br /> 
	 *    a. True or false question: "True" or "False"<br />
	 *    b. Multiple choice question: [ ] enclosing the answer (each entry separated by commas) and in
	 *       sorted order. <br />
	 *    c. Fill in the blanks question: [ ] enclosing the answer (each entry separated by commas) and
	 *       in sorted order. <br />
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 **/
	public String getKey(int examId)
	{
		Exam examTaken = exams.get(examId);
		String ans = "";
		
		if(!exams.containsKey(examId))
		{
			return "Exam not found";
		}
		
		for(Integer questionNumbers: examTaken.questions.keySet())
		{
			Question currQuestion = examTaken.questions.get(questionNumbers);
			
			ans += "Question Text: " + currQuestion.text + "\n";
			ans += "Points: " + currQuestion.points + "\n";
			ans += "Correct Answer: ";
			
			if(currQuestion instanceof TrueFalse)
			{
				
				ans += TrueFalse.toString(((TrueFalse) currQuestion).getAnswer()) + "\n";
			}
			
			else if(currQuestion instanceof MultipleChoice)
			{
				ans += "[";
				int endIndex = ((MultipleChoice) currQuestion).getAnswer().length-1;
				
				for(int x = 0; x < ((MultipleChoice) currQuestion).getAnswer().length-1; x++)
				{
					ans += ((MultipleChoice)currQuestion).getAnswer()[x] + ", ";
				}
				
				ans += ((MultipleChoice)currQuestion).getAnswer()[endIndex] +"]\n";
			}
			
			else
			{
				ans += "[";
				int endIndex = ((FillInTheBlank) currQuestion).getAnswer().length-1;
				
				for(int x = 0; x < ((FillInTheBlank) currQuestion).getAnswer().length-1; x++)
				{
					ans += ((FillInTheBlank)currQuestion).getAnswer()[x] + ", ";
				}
				
				ans += ((FillInTheBlank)currQuestion).getAnswer()[endIndex] +"]\n";
			}
		}
		
		return ans;	
	}
	
	
	/**
	 * Adds the specified student to the database.  Names are specified in the format
	 * LastName,FirstName
	 * @param name
	 * @return false if student already exists.
	 */
	public boolean addStudent(String name)
	{
		HashMap<Integer,Exam> examsTaken = new HashMap<Integer,Exam>();
		Student student = new Student(examsTaken);
		
		if(students.containsKey(name))
		{
			return false;
		}
		
		students.put(name,student);
		
		return true;
	}
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer)
	{
		Exam exam = exams.get(examId);
		students.get(studentName).examsTaken.put(examId, exam);
		
		((TrueFalse)(students.get(studentName).examsTaken.get(examId).questions.get(questionNumber))).setStudentAnswer(studentName,answer);
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer)
	{
		Exam exam = exams.get(examId);
		students.get(studentName).examsTaken.put(examId, exam);
		
		((MultipleChoice)(exam.questions.get(questionNumber))).setStudentAnswer(studentName,answer);
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 **/
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer)
	{
		Exam exam = exams.get(examId);
		students.get(studentName).examsTaken.put(examId, exam);
		
		((FillInTheBlank)(students.get(studentName).examsTaken.get(examId).questions.get(questionNumber))).setStudentAnswer(studentName,answer);
	}
	

	/**
	 * Returns the score the student got for the specified exam.
	 * @param studentName
	 * @param examId
	 * @return score
	 **/
	public double getExamScore(String studentName, int examId)
	{
		
		double score = 0;
		Exam answerSheet = exams.get(examId);
		
		if(answerSheet == null)
		{
			throw new IllegalArgumentException("Exam doesn't exist");
		}
		
		Map<Integer, Question> correctAnswers = answerSheet.questions;
		
		Student studentTakingExam = students.get(studentName);
		if(studentTakingExam == null)
		{
			throw new IllegalArgumentException("Student doesn't exist");
		}
		
		Exam studentExam = studentTakingExam.examsTaken.get(examId);
		
		if(studentExam == null)
		{
			throw new IllegalArgumentException("Student hasn't taken exam");
		}
		
		
		for(Integer questionNumbers: studentExam.questions.keySet())
		{
			if(studentExam.questions.get(questionNumbers) instanceof MultipleChoice)
			{
				score += studentExam.questions.get(questionNumbers).score(studentName,(MultipleChoice)(correctAnswers.get(questionNumbers)));
			}
			
			else if(studentExam.questions.get(questionNumbers) instanceof FillInTheBlank)
			{
				score += studentExam.questions.get(questionNumbers).score(studentName,(FillInTheBlank)(correctAnswers.get(questionNumbers)));
			}
			
			else
			{
				score += studentExam.questions.get(questionNumbers).score(studentName,(TrueFalse)(correctAnswers.get(questionNumbers)));
			}
		}
        
		return score;
		
	}
	
	
	/**
	 * Generates a grading report for the specified exam.  The report will include
	 * the following information for each exam question:<br />
	 * "Question #" {questionNumber} {questionScore} " points out of " {totalQuestionPoints}<br />
	 * The report will end with the following information:<br />
	 * "Final Score: " {score} " out of " {totalExamPoints};  
	 * @param studentName
	 * @param examId
	 * @return report
	 */
	public String getGradingReport(String studentName, int examId)
	{
		String report = "";
		Exam examTaken = students.get(studentName).examsTaken.get(examId);
		Exam answerSheet = exams.get(examId);
		double totalScore = 0;
		double totalPoints = 0;
		
		if(studentName == null || students.get(studentName).examsTaken.get(examId) == null)
		{
			return report;
		}
		
		for(Integer questionNumbers : examTaken.questions.keySet())
		{
			report += "Question #" + questionNumbers + " " + examTaken.questions.get(questionNumbers).score(studentName,answerSheet.questions.get(questionNumbers));
			report += " points out of " + examTaken.questions.get(questionNumbers).points + "\n";
			totalScore += examTaken.questions.get(questionNumbers).score(studentName,answerSheet.questions.get(questionNumbers));
			totalPoints += examTaken.questions.get(questionNumbers).points;
		}
		
		report += "Final Score: " + totalScore + " out of " + totalPoints;
		
		return report;
	}
	
	
	/**
	 * Sets the cutoffs for letter grades.  For example, a typical curve we will have
	 * new String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.  Anyone with a 90 or
	 * above gets an A, anyone with an 80 or above gets a B, etc.  Notice we can have different
	 * letter grades and cutoffs (not just the typical curve).
	 * @param letterGrades
	 * @param cutoffs
	 */
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs)
	{	
		
		
		for(int x = 0; x < cutoffs.length; x++)
		{
			cutOffs.put(cutoffs[x],letterGrades[x]);
		}
	}
	
	
	/**
	 * Computes a numeric grade (value between 0 and a 100) for the student taking
	 * into consideration all the exams.  All exams have the same weight. 
	 * @param studentName
	 * @return grade
	 */
	public double getCourseNumericGrade(String studentName)
	{
		double examCount = 0;
		double totalGrades = 0;
		Student currStudent= students.get(studentName);
		
		
		for(Integer examNum: currStudent.examsTaken.keySet())
		{
			double score = getExamScore(studentName,examNum);
			double totalPoints = 0;
			
			for(Integer questionNumbers : currStudent.examsTaken.get(examNum).questions.keySet())
			{
				totalPoints += currStudent.examsTaken.get(examNum).questions.get(questionNumbers).points;
			}
			
			totalGrades += score/totalPoints;
			examCount++;
			
		}
		
		return totalGrades/examCount * 100;
	}
	
	
	/** 
	 * Computes a letter grade based on cutoffs provided.  It is assumed the cutoffs have
	 * been set before the method is called.
	 * @param studentName
	 * @return letter grade
	 */
	public String getCourseLetterGrade(String studentName)
	{
		double score = getCourseNumericGrade(studentName);
		Double lastCutOff = 0.0;
		String letter;
		ArrayList <Double> sortKeys = new ArrayList<Double>();
		
		for(Double key: cutOffs.keySet())
		{
			sortKeys.add(key);		
		}
		
		Collections.sort(sortKeys);
		Collections.reverse(sortKeys);
		
		for(Double cutOff : sortKeys)
		{
			lastCutOff = cutOff;
			
			if(score >= cutOff)
			{
				letter = cutOffs.get(cutOff);
				return letter;
			}
		}
		
		return cutOffs.get(lastCutOff);
	}
	
	
	/**
	 * Returns a listing with the grades for each student.  For each student the report will
	 * include the following information: <br />
	 * {studentName} {courseNumericGrade} {courseLetterGrade}<br />
	 * The names will appear in sorted order.
	 * @return grades
	 */
	public String getCourseGrades()
	{
		String ans = "";
		int x = 0;
		for(String studentName: students.keySet())
		{
			x++;
		}
		
		String[] sortNames = new String[x];
		int y = 0;
		
		for(String studentName: students.keySet())
		{
			sortNames[y] = studentName;
			y++;
			
		}

		Arrays.sort(sortNames);
		
		for(String studentName: sortNames)
		{
	
			ans += studentName + " " + getCourseNumericGrade(studentName) + " " + getCourseLetterGrade(studentName);
			ans += "\n";
		}
		
		return ans;
	}
	
	
	/**
	 * Returns the maximum score (among all the students) for the specified exam.
	 * @param examId
	 * @return maximum
	 */
	public double getMaxScore(int examId)
	{
		double max = 0;
		Exam examTaken = null;
		Exam answerSheet = exams.get(examId);
		
		for(String studentName : students.keySet())
		{
			if(students.get(studentName).examsTaken.containsKey(examId))
			{
				examTaken = students.get(studentName).examsTaken.get(examId);
				double studentScore = 0;
				
				for(Integer questionNumbers : examTaken.questions.keySet())
				{
					studentScore += examTaken.questions.get(questionNumbers).score(studentName,answerSheet.questions.get(questionNumbers));
				}
				
				if(studentScore > max)
				{
					max = studentScore;
				}
			}
		}
		
		return max;
	}
	
	
	/**
	 * Returns the minimum score (among all the students) for the specified exam.
	 * @param examId
	 * @return minimum
	 */
	public double getMinScore(int examId)
	{
		double min = 99999999;
		Exam examTaken = null;
		Exam answerSheet = exams.get(examId);
		
		for(String studentName : students.keySet())
		{
			if(students.get(studentName).examsTaken.containsKey(examId))
			{
				examTaken = students.get(studentName).examsTaken.get(examId);
				double studentScore = 0;
				
				for(Integer questionNumbers : examTaken.questions.keySet())
				{
					studentScore += examTaken.questions.get(questionNumbers).score(studentName,answerSheet.questions.get(questionNumbers));
				}
				
				if(studentScore < min)
				{
					min = studentScore;
				}
			}
		}
		
		return min;		
	}
	
	
	/**
	 * Returns the average score for the specified exam.
	 * @param examId
	 * @return average
	 */
	public double getAverageScore(int examId) 
	{
		double avg = 0;
		double totalStudentScore = 0;
		double studentCount = 0;
		Exam examTaken = null;
		Exam answerSheet = exams.get(examId);
		
		for(String studentName : students.keySet())
		{
			if(students.get(studentName).examsTaken.containsKey(examId))
			{
				studentCount++;
				examTaken = students.get(studentName).examsTaken.get(examId);
				
				for(Integer questionNumbers : examTaken.questions.keySet())
				{
					totalStudentScore += examTaken.questions.get(questionNumbers).score(studentName,answerSheet.questions.get(questionNumbers));
				}
			}
		}
		
		avg = totalStudentScore / studentCount;
		return avg;	
	}
	
	
	/**
	 * It will serialize the Manager object and store it in the
	 * specified file.
	 */
	public void saveManager(Manager manager, String fileName)
	{
		SystemManager newManager = (SystemManager) manager;
		File file = new File(fileName);
		try {
	
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

		output.writeObject(newManager);
		output.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * It will return a Manager object based on the serialized data
	 * found in the specified file.
	 */
	
	public Manager restoreManager(String fileName)
	{
		File file = new File(fileName);
		SystemManager manager = null;
		
		if (!file.exists()) {
			return new SystemManager();
		}
		else {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
				manager = (SystemManager) input.readObject();
				input.close();	
				return manager;
				}
			catch(IOException e)
				{
				e.printStackTrace();
				} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
		return new SystemManager();
	}
}

