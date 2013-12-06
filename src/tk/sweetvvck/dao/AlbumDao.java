package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Album;

public interface AlbumDao {

	public abstract boolean addAlbum(Album album);

	public abstract boolean deleteAlbum(Album album);

	public abstract boolean updateAlbum(Album album);

	public abstract List<Album> getAllAlbums();

	public abstract Album getAlbumById(int id);

}