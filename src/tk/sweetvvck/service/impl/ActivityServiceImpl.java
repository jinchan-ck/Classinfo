package tk.sweetvvck.service.impl;
import java.util.List;

import tk.sweetvvck.dao.ActivityDao;
import tk.sweetvvck.domain.Activity;
import tk.sweetvvck.service.ActivityService;
import tk.sweetvvck.utils.DaoFactory;


public class ActivityServiceImpl implements ActivityService {

	private ActivityDao activityDao = DaoFactory.getInstance().getActivityDao();
	
	@Override
	public boolean addActivity(Activity activity) {
		return activityDao.addActivity(activity);
	}

	@Override
	public boolean deleteActivity(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Activity getActivityById(Integer id) {
		return activityDao.getActivityById(id);
	}

	@Override
	public List<Activity> getAllActivies() {
		return activityDao.getAllActivities();
	}

}
