package tk.sweetvvck.service;

import java.util.List;

import tk.sweetvvck.domain.Album;

public interface AlbumService {

	public abstract boolean uploadAlbum(Album album);

	public abstract boolean updateAlbum(Album album);

	public abstract boolean deleteAlbum(Album album);
	
	public abstract List<Album> getAllAlbum();
	
	public abstract Album getAlbumById(int id);

}