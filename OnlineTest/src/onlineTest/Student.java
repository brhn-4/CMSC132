package onlineTest;

import java.io.Serializable;
import java.util.Map;

public class Student implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Map<Integer,Exam> examsTaken = null;
	
	public Student(Map<Integer,Exam> examsTaken)
	{
		
		this.examsTaken = examsTaken;
	}

}
