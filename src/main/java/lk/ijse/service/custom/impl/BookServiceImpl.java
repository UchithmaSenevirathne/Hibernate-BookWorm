package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BookRepository;
import lk.ijse.service.custom.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);

    @Override
    public boolean saveBook(BookDTO bookDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        boolean save = bookRepository.save(bookDTO.toEntity());
        transaction.commit();
        session.close();
        return save;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : bookRepository.getAll()){
            bookDTOS.add(new BookDTO(
               book.getBookID(),
               book.getTitle(),
               book.getAuthor(),
               book.getGenre(),
               book.getBranch(),
               book.getAvailability()
            ));
        }
        transaction.commit();
        session.close();
        return bookDTOS;
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        bookRepository.update(bookDTO.toEntity());
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteBook(int id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        bookRepository.delete(id);
        transaction.commit();
        session.close();
    }
}
