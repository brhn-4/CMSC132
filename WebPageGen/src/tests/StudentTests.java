package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.TableElement;
import model.TagElement;
import model.TextElement;

public class StudentTests {
	
	public static final String TESTS_TAG = "\n\nEndTest";
	
	@Test
	public void studTableTest1() {
		int indentation = 2;
		String attributes = "border=\"5\"", answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		TableElement tableElement = new TableElement(12, 3, attributes);
		tableElement.addItem(0, 0, new TextElement("John"));
		tableElement.addItem(10, 2, new TextElement("Laura"));
		tableElement.addItem(7, 2, new TextElement("Rose"));
		
		answer += tableElement.genHTML(indentation);
		answer += TESTS_TAG;
		System.out.println(answer);
		assertTrue(TestsSupport.isCorrect("studTableTest1.txt", answer));
	}
}
