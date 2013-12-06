package tk.sweetvvck.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.BanjiDao;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class BanjiDaoHibernateImpl extends BaseDaoHibernateImpl implements BanjiDao {

	@Override
	public Banji getBanjiById(int id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Banji banji = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Banji.class);
			criteria.add(Restrictions.eq("banjiId", id));
			
			banji = (Banji)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return banji;
	}

	@Override
	public List<Banji> getAllBanji() {
		return super.get(new Banji());
	}

	@Override
	public boolean updateBanji(Banji banji) {
		return super.update(banji);
	}

}
