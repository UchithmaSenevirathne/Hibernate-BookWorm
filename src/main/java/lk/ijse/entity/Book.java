package lk.ijse.entity;


import lk.ijse.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookID;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "branch")
    private String branch;
    @Column(name = "availability")
    private String availability;

    public BookDTO toDto() {
        BookDTO bookDto = new BookDTO();
        bookDto.setBookID(this.bookID);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);
        bookDto.setBranch(this.branch);
        bookDto.setAvailability(this.availability);
        return bookDto;
    }
}
