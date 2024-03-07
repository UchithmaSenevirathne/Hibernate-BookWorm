package lk.ijse.service.custom;

import lk.ijse.dto.UserDTO;
import lk.ijse.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    boolean searchUser(String userName, String password);
    boolean checkAdmin(String userName);
    boolean saveUser(UserDTO userDTO);
    List<UserDTO> getAdmins();
    void updateUser(UserDTO userDTO);

    void deleteAdmin(String username);
}
