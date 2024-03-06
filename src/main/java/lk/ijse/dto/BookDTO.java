package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private String branchName;
    private String availability;
    private int branchId;


}
