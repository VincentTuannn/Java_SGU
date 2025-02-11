package dao;
import java.io.FileInputStream;
import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(int t);
	public ArrayList<T> selectAll();
	public T selectById(int t);
	public ArrayList<T> selectByCondition(String condition);
	public int getAutoIncrement();
	int restore(T t);
	ArrayList<T> selectAll(String t);
	void importDatabase(FileInputStream inputStream);
}
    