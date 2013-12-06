package tk.sweetvvck.dao;

import tk.sweetvvck.domain.Avatar;

public interface AvatarDao {
	
	public boolean addAvatar(Avatar avatar);
	
	public boolean deleteAvatar(Avatar avatar);
	
	public boolean updateAvatar(Avatar avatar);
	
	public Avatar getAvatarById(int id);
	
}
