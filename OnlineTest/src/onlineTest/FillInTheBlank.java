package onlineTest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class FillInTheBlank extends Question implements Serializable
{
	private static final long serialVersionUID = 1L;
	String[] answer;
	HashMap<String,String[]> studentAnswers;
	
	public FillInTheBlank(String text, double points, String [] answer)
	{
		super(text, points);
		this.answer = answer;
		Arrays.sort(this.answer);
		studentAnswers = new HashMap<String,String[]>();

	}
	
	public String[] getAnswer()
	{
		return answer;
	}
	
	public void setStudentAnswer(String id, String[] a)
	{
		Arrays.sort(a);
		studentAnswers.put(id, a);
	}
	
	public double score(String currStud, Question q)
	{
		String[] correctAnswer = ((FillInTheBlank) q).getAnswer();
		double score = 0;
		
		
			for(int x = 0; x < studentAnswers.get(currStud).length ; x++)
			{
				for(int y = 0; y < correctAnswer.length; y++)
				{
					if((correctAnswer[y].equals(studentAnswers.get(currStud)[x])))
					{
						score++;
					}
				}
			}
				
		
		return (score/correctAnswer.length)*points;
		
	}
}
