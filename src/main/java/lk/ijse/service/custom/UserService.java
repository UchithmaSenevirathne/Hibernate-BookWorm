package lk.ijse.service.custom;

import lk.ijse.dto.UserDTO;
import lk.ijse.service.SuperService;

public interface UserService extends SuperService {
    boolean searchUser(String userName, String password);

    boolean checkAdmin(String userName, String password);

}
