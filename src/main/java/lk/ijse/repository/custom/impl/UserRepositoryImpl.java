package lk.ijse.repository.custom.impl;

import lk.ijse.entity.User;
import lk.ijse.repository.custom.UserRepository;
import org.hibernate.Session;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    @Override
    public User search(String id) {
        return session.get(User.class, id);
    }

    @Override
    public boolean checkAdmin(String id) {
        User user = session.get(User.class, id);
        if (user.getRole().equals("ADM")){
            return true;
        }
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
