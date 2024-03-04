package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTM {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private String branch;
    private String availability;
}
