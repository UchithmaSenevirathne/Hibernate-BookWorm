package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibraryTM {
    private String title;
    private String branchName;
    private Timestamp borrowDate;
    private Timestamp dueDate;
}
