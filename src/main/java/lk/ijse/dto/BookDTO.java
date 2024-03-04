package lk.ijse.dto;

import lk.ijse.entity.Book;
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
    private String branch;
    private String availability;

    public Book toEntity() {
        Book bookEnt = new Book();
        bookEnt.setBookID(this.bookID);
        bookEnt.setTitle(this.title);
        bookEnt.setAuthor(this.author);
        bookEnt.setGenre(this.genre);
        bookEnt.setBranch(this.branch);
        bookEnt.setAvailability(this.availability);
        return bookEnt;
    }
}
