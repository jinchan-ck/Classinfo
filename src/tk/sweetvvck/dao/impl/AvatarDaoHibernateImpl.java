package tk.sweetvvck.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.AvatarDao;
import tk.sweetvvck.domain.Avatar;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class AvatarDaoHibernateImpl extends BaseDaoHibernateImpl implements AvatarDao {

	@Override
	public boolean addAvatar(Avatar avatar) {
		return super.add(avatar);
	}

	@Override
	public boolean deleteAvatar(Avatar avatar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAvatar(Avatar avatar) {
		return super.update(avatar);
	}

	@Override
	public Avatar getAvatarById(int id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Avatar avatar = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Avatar.class);
			criteria.add(Restrictions.eq("avatarId", id));
			
			avatar = (Avatar)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return avatar;
	}

}
