package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowingDTO {
    private String name;
    private int bookID;
    private String title;
    private String branchName;
    private Timestamp borrowDate;
    private Timestamp dueDate;
    private Timestamp returnDate;
}
