package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String userName;
    private String name;
    private String password;
    private String role;

    public User toEntity() {
        User userEnt = new User();
        userEnt.setUserName(this.userName);
        userEnt.setName(this.name);
        userEnt.setPassword(this.password);
        userEnt.setRole(this.role);
        return userEnt;
    }
}
