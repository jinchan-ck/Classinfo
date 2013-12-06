package tk.sweetvvck.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tk.sweetvvck.dao.BaseDao;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class BaseDaoHibernateImpl implements BaseDao {

	@Override
	public boolean add(Object object) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		boolean flag = false;
		Transaction tx = session.beginTransaction();

		try {
			session.save(object);
			tx.commit();
			flag = true;
		} catch (HibernateException e) {
			flag = false;
			throw new DaoException(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean delete(Object object) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		boolean flag = false;
		Transaction tx = session.beginTransaction();

		try {
			session.delete(object);
			tx.commit();
			flag = true;
		} catch (HibernateException e) {
			flag = false;
			throw new DaoException(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean update(Object object) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		boolean flag = false;
		Transaction tx = session.beginTransaction();

		try {
			session.saveOrUpdate(object);
			tx.commit();
			flag = true;
		} catch (HibernateException e) {
			flag = false;
			throw new DaoException(e.getMessage(), e);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> get(T t) {
		List<T> list = null;
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			Query query = session.createQuery("from "+ t.getClass().getName());
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}

		return list;

	}

}
