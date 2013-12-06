package tk.sweetvvck.service.impl;

import tk.sweetvvck.dao.StudentDao;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.service.StudentService;
import tk.sweetvvck.utils.DaoFactory;

public class StudentServiceImpl implements StudentService {
	public static final int LOGIN_SUCCESS = 1;
	public static final int STUDENT_NOT_EXIT = 0;
	public static final int PASSWORD_ERROR = -1;
	
	public static Student student = null;
	
	private StudentDao studentDao = DaoFactory.getInstance().getStudentDao();
	@Override
	public int validate(String studentNum, String password) {
		Student student = studentDao.getStudentInfoByStudentNum(studentNum);
		if(student != null){
			if(student.getPassword().equals(password)){
				StudentServiceImpl.student = student;
				return LOGIN_SUCCESS;
			}else{
				return PASSWORD_ERROR;
			}
		}else{
			return STUDENT_NOT_EXIT;
		}
		
	}
	
	public static Student getStudent(){
		return student;
	}

	@Override
	public boolean addStudentInfo(Student student) {
		return studentDao.addStudentInfo(student);
	}

	@Override
	public boolean deleteStudentInfo(Student student) {
		return studentDao.deleteStudentInfo(student);
	}

	@Override
	public boolean updateStudentInfo(Student student) {
		return studentDao.updateStudentInfo(student);
	}

	@Override
	public Student getStudentByStudentNum(String studentNum) {
		return studentDao.getStudentInfoByStudentNum(studentNum);
	}

	@Override
	public Student getStudentByStudentName(String studentName) {
		return studentDao.getStudentInfoByStudentName(studentName);
	}

	@Override
	public Student getStudentByPhoneNum(String phoneNum) {
		return null;
	}

}
