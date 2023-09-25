package onlineTest;

import java.io.Serializable;
import java.util.HashMap;

public class TrueFalse extends Question implements Serializable
{
	private static final long serialVersionUID = 1L;
	boolean answer;
	HashMap<String, Boolean> studentAnswers;
	
	public TrueFalse(String text, double points, boolean answer)
	{
		super(text, points);
		this.answer = answer;
		studentAnswers = new HashMap<String,Boolean>(); 
	}
	
	public boolean getAnswer()
	{
		return answer;
	}
	
	public static String toString(boolean ans)
	{
		if(ans)
		{
			return "True";
		}
		
		return "False";
	}
	
	public void setStudentAnswer(String id, boolean a)
	{
		studentAnswers.put(id, a);
	}
	
	public double score(String currStudent, Question q)
	{
		boolean correctAnswer = ((TrueFalse) q).getAnswer();
		
				
		if(correctAnswer == studentAnswers.get(currStudent))
		{
			return q.points;
		}
	
		return 0;
	}
}
