package tk.sweetvvck.web.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tk.sweetvvck.dao.impl.BaseDaoHibernateImpl;
import tk.sweetvvck.domain.Activity;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.domain.Photo;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.domain.SystemAdmin;
import tk.sweetvvck.service.ActivityService;
import tk.sweetvvck.service.AlbumService;
import tk.sweetvvck.service.BanjiService;
import tk.sweetvvck.service.HonorService;
import tk.sweetvvck.service.StudentService;
import tk.sweetvvck.service.impl.ActivityServiceImpl;
import tk.sweetvvck.service.impl.AlbumServiceImpl;
import tk.sweetvvck.service.impl.BanjiServiceImpl;
import tk.sweetvvck.service.impl.HonorServiceImpl;
import tk.sweetvvck.service.impl.StudentServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RedirectAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<Photo> photoList = new ArrayList<Photo>();
	private List<Album> albumList = null;
	//private PhotoService photoService = new PhotoServiceImpl();
	private AlbumService albumService = new AlbumServiceImpl();
	private BanjiService banjiService = new BanjiServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	private HonorService honorService= new HonorServiceImpl();
	private ActivityService activityService = new ActivityServiceImpl();
	private List<Banji> banjiList = null;
	private Photo showAlbum = null;
	private int albumId = 0;
	private String[] item;
	private String[] positions;
	private List<SystemAdmin> systemAdmins = null;
	private List<Honor> honors = null;
	
	public String toUploadView() {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		if(ActionContext.getContext().getSession().get("banjiList") != null)
			banjiList = (List<Banji>) ActionContext.getContext().getSession().get("banjiList");
		else
			banjiList = banjiService.getAllBanji();
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String sysLogin() throws Exception {
		honors = new ArrayList<Honor>();
		if(ActionContext.getContext().getSession().get("banjiList") != null)
			banjiList = (List<Banji>) ActionContext.getContext().getSession().get("banjiList");
		else
			banjiList = banjiService.getAllBanji();
		for (Banji banji : banjiList) {
			honors.addAll(banji.getHonors());
		}
		ActionContext.getContext().getSession().put("honors", honors);
		systemAdmins = new BaseDaoHibernateImpl().get(new SystemAdmin());
		System.out.println(systemAdmins.get(0).getUsername());
		System.out.println(banjiList);
		return SUCCESS;
	}
	
	public String toShowPhotoView() {
		Album album = albumService.getAlbumById(albumId);
		System.out.println("albumId" + albumId);
		Set<Photo> photos = album.getPhotos();
		
		for (Photo photo : photos) {
			photoList.add(photo);
		}
		return SUCCESS;
	}
	
	public String toShowAlbumView() {
		albumList = albumService.getAllAlbum();
		int i = albumList.size();
		System.out.println(i);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String addManager(){
		honors = new ArrayList<Honor>();
		if(ActionContext.getContext().getSession().get("banjiList") != null)
			banjiList = (List<Banji>) ActionContext.getContext().getSession().get("banjiList");
		else
			banjiList = banjiService.getAllBanji();
		for (Banji banji : banjiList) {
			honors.addAll(banji.getHonors());
		}
		ActionContext.getContext().getSession().put("honors", honors);
		systemAdmins = new BaseDaoHibernateImpl().get(new SystemAdmin());
		Banji theBanji = banjiList.get(albumId-1);
		for(Student stu : theBanji.getStudents()){
			stu.setIsManager(false);
			studentService.updateStudentInfo(stu);
		}
		Student student = null;
		for (String name : item) {
			System.out.println(name);
			student = studentService.getStudentByStudentName(name);
			student.setIsManager(true);
			studentService.updateStudentInfo(student);
		}
		return "addManager";
	}

	@SuppressWarnings("unchecked")
	public String appoint(){
		honors = new ArrayList<Honor>();
		if(ActionContext.getContext().getSession().get("banjiList") != null)
			banjiList = (List<Banji>) ActionContext.getContext().getSession().get("banjiList");
		else
			banjiList = banjiService.getAllBanji();
		for (Banji banji : banjiList) {
			honors.addAll(banji.getHonors());
		}
		ActionContext.getContext().getSession().put("honors", honors);
		Student student = null;
		for (int i = 0; item != null && i < item.length; i ++) {
			System.out.println(item[i]);
			student = studentService.getStudentByStudentNum(item[i]);
			student.setPosition(positions[i]);
			studentService.updateStudentInfo(student);
		}
		return "appoint";
	}
	
	@SuppressWarnings("unchecked")
	public String showHonor(){
		honors = (List<Honor>) ActionContext.getContext().getSession().get("honors");
		if(honors != null && !honors.isEmpty()){
			for(Honor honor : honors){
				if(honor.getHonorId() == albumId){
					ActionContext.getContext().getSession().put("honor", honor);
				}
			}
		}else{
			Honor honor = honorService.getHonorById(albumId);
			ActionContext.getContext().getSession().put("honor", honor);
		}
		return SUCCESS;
	}
	
	public String showActivity(){
		List<Activity> ayctivities = activityService.getAllActivies();
		if(ayctivities != null && !ayctivities.isEmpty()){
			for(Activity activity : ayctivities){
				if(activity.getActivityId() == albumId){
					ActionContext.getContext().getSession().put("activity", activity);
				}
			}
		}else{
			Activity activity = activityService.getActivityById(albumId);
			ActionContext.getContext().getSession().put("activity", activity);
		}
		return SUCCESS;
	}
	
	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

	public Photo getShowAlbum() {
		return showAlbum;
	}

	public void setShowAlbum(Photo showAlbum) {
		this.showAlbum = showAlbum;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public List<Banji> getBanjiList() {
		return banjiList;
	}

	public void setBanjiList(List<Banji> banjiList) {
		this.banjiList = banjiList;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public List<SystemAdmin> getSystemAdmins() {
		return systemAdmins;
	}

	public void setSystemAdmins(List<SystemAdmin> systemAdmins) {
		this.systemAdmins = systemAdmins;
	}

	public List<Honor> getHonors() {
		return honors;
	}

	public void setHonors(List<Honor> honors) {
		this.honors = honors;
	}

	public String[] getPositions() {
		return positions;
	}

	public void setPositions(String[] positions) {
		this.positions = positions;
	}
}
