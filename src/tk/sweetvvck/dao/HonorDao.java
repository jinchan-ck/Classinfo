package tk.sweetvvck.dao;

import tk.sweetvvck.domain.Honor;

public interface HonorDao {
	
	public boolean addHonor(Honor honor);
	
	public boolean deleteHonor(Honor honor);
	
	public boolean updateHonor(Honor honor);
	
	public Honor getHonorById(Integer id);
	
}
