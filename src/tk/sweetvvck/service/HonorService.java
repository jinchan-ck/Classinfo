package tk.sweetvvck.service;

import tk.sweetvvck.domain.Honor;

public interface HonorService {
	public boolean addHonor(Honor honor);
	
	public boolean deleteHonor(Honor honor);
	
	public Honor getHonorById(Integer id);
}
