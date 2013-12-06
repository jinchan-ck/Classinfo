package tk.sweetvvck.service.impl;

import tk.sweetvvck.dao.HonorDao;
import tk.sweetvvck.domain.Honor;
import tk.sweetvvck.service.HonorService;
import tk.sweetvvck.utils.DaoFactory;

public class HonorServiceImpl implements HonorService {

	private HonorDao honorDao = DaoFactory.getInstance().getHonorDao();
	
	@Override
	public boolean addHonor(Honor honor) {
		return honorDao.updateHonor(honor);
	}

	@Override
	public boolean deleteHonor(Honor honor) {
		return honorDao.deleteHonor(honor);
	}

	@Override
	public Honor getHonorById(Integer id) {
		return honorDao.getHonorById(id);
	}

}
