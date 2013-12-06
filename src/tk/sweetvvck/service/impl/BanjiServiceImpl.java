package tk.sweetvvck.service.impl;

import java.util.List;

import tk.sweetvvck.dao.BanjiDao;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.service.BanjiService;
import tk.sweetvvck.utils.DaoFactory;

public class BanjiServiceImpl implements BanjiService {
	BanjiDao banjiDao = DaoFactory.getInstance().getBanjiDao();
	
	@Override
	public Banji getBanjiById(int id) {
		return banjiDao.getBanjiById(id);
	}

	@Override
	public List<Banji> getAllBanji() {
		return banjiDao.getAllBanji();
	}

	@Override
	public boolean updateBanji(Banji banji) {
		return banjiDao.updateBanji(banji);
	}

}
