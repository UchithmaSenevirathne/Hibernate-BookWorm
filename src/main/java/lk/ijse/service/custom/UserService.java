package lk.ijse.service.custom;

import lk.ijse.dto.UserDTO;
import lk.ijse.service.SuperService;

public interface UserService extends SuperService {
    boolean searchUser(String userName);

    boolean checkAdmin(String userName);

}
