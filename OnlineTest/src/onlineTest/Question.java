package onlineTest;
import java.io.Serializable;

public class Question implements Serializable
{
	private static final long serialVersionUID = 1L;
	int examId;
	int questionNumber;
	String text;
	double points;
	
	
	public Question( String text, double points)
	{
		this.text = text;
		this.points = points;
	}
	
	public double score(String currStud, Question q)
	{
		return 0;
	}
}
