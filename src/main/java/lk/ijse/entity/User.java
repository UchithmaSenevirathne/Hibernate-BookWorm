package lk.ijse.entity;

import lk.ijse.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<BorrowingDetails> borrowingDetails = new ArrayList<>();

    public UserDTO toDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(this.userName);
        userDTO.setName(this.name);
        userDTO.setPassword(this.password);
        userDTO.setRole(this.role);
        return userDTO;
    }
}
