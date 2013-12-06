package tk.sweetvvck.service;

import java.util.List;

import tk.sweetvvck.domain.Banji;

public interface BanjiService {
	public Banji getBanjiById(int id);
	
	public List<Banji> getAllBanji();
	
	public boolean updateBanji(Banji banji);
}
