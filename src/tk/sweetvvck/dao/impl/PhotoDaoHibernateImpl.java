package tk.sweetvvck.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.PhotoDao;
import tk.sweetvvck.domain.Photo;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class PhotoDaoHibernateImpl extends BaseDaoHibernateImpl implements PhotoDao{
	public boolean addPhoto(Photo photo) {
		return super.add(photo);
	}

	public boolean deletePhoto(Photo photo) {
		return super.delete(photo);
	}

	public boolean updatePhoto(Photo photo) {
		return super.update(photo);
	}

	@SuppressWarnings("unchecked")
	public List<Photo> getPhotosByAlbum(int albumId) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		List<Photo> photos = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Photo.class);
			criteria.add(Restrictions.eq("album", albumId));
			
			photos = (List<Photo>)criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return photos;
	}

	public Photo getPhotoById(int id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Photo photo = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Photo.class);
			criteria.add(Restrictions.eq("id", id));
			
			photo = (Photo)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return photo;
	}
}
