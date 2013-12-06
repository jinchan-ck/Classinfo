package tk.sweetvvck.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.HonorDao;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class HonorDaoHibernateImpl extends BaseDaoHibernateImpl implements
		HonorDao {

	@Override
	public boolean addHonor(Honor honor) {
		return super.add(honor);
	}

	@Override
	public boolean deleteHonor(Honor honor) {
		return super.delete(honor);
	}

	@Override
	public boolean updateHonor(Honor honor) {
		return super.update(honor);
	}

	@Override
	public Honor getHonorById(Integer id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Honor honor = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Honor.class);
			criteria.add(Restrictions.eq("honorId", id));
			
			honor = (Honor)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return honor;
	}

}
