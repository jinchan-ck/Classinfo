package tk.sweetvvck.service.impl;

import tk.sweetvvck.dao.PhotoDao;
import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Photo;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.service.PhotoService;
import tk.sweetvvck.utils.DaoFactory;

public class PhotoServiceImpl implements PhotoService {
	PhotoDao photoDao = DaoFactory.getInstance().getPhotoDao();
	
	@Override
	public boolean uploadPhoto(Photo photo) {
		boolean flag = false;
		try {
			photoDao.addPhoto(photo);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean updatePhoto(Photo photo) {
		boolean flag = false;
		try {
			photoDao.updatePhoto(photo);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean deletePhoto(Photo photo) {
		boolean flag = false;
		try {
			photoDao.deletePhoto(photo);
			flag = true;
		} catch (DaoException e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Album getAlbum(Photo photo) {
		return photo.getAlbum();
	}

	@Override
	public Photo getPhotoById(Integer id) {
		return photoDao.getPhotoById(id);
	}
}
