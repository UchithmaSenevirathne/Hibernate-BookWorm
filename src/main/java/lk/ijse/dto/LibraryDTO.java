package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibraryDTO {
    private String title;
    private String branchName;
    private Timestamp borrowDate;
    private Timestamp dueDate;
}
