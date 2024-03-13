package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OverDueTM {

    private int bookID;
    private String title;
    private String name;
    private Timestamp borrowDate;
    private Timestamp dueDate;
}
