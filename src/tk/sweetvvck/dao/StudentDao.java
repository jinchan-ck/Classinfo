package tk.sweetvvck.dao;

import tk.sweetvvck.domain.Student;

public interface StudentDao {
	public boolean addStudentInfo(Student student);
	
	public boolean deleteStudentInfo(Student student);
	
	public boolean updateStudentInfo(Student student);
	
	public Student getStudentInfoByStudentName(String studentName);
	
	public Student getStudentInfoByStudentNum(String studentNum);
	
	public Student getStudentInfoByPhoneNum(String phoneNum);
}
