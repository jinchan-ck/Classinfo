package tk.sweetvvck.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.AlbumDao;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class AlbumDaoHibernateImpl extends BaseDaoHibernateImpl implements AlbumDao {

	@Override
	public boolean addAlbum(Album album) {
		return super.add(album);
	}

	@Override
	public boolean deleteAlbum(Album album) {
		return super.delete(album);
	}

	@Override
	public boolean updateAlbum(Album album) {
		return super.update(album);
	}

	@Override
	public List<Album> getAllAlbums() {
		return super.get(new Album());
	}

	@Override
	public Album getAlbumById(int id) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Album album = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Album.class);
			criteria.add(Restrictions.eq("albumId", id));
			
			album = (Album)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return album;
	}

}
