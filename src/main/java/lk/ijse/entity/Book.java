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
    private String branchName;
    @Column(name = "availability")
    private String availability;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

//    public Book() {
//    }
//
//    public Book(int bookID, String title, String author, String genre, String branchName, String availability) {
//        this.bookID = bookID;
//        this.title = title;
//        this.author = author;
//        this.genre = genre;
//        this.branchName = branchName;
//        this.availability = availability;
//    }
//
//    public int getBookID() {
//        return bookID;
//    }
//
//    public void setBookID(int bookID) {
//        this.bookID = bookID;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getBranchName() {
//        return branchName;
//    }
//
//    public void setBranchName(String branchName) {
//        this.branchName = branchName;
//    }
//
//    public String getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(String availability) {
//        this.availability = availability;
//    }
//
//    @Override
//    public String toString() {
//        return "Book{" +
//                "bookID=" + bookID +
//                ", title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", genre='" + genre + '\'' +
//                ", branchName='" + branchName + '\'' +
//                ", availability='" + availability + '\'' +
//                '}';
//    }
}


