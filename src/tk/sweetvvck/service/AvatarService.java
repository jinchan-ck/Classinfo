package tk.sweetvvck.service;

import tk.sweetvvck.domain.Avatar;

public interface AvatarService {
	public boolean addAvatar(Avatar avatar);
	
	public Avatar getAvatarById(int id);
	
	public boolean updateAvatar(Avatar avatar);
}
