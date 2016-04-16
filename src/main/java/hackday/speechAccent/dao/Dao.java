package hackday.speechAccent.dao;

import java.util.List;

/**
 * Created by nicaraguanec on 09.02.2016.
 */
public interface Dao<T> {
    void create(T target);
    T read(int id);
    List<T> readAll();
    void update(T target);
    void delete(T target);
}
