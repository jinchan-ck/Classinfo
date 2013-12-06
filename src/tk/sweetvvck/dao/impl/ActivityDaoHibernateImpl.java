package tk.sweetvvck.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.ActivityDao;
import tk.sweetvvck.domain.Activity;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class ActivityDaoHibernateImpl extends BaseDaoHibernateImpl implements ActivityDao {

	@Override
	public boolean addActivity(Activity activity) {
		return super.update(activity);
	}

	@Override
	public boolean deleteActivity(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Activity getActivityById(Integer id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		
		Activity activity = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Activity.class);
			criteria.add(Restrictions.eq("activityId", id));
			
			activity = (Activity)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return activity;
	}

	@Override
	public List<Activity> getAllActivities() {
		return super.get(new Activity());
	}

}
