package lk.ijse.repository.custom;

import lk.ijse.entity.User;
import lk.ijse.repository.CRUDRepository;
import org.hibernate.Session;

public interface UserRepository extends CRUDRepository <User> {
    boolean checkAdmin(String id);

    void setSession(Session session);

}
