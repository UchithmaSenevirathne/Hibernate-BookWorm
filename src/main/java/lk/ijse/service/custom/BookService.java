package lk.ijse.service.custom;

import lk.ijse.dto.BookDTO;
import lk.ijse.service.SuperService;

import java.util.List;

public interface BookService extends SuperService {
    boolean saveBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    void updateBook(BookDTO bookDTO);

    void deleteBook(int id);

    String getBranch(int bId);

    BookDTO getBook(int bookId);
}
