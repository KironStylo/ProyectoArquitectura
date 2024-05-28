package repositorio;

import java.util.List;

public interface Repositorio<T> {
	public void save(T obj);
	public void delete(T obj);
	public void update(T obj);
	public T findById(Long id);
	public List<T> findAll();
}
