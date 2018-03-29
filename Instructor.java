import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Instructor {
	HashMap<String, Question> courses = new HashMap<String, Question>();
	public Instructor(){
		
	}
	public void createCourse(String courseCode){
		Course newCourse = new Course();
		newCourse.createQuestionAnswers(courseCode);
	}
	public void createSession(){

	}
	public void addQuestionAnswers(){
		
	}
}
