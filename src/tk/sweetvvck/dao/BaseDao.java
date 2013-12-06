package tk.sweetvvck.dao;

import java.util.List;

public interface BaseDao {
	public boolean add(Object object);
	
	public boolean delete(Object object);
	
	public boolean update (Object object);
	
	public <T> List<T> get(T t);
}
