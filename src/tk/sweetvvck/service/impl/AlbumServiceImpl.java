package tk.sweetvvck.service.impl;

import java.util.List;

import tk.sweetvvck.dao.AlbumDao;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.service.AlbumService;
import tk.sweetvvck.utils.DaoFactory;

public class AlbumServiceImpl implements AlbumService {
	private AlbumDao albumDao = DaoFactory.getInstance().getAlbumDao();

	@Override
	public boolean uploadAlbum(Album album) {
		boolean flag = false;
		try {
			albumDao.addAlbum(album);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean deleteAlbum(Album album) {
		boolean flag = false;
		try {
			albumDao.deleteAlbum(album);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean updateAlbum(Album album) {
		boolean flag = false;
		try {
			albumDao.updateAlbum(album);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<Album> getAllAlbum() {
		return albumDao.getAllAlbums();
	}

	@Override
	public Album getAlbumById(int id) {
		return albumDao.getAlbumById(id);
	}
	
	
}
