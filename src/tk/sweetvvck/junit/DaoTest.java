package tk.sweetvvck.junit;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import tk.sweetvvck.dao.AlbumDao;
import tk.sweetvvck.dao.HonorDao;
import tk.sweetvvck.dao.PhotoDao;
import tk.sweetvvck.dao.StudentDao;
import tk.sweetvvck.dao.impl.BaseDaoHibernateImpl;
import tk.sweetvvck.dao.impl.HonorDaoHibernateImpl;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.domain.Photo;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.domain.SystemAdmin;
import tk.sweetvvck.service.HonorService;
import tk.sweetvvck.service.impl.HonorServiceImpl;
import tk.sweetvvck.utils.DaoFactory;

public class DaoTest {
	PhotoDao photoDao = DaoFactory.getInstance().getPhotoDao();
	AlbumDao albumDao = DaoFactory.getInstance().getAlbumDao();
	@Test
	public void testAddPhoto() {
		Album album = new Album();
		album.setAlbumname("�ڶ������");
		albumDao.addAlbum(album);/*
								 * Banji banji = new Banji();
								 * banji.setBanjiName("��Ϣ�����1109B"); Banji
								 * banji2 = new Banji();
								 * banji2.setBanjiName("��Ϣ���1205B"); Session
								 * session =
								 * HibernateSessionFactory.getSessionFactory()
								 * .getCurrentSession();
								 * session.beginTransaction();
								 * session.save(banji); session.save(banji2);
								 * session.getTransaction().commit();
								 */
		/*
		 * Student stu = new Student(); stu.setStudentName("gad");
		 * stu.setStudentNum("132"); stu.setIsManager(true);
		 * stu.setBanji(banji); stu.setGender("nan");
		 * 
		 * Session session1 = HibernateSessionFactory.getSessionFactory()
		 * .getCurrentSession(); session1.beginTransaction();
		 * session1.save(stu); session1.getTransaction().commit(); Photo photo =
		 * new Photo(); photo.setAlbum(album); photo.setStudent(stu);
		 * photo.setDiscription("haha"); photo.setName("xixi");
		 * photo.setPath("dagsdagds"); photo.setUploadDate(new Timestamp(new
		 * Date().getTime()));
		 * 
		 * photoDao.addPhoto(photo);
		 */
	}

	@Test
	public void testDeletePhoto() {
		Photo photo = photoDao.getPhotoById(4);

		System.out.println(photoDao.deletePhoto(photo));
	}

	@Test
	public void testUpdate() {
		Photo photo = photoDao.getPhotoById(5);
		photo.setPath("fadfadfdsfdsfdsfadsgsdgsda");
		System.out.println(photoDao.updatePhoto(photo));

	}

	@Test
	public void testDeleteAlbum() {
		Album album = albumDao.getAlbumById(1);
		System.out.println(albumDao.deleteAlbum(album));
	}

	@Test
	public void testUpdateAlbum() {
		/*
		 * Album album = albumDao.getAlbumById(2);
		 * album.setAlbumname("�������͹�����");
		 * 
		 * System.out.println(albumDao.updateAlbum(album));
		 */
		List<Album> list = albumDao.getAllAlbums();

		for (Album album : list) {
			System.out.println(album.getAlbumname());
		}

		/*
		 * Set<Photo> photos = album.getPhotos();
		 * 
		 * 
		 * //Hibernate.initialize(photos); for (Photo photo : photos) {
		 * System.out.println(photo.getUploadDate()); }
		 */

	}

	@Test
	public void getBanji() {

		StudentDao studentDao = DaoFactory.getInstance().getStudentDao();

		Student student = studentDao
				.getStudentInfoByStudentNum("2011080332120");

		Banji banji = student.getBanji();
		System.out.println("banji " + banji.getBanjiName());

	}

	@Test
	public void getAlbums() {

		StudentDao studentDao = DaoFactory.getInstance().getStudentDao();

		Student student = studentDao
				.getStudentInfoByStudentNum("2011080332120");

		Banji banji = student.getBanji();
		System.out.println(banji.getAlbums());

	}

	@Test
	public void getHonors() {
		StudentDao studentDao = DaoFactory.getInstance().getStudentDao();
		/*
		 * HonorDao honorDao = new HonorDaoHibernateImpl();
		 * 
		 * Honor honor = honorDao.getHonorById(1); Student student = studentDao
		 * .getStudentInfoByStudentNum("2011080332120"); Set<Honor> honors1 =
		 * new HashSet<Honor>(); honors1.add(honor); student.setHonors(honors1);
		 * studentDao.updateStudentInfo(student);
		 */
		Student student1 = studentDao
				.getStudentInfoByStudentNum("2010080305131");
		Banji banji = student1.getBanji();
		Set<Honor> honors = banji.getHonors();
		for (Honor honor1 : honors) {
			System.out.println(honor1.getCompetitionName());
		}
	}

	@Test
	public void deleteHonor() {
		HonorDao honorDao = new HonorDaoHibernateImpl();
		Honor honor = honorDao.getHonorById(1);
		honorDao.deleteHonor(honor);
		System.out.println(honor.getCompetitionName());
	}
	
	public static void main(String[] args) {
		StudentDao studentDao = DaoFactory.getInstance().getStudentDao();
		Student student = studentDao
				.getStudentInfoByStudentNum("2010080305131");
		Honor honor = student.getHonors().iterator().next();
		HonorDao honorDao = new HonorDaoHibernateImpl();
		honorDao.deleteHonor(honor);
		System.out.println(honor.getCompetitionName());
	}
	
	@Test
	public void testSpit(){
		String str = "关微,程科";
		String[] arr = str.split(",");
		for (String string : arr) {
			System.out.println(string);
		}
		
	}
	
	@Test
	public void getSystemAdmin(){
		List<SystemAdmin> sys = new BaseDaoHibernateImpl().get(new SystemAdmin());
		for (SystemAdmin systemAdmin : sys) {
			System.out.println(systemAdmin.getPassword());
		}
	}
	
	@Test
	public void getHonorAlbum(){
		HonorService honorService = new HonorServiceImpl();
		Honor honor = honorService.getHonorById(2);
		Album album = honor.getAlbum();
		String name = album.getAlbumname();
		System.out.println("name : " + name);
	}
	
}
