package tk.sweetvvck.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tk.sweetvvck.domain.Activity;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Avatar;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.service.AvatarService;
import tk.sweetvvck.service.BanjiService;
import tk.sweetvvck.service.StudentService;
import tk.sweetvvck.service.impl.AvatarServiceImpl;
import tk.sweetvvck.service.impl.BanjiServiceImpl;
import tk.sweetvvck.service.impl.StudentServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String userType = null;
	private String studentNum = null;
	private String password = null;
	private String message = null;
	private Student student = null;
	private Banji banji = null;
	private int banjiId = 0;
	private Avatar avatar = null;
	private List<Album> albumList = new ArrayList<Album>();

	private StudentService studentService = new StudentServiceImpl();
	private BanjiService banjiService = new BanjiServiceImpl();
	private AvatarService avatarService = new AvatarServiceImpl();
	private List<Honor> collectiveHonor = new ArrayList<Honor>();
	private List<Honor> personerHonor = new ArrayList<Honor>();
	private List<Banji> banjiList = null;
	private List<Activity> activities = new ArrayList<Activity>();

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		banjiList = (List<Banji>) ActionContext.getContext().getSession().get("banjiList");
		if(banjiList == null){
			banjiList = banjiService.getAllBanji(); 
		}
		System.out.println("stuentNum----->" + studentNum);
		System.out.println("password----->" + password + userType);
		String result = SUCCESS;
		if("host".equals(userType)){
			student = (Student) ActionContext.getContext().getSession().get("student");
			if(student == null)		
				student = studentService.getStudentByStudentNum(studentNum);
			Integer id = student.getAvatarId();
			if(id != null)
				avatar = avatarService.getAvatarById(student.getAvatarId());
			banji = student.getBanji();
			Set<Album> albumSet = banji.getAlbums();
			albumList.addAll(albumSet);
			Set<Honor> honors = banji.getHonors();
			activities.addAll(banji.getActivities());
			for (Honor honor : honors) {
				if("personer".equalsIgnoreCase(honor.getHonorType())){
					personerHonor.add(honor);
				}else{
					collectiveHonor.add(honor);
				}
			}
			message = "登录成功";
			result = SUCCESS;
		}else{
			result = SUCCESS;
			banji = banjiList.get(banjiId-1);
			if(banji == null)
				banji = banjiService.getBanjiById(banjiId);
			Set<Album> albumSet = banji.getAlbums();
			Set<Honor> honors = banji.getHonors();
			for (Honor honor : honors) {
				if("personer".equalsIgnoreCase(honor.getHonorType())){
					personerHonor.add(honor);
				}else{
					collectiveHonor.add(honor);
				}
			}
			activities.addAll(banji.getActivities());
			for (Honor honor : honors) {
				if("personer".equalsIgnoreCase(honor.getHonorType())){
					personerHonor.add(honor);
				}else{
					collectiveHonor.add(honor);
				}
			}
			albumList.addAll(albumSet);
			studentNum = null;
			password = null;
		}
		return result;
	}

	public String validateStudent(){
		String result = "";
		int state = studentService.validate(studentNum, password);
		switch (state) {
		case StudentServiceImpl.LOGIN_SUCCESS:
			result = "pass";
			ActionContext.getContext().getSession().put("student", StudentServiceImpl.getStudent());
			message = "验证成功";
			break;
		case StudentServiceImpl.PASSWORD_ERROR:
			result = ERROR;
			message = "密码错误";
			break;
		case StudentServiceImpl.STUDENT_NOT_EXIT:
			result = ERROR;
			message = "用户名不存在";
			break;
		default:
			result = ERROR;
			message = "未知错误！！";
			break;
		}
		return result;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Banji getBanji() {
		return banji;
	}

	public void setBanji(Banji banji) {
		this.banji = banji;
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

	public int getBanjiId() {
		return banjiId;
	}

	public void setBanjiId(int banjiId) {
		this.banjiId = banjiId;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public List<Honor> getCollectiveHonor() {
		return collectiveHonor;
	}

	public void setCollectiveHonor(List<Honor> collectiveHonor) {
		this.collectiveHonor = collectiveHonor;
	}

	public List<Honor> getPersonerHonor() {
		return personerHonor;
	}

	public void setPersonerHonor(List<Honor> personerHonor) {
		this.personerHonor = personerHonor;
	}

	public List<Banji> getBanjiList() {
		return banjiList;
	}

	public void setBanjiList(List<Banji> banjiList) {
		this.banjiList = banjiList;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
