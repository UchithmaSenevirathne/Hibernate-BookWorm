package lk.ijse.repository.custom.impl;

import lk.ijse.entity.User;
import lk.ijse.repository.custom.UserRepository;
import org.hibernate.Session;

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
        return null;
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void delete(int id) {

    }
}
