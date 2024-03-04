package lk.ijse.repository;

import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface CRUDRepository <T> extends SuperRepository{

    boolean save(T entity);

    void setSession(Session session);

    List<T> getAll();

    void update(T entity);

    void delete(int id);

}
