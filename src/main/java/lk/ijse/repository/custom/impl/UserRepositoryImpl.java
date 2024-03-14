package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.entity.User;
import lk.ijse.repository.custom.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    @Override
    public boolean search(String userName, String password) {
        User user = session.get(User.class, userName);
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public void deleteAdmin(String username) {
        User user = session.get(User.class, username);
        session.delete(user);
    }

    @Override
    public User getUser(String username) {
        User user = session.get(User.class, username);
        return user;
    }

    @Override
    public String getPwd(String name, String oldUName) {
        User user = session.get(User.class, oldUName);
        String pwd = user.getPassword();

        return pwd;
    }

    @Override
    public boolean save(User entity) {
        String userName = (String) session.save(entity);
        if (userName!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAdmin(String userName) {
        User user = session.get(User.class, userName);
        if (user.getRole().equals("ADM")){
            return true;
        }
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAll() {
        String sql = "FROM user";
        Query query = session.createQuery(sql);
        List<User> list = query.list();
        return list;
    }

    @Override
    public void update(User entity) {
        session.update(entity);
    }

    @Override
    public void delete(int id) {
    }
}
