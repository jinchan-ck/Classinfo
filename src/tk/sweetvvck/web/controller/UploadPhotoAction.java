package tk.sweetvvck.web.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import tk.sweetvvck.domain.Activity;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Avatar;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.domain.Photo;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.service.ActivityService;
import tk.sweetvvck.service.AlbumService;
import tk.sweetvvck.service.AvatarService;
import tk.sweetvvck.service.HonorService;
import tk.sweetvvck.service.PhotoService;
import tk.sweetvvck.service.StudentService;
import tk.sweetvvck.service.impl.ActivityServiceImpl;
import tk.sweetvvck.service.impl.AlbumServiceImpl;
import tk.sweetvvck.service.impl.AvatarServiceImpl;
import tk.sweetvvck.service.impl.HonorServiceImpl;
import tk.sweetvvck.service.impl.PhotoServiceImpl;
import tk.sweetvvck.service.impl.StudentServiceImpl;
import tk.sweetvvck.utils.UploadConfigurationRead;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadPhotoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private File[] upload;
	private String[] uploadContentType;
	private String[] uploadFileName;
	private String[] photoTitles;
	private String[] photoDescribes;
	private List<Photo> uploadFiles = new ArrayList<Photo>();
	private PhotoService photoService = new PhotoServiceImpl();
	private AlbumService albumService = new AlbumServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	private String albumname = null;
	private String studentNum = null;
	private String studentName = null;
	private String gender = null;
	private Album album = null;
	private int albumId;
	private Student student;
	private Banji banji;
	private String phoneNum = null;
	private List<Album> albumList = new ArrayList<Album>();
	private AvatarService avatarService = new AvatarServiceImpl();
	private ActivityService activityService = new ActivityServiceImpl();
	private Avatar avatar = null;
	private String optStudentNum;
	private String competitionName;
	private String level;
	private String honorName;
	private String honorType;
	private String instructor;
	private String honorStudentName;
	private String prizeDate;
	private HonorService honorService = new HonorServiceImpl();
	private List<Honor> collectiveHonor = new ArrayList<Honor>();
	private List<Honor> personerHonor = new ArrayList<Honor>();
	private String honorId;
	private Student addStudent;
	private String mail;
	private String activityId;
	private String activityTitle;
	private String activityDate;
	private String activityAdd;
	private String activityContent;
	private List<Activity> activities;
	private String[] photoId;

	public String execute() throws Exception {
		return createOrUpdateAlbum() ? SUCCESS : INPUT;
	}

	private boolean createOrUpdateAlbum() {
		activities = activityService.getAllActivies();
		if (activityId != null && !"".equalsIgnoreCase(activityId)) {
			album = albumService.getAlbumById(Integer.parseInt(activityId));
		} else if (honorId != null && !"".equalsIgnoreCase(honorId)) {
			album = albumService.getAlbumById(Integer.parseInt(honorId));
		} else if (albumname != null && "" != albumname && "[]" != albumname
				&& !albumname.isEmpty() || honorName != null
				|| activityTitle != null) {
			album = new Album();
			if (honorName != null)
				album.setAlbumname(honorName);
			else if (activityTitle != null)
				album.setAlbumname(activityTitle);
			else
				album.setAlbumname(albumname);
		} else {
			album = albumService.getAlbumById(albumId);
		}
		banji = null;
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		Integer id = student.getAvatarId();
		if (id != null) {
			avatar = avatarService.getAvatarById(student.getAvatarId());
		} else {
			avatar = new Avatar();
		}
		avatar = avatarService.getAvatarById(id);
		banji = student.getBanji();
		album.setBanji(banji);
		try {
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath(
							File.separatorChar
									+ UploadConfigurationRead.getInstance()
											.getConfigItem("uploadFilePath")
											.trim());
			for (int i = 0; i < upload.length; i++) {
				String fileName = uploadFileName[i];
				// String type = uploadContentType[i];
				String path = UUID.randomUUID().toString() + getExt(fileName);
				String photoTitle = "";
				if (photoTitles != null) {
					photoTitle = photoTitles[i];
				}
				File target = new File(targetDirectory, path);
				FileUtils.copyFile(upload[i], target);

				Photo photo = new Photo();
				photo.setName(fileName);
				photo.setPath(path);
				photo.setPhotoTitle(photoTitle);
				if (photoDescribes != null)
					photo.setDiscription(photoDescribes[i]);
				photo.setUploadDate(new Timestamp(new Date().getTime()));

				photoService.uploadPhoto(photo);

				uploadFiles.add(photo);
				Set<Photo> photos = album.getPhotos();
				photos.addAll(uploadFiles);

				album.setPhotos(photos);
			}
			albumService.updateAlbum(album);
			albumList.addAll(banji.getAlbums());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			addActionError(e.getMessage());
			return false;
		}
		return true;
	}

	public String modifyMail() {
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		String previousMail = student.getMail();
		student.setMail(mail);

		boolean flag = studentService.updateStudentInfo(student);

		if (flag) {
		} else {
			mail = previousMail;
		}
		return "modifyMail";
	}

	public String modifyPhoneNum() {
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		String previousPhoneNum = student.getPhoneNum();
		student.setPhoneNum(phoneNum);

		boolean flag = studentService.updateStudentInfo(student);
		if (flag) {
			// phoneNum = phoneNum;
		} else {
			phoneNum = previousPhoneNum;
		}
		return "modifyPhoneNum";
	}

	public String modifyAvatar() {
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		try {
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath(
							File.separatorChar
									+ UploadConfigurationRead.getInstance()
											.getConfigItem("uploadFilePath")
											.trim());
			for (int i = 0; i < upload.length; i++) {
				String fileName = uploadFileName[i];
				String path = UUID.randomUUID().toString() + getExt(fileName);
				File target = new File(targetDirectory, path);
				FileUtils.copyFile(upload[i], target);

				Integer id = student.getAvatarId();
				if (id != null) {
					avatar = avatarService.getAvatarById(student.getAvatarId());
				} else {
					avatar = new Avatar();
				}

				avatar.setPath(path);
				avatar.setStudent(student);
				if (new Integer(1).equals(student.getAvatarId())) {
					avatarService.addAvatar(avatar);
				} else {
					avatarService.updateAvatar(avatar);
				}
				student.setAvatarId(avatar.getAvatarId());
				student.setAvatar(avatar);
				studentService.updateStudentInfo(student);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return "modifyAvatar";
	}

	public String deleteStudentInfo() {
		Student deleteStudnent = studentService
				.getStudentByStudentNum(optStudentNum);
		boolean result = false;
		if (deleteStudnent != null) {
			result = studentService.deleteStudentInfo(deleteStudnent);
		}
		if (result) {
			optStudentNum = "删除成功";
		} else {
			optStudentNum = "删除失败";
		}
		return "deleteStudentInfo";
	}

	public String addStudentInfo() {
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		banji = student.getBanji();
		addStudent = new Student();
		addStudent.setBanji(banji);
		addStudent.setGender(gender);
		addStudent.setPassword(optStudentNum);
		addStudent.setPhoneNum(phoneNum);
		addStudent.setStudentName(studentName);
		addStudent.setStudentNum(optStudentNum);
		addStudent.setIsManager(false);
		addStudent.setAvatarId(1);
		studentService.addStudentInfo(addStudent);
		return "addStudentInfo";
	}

	public String addHonors() {
		boolean uploadResult = createOrUpdateAlbum();
		if (photoId != null) {
			for (String id : photoId) {
				if (id != null && !"".equalsIgnoreCase(id)) {
					Photo photo = photoService.getPhotoById(Integer
							.parseInt(id));
					photoService.deletePhoto(photo);
				}
			}
		}
		Honor honor;
		if(honorId != null && !"".equalsIgnoreCase(honorId.trim())){
			honor = honorService.getHonorById(Integer.parseInt(honorId));
		}else if(activityId != null && !"".equalsIgnoreCase(activityId.trim())){
			honor = honorService.getHonorById(Integer.parseInt(activityId));
		}else{
			honor = new Honor();
		}
		/*if (honorId == null || "".equalsIgnoreCase(honorId))
			honor = new Honor();
		else {
			honor = honorService.getHonorById(Integer.parseInt(honorId));
		}*/
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		banji = student.getBanji();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			honor.setPrizeDate(sdf.parse(prizeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Set<Honor> honors = new HashSet<Honor>();
		if ("personer".equalsIgnoreCase(honorType)) {
			Set<Student> honorStudents = new HashSet<Student>();
			if (honorStudentName != null) {
				if (honorStudentName.contains(",")) {
					String[] studentsNames = honorStudentName.split(",");
					for (String string : studentsNames) {
						honorStudents.add(studentService
								.getStudentByStudentName(string));
					}
				} else {
					honorStudents.add(studentService
							.getStudentByStudentName(honorStudentName));
				}
			}
			honor.setBanji(banji);
			honor.setCompetitionName(competitionName);
			honor.setHonorName(honorName);
			honor.setHonorType(honorType);
			honor.setInstructor(instructor);
			honor.setLevel(level);
			honor.setStudents(honorStudents);
			if (uploadResult)
				honor.setAlbum(album);
			honorService.addHonor(honor);
			honors.add(honor);
			banji.setHonors(honors);
			for (Student student : honorStudents) {
				student.setHonors(honors);
				student.setBanji(banji);
				studentService.updateStudentInfo(student);
			}
		} else {
			honor.setBanji(banji);
			honor.setCompetitionName(competitionName);
			honor.setHonorName(honorName);
			honor.setHonorType(honorType);
			honor.setInstructor(instructor);
			honor.setLevel(level);
			honor.setStudents(banji.getStudents());
			if (uploadResult)
				honor.setAlbum(album);
			honorService.addHonor(honor);
			honors.add(honor);
			banji.setHonors(honors);
			for (Student honorStudent : banji.getStudents()) {
				honorStudent.setHonors(honors);
				honorStudent.setBanji(banji);
				studentService.updateStudentInfo(honorStudent);
			}
		}
		activities = activityService.getAllActivies();
		student = studentService.getStudentByStudentNum(studentNum);
		banji = student.getBanji();
		Set<Honor> honors1 = banji.getHonors();
		for (Honor honor1 : honors1) {
			if ("personer".equalsIgnoreCase(honor1.getHonorType())) {
				personerHonor.add(honor1);
			} else {
				collectiveHonor.add(honor1);
			}
		}
		albumList.removeAll(albumList);
		albumList.addAll(banji.getAlbums());
		return SUCCESS;
	}

	public String addActivity() {
		boolean uploadResult = createOrUpdateAlbum();
		Activity activity = null;
		if (photoId != null) {
			for (String id : photoId) {
				if (id != null && !"".equalsIgnoreCase(id)) {
					Photo photo = photoService.getPhotoById(Integer
							.parseInt(id));
					photoService.deletePhoto(photo);
				}
			}
		}
		if (activityId == null || "".equalsIgnoreCase(activityId))
			activity = new Activity();
		else {
			activity = activityService.getActivityById(Integer
					.parseInt(activityId));
		}
		student = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (student == null)
			student = studentService.getStudentByStudentNum(studentNum);
		banji = student.getBanji();
		activity.setBanji(banji);
		activity.setActivityAdd(activityAdd);
		activity.setActivityContent(activityContent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			activity.setActivityDate(sdf.parse(activityDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		activity.setActivityTitle(activityTitle);
		if (uploadResult)
			activity.setActivityAlbum(album);
		activityService.addActivity(activity);
		activities = activityService.getAllActivies();
		student = studentService.getStudentByStudentNum(studentNum);
		banji = student.getBanji();
		Set<Honor> honors1 = banji.getHonors();
		for (Honor honor1 : honors1) {
			if ("personer".equalsIgnoreCase(honor1.getHonorType())) {
				System.out.println(honor1.getStudents().iterator().next()
						.getStudentName());
				personerHonor.add(honor1);
			} else {
				collectiveHonor.add(honor1);
			}
		}
		albumList.removeAll(albumList);
		albumList.addAll(banji.getAlbums());
		return SUCCESS;
	}

	public String deleteHonors() {
		Honor honor = honorService.getHonorById(Integer.parseInt(honorId));
		Album album = honor.getAlbum();
		albumService.deleteAlbum(album);
		activities = activityService.getAllActivies();
		student = studentService.getStudentByStudentNum(studentNum);
		avatar = avatarService.getAvatarById(student.getAvatarId());
		banji = student.getBanji();
		Set<Honor> honors1 = banji.getHonors();
		for (Honor honor1 : honors1) {
			if ("personer".equalsIgnoreCase(honor1.getHonorType())) {
				System.out.println(honor1.getStudents().iterator().next()
						.getStudentName());
				personerHonor.add(honor1);
			} else {
				collectiveHonor.add(honor1);
			}
		}
		albumList.removeAll(albumList);
		albumList.addAll(banji.getAlbums());
		return SUCCESS;
	}

	public String deleteActivity() {
		Activity activity = activityService.getActivityById(Integer
				.parseInt(activityId));
		Album album = activity.getActivityAlbum();
		albumService.deleteAlbum(album);
		activities = activityService.getAllActivies();
		student = studentService.getStudentByStudentNum(studentNum);
		avatar = avatarService.getAvatarById(student.getAvatarId());
		banji = student.getBanji();
		Set<Honor> honors1 = banji.getHonors();
		for (Honor honor1 : honors1) {
			if ("personer".equalsIgnoreCase(honor1.getHonorType())) {
				System.out.println(honor1.getStudents().iterator().next()
						.getStudentName());
				personerHonor.add(honor1);
			} else {
				collectiveHonor.add(honor1);
			}
		}
		albumList.removeAll(albumList);
		albumList.addAll(banji.getAlbums());
		return SUCCESS;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public List<Photo> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<Photo> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String[] getPhotoTitles() {
		return photoTitles;
	}

	public void setPhotoTitles(String[] photoTitles) {
		this.photoTitles = photoTitles;
	}

	public String[] getPhotoDescribes() {
		return photoDescribes;
	}

	public void setPhotoDescribes(String[] photoDescribes) {
		this.photoDescribes = photoDescribes;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public String getOptStudentNum() {
		return optStudentNum;
	}

	public void setOptStudentNum(String optStudentNum) {
		this.optStudentNum = optStudentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHonorName() {
		return honorName;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public String getHonorType() {
		return honorType;
	}

	public void setHonorType(String honorType) {
		this.honorType = honorType;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getHonorStudentName() {
		return honorStudentName;
	}

	public void setHonorStudentName(String honorStudentName) {
		this.honorStudentName = honorStudentName;
	}

	public String getPrizeDate() {
		return prizeDate;
	}

	public void setPrizeDate(String prizeDate) {
		this.prizeDate = prizeDate;
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

	public String getHonorId() {
		return honorId;
	}

	public void setHonorId(String honorId) {
		this.honorId = honorId;
	}

	public Student getAddStudent() {
		return addStudent;
	}

	public void setAddStudent(Student addStudent) {
		this.addStudent = addStudent;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityAdd() {
		return activityAdd;
	}

	public void setActivityAdd(String activityAdd) {
		this.activityAdd = activityAdd;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String[] getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String[] photoId) {
		this.photoId = photoId;
	}
}
