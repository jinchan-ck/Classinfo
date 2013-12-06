package tk.sweetvvck.dao;

import tk.sweetvvck.domain.Photo;

public interface PhotoDao {

	public abstract boolean addPhoto(Photo photo);

	public abstract boolean deletePhoto(Photo photo);

	public abstract boolean updatePhoto(Photo photo);

	public abstract Photo getPhotoById(int id);

}