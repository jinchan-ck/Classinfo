package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Activity;

public interface ActivityDao {
	
	public boolean addActivity(Activity activity);
	
	public boolean deleteActivity(Activity activity);
	
	public boolean updateActivity(Activity activity);
	
	public Activity getActivityById(Integer id);
	
	public List<Activity> getAllActivities();
	
}
