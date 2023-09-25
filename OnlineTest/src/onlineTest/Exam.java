package onlineTest;

import java.io.Serializable;
import java.util.Map;


public class Exam implements Serializable
{	
	private static final long serialVersionUID = 1L;
	public String title;
	public Map<Integer,Question> questions;
	
	public Exam( String title, Map<Integer,Question> questions)
	{
	
		this.title = title;
		this.questions = questions;	
	}
	
	

}
