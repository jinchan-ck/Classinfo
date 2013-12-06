package tk.sweetvvck.service;

import java.util.List;

import tk.sweetvvck.domain.Activity;

public interface ActivityService {
	public boolean addActivity(Activity activity);
	
	public boolean deleteActivity(Activity activity);
	
	public Activity getActivityById(Integer id);
	
	public List<Activity> getAllActivies();
}
