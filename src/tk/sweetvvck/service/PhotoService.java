package tk.sweetvvck.service;

import tk.sweetvvck.domain.Album;
import tk.sweetvvck.domain.Photo;

public interface PhotoService {

	public abstract boolean uploadPhoto(Photo photo);

	public abstract boolean updatePhoto(Photo photo);

	public abstract boolean deletePhoto(Photo photo);
	
	public abstract Album getAlbum(Photo photo);
	
	public abstract Photo getPhotoById(Integer id);

}