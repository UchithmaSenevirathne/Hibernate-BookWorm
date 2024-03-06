package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Book;
import lk.ijse.repository.custom.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private Session session;
    @Override
    public boolean save(Book entity) {
        int id = (Integer) session.save(entity);
        System.out.println("saveId "+id);
        if (id > 0){
            return true;
        }
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Book> getAll() {
        String sql = "FROM book";
        Query query = session.createQuery(sql);
        List<Book> list = query.list();
        return list;
    }

    @Override
    public void update(Book entity) {
        session.update(entity);
    }

    @Override
    public void delete(int id) {
        Book book = session.get(Book.class, id);
        session.delete(book);
    }
}
