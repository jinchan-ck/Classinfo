package tk.sweetvvck.utils;

import java.io.InputStream;
import java.util.Properties;

import tk.sweetvvck.dao.ActivityDao;
import tk.sweetvvck.dao.AlbumDao;
import tk.sweetvvck.dao.AvatarDao;
import tk.sweetvvck.dao.BanjiDao;
import tk.sweetvvck.dao.HonorDao;
import tk.sweetvvck.dao.PhotoDao;
import tk.sweetvvck.dao.StudentDao;

public class DaoFactory {
	private static PhotoDao photoDao = null;
	private static AlbumDao albumDao = null;
	private static StudentDao studentDao = null;
	private static BanjiDao banjiDao = null;
	private static AvatarDao avatarDao = null;
	private static HonorDao honorDao = null;
	private static ActivityDao activityDao = null;
	private static DaoFactory instance = new DaoFactory();

	private DaoFactory() {
		try {
			Properties prop = new Properties();
			InputStream inStream = DaoFactory.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
			String photoDaoClass = prop.getProperty("photoDaoClass");
			String albumDaoClass = prop.getProperty("albumDaoClass");
			String studentDaoClass = prop.getProperty("studentDaoClass");
			String banjiDaoClass = prop.getProperty("banjiDaoClass"); 
			String avatarDaoClass = prop.getProperty("avatarDaoClass"); 
			String honorDaoClass = prop.getProperty("honorDaoClass");
			String activityDaoClass = prop.getProperty("activityDaoClass");

			photoDao = (PhotoDao) Class.forName(photoDaoClass).newInstance();
			albumDao = (AlbumDao) Class.forName(albumDaoClass).newInstance();
			studentDao = (StudentDao) Class.forName(studentDaoClass).newInstance();
			banjiDao = (BanjiDao) Class.forName(banjiDaoClass).newInstance();
			avatarDao = (AvatarDao) Class.forName(avatarDaoClass).newInstance();
			honorDao = (HonorDao) Class.forName(honorDaoClass).newInstance();
			activityDao = (ActivityDao) Class.forName(activityDaoClass).newInstance();
			
		} catch (Throwable e) {
			System.out.println("instance:" + instance);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public AlbumDao getAlbumDao() {
		return albumDao;
	}
	
	public StudentDao getStudentDao(){
		return studentDao;
	}
	
	public BanjiDao getBanjiDao(){
		return banjiDao;
	}
	
	public AvatarDao getAvatarDao(){
		return avatarDao;
	}
	
	public HonorDao getHonorDao(){
		return honorDao;
	}
	
	public ActivityDao getActivityDao(){
		return activityDao;
	}
}
