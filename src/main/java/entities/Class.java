package entities;
import java.time.LocalTime;
import java.util.LinkedList;

public class Class {

	private int id;
	private String day;
	private LocalTime beginHour;
	private LocalTime endHour;
	private People teacher;
	private Activity activity;
	private LinkedList<People> attendants;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public LocalTime getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(LocalTime beginHour) {
		this.beginHour = beginHour;
	}
	public LocalTime getEndHour() {
		return endHour;
	}
	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}
	public People getTeacher() {
		return teacher;
	}
	public void setTeacher(People teacher) {
		this.teacher = teacher;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {

		this.activity = activity;
	}
	public LinkedList<People> getAttendants() {
		return attendants;
	}
	public void setAttendants(LinkedList<People> attendants) {
		this.attendants = attendants;
	}

	
	
}
