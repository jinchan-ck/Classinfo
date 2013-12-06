package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Banji;

public interface BanjiDao {
	public Banji getBanjiById(int id);
	
	public List<Banji> getAllBanji();
	
	public boolean updateBanji(Banji banji);
}
