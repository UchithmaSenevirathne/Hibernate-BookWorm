package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OverDueDTO {

    private int bookID;
    private String title;
    private String name;
    private Timestamp borrowDate;
    private Timestamp dueDate;
}
