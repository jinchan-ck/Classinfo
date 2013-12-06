package tk.sweetvvck.service.impl;

import tk.sweetvvck.dao.AvatarDao;
import tk.sweetvvck.domain.Avatar;
import tk.sweetvvck.service.AvatarService;
import tk.sweetvvck.utils.DaoFactory;

public class AvatarServiceImpl implements AvatarService {
	
	private AvatarDao avatarDao = DaoFactory.getInstance().getAvatarDao(); 
	
	@Override
	public boolean addAvatar(Avatar avatar) {
		return avatarDao.addAvatar(avatar);
	}

	@Override
	public Avatar getAvatarById(int id) {
		return avatarDao.getAvatarById(id);
	}
	
	public boolean updateAvatar(Avatar avatar){
		return avatarDao.updateAvatar(avatar);
	}
	
}
