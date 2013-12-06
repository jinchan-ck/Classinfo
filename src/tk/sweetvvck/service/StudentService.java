package tk.sweetvvck.service;

import tk.sweetvvck.domain.Student;

public interface StudentService {
	public int validate(String studentNum, String password);
	
	public boolean addStudentInfo(Student student);
	
	public boolean deleteStudentInfo(Student student);
	
	public boolean updateStudentInfo(Student student);
	
	public Student getStudentByStudentNum(String studentNum);
	
	public Student getStudentByStudentName(String studentName);
	
	public Student getStudentByPhoneNum(String phoneNum);
	
}
