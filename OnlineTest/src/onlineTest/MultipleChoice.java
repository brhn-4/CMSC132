package onlineTest;

import java.io.Serializable;
import java.util.HashMap;


public class MultipleChoice extends Question implements Serializable
{
	private static final long serialVersionUID = 1L;
	String[] answer;
	HashMap<String,String[]> studentAnswers;
	
	public MultipleChoice(String text, double points, String [] answer)
	{
		super(text, points);
		this.answer = answer;
		studentAnswers = new HashMap<String,String[]>();
	}
	
	public String[] getAnswer()
	{
		return answer;
	}
	
	public void setStudentAnswer(String id, String[] a)
	{
		studentAnswers.put(id, a);
	}
	
	public double score(String currStud, Question q)
	{
		String[] correctAnswer = ((MultipleChoice) q).getAnswer();
		
		if(correctAnswer.length == studentAnswers.get(currStud).length)
		{
			for(int x = 0; x < studentAnswers.get(currStud).length ; x++)
			{
				if(!(correctAnswer[x].equals(studentAnswers.get(currStud)[x])))
				{
					return 0;
				}
			}
		}
		
		else if(correctAnswer.length > studentAnswers.get(currStud).length)
		{
			return 0;
		}
		
		else if(correctAnswer.length < studentAnswers.get(currStud).length)
		{
			return 0;
		}
		
		return points;
		
		
	}
}
