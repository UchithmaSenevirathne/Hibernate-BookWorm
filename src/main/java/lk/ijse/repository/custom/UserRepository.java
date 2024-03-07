package lk.ijse.repository.custom;

import lk.ijse.entity.User;
import lk.ijse.repository.CRUDRepository;

public interface UserRepository extends CRUDRepository <User> {

    boolean checkAdmin(String userName);

    boolean search(String userName, String password);

    void deleteAdmin(String username);
}
