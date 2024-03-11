package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BookRepository;
import lk.ijse.repository.custom.BranchRepository;
import lk.ijse.service.custom.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BRANCH);
    @Override
    public boolean saveBook(BookDTO bookDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        Branch branch = branchRepository.getBranch(bookDTO.getBranchId());
        boolean save = bookRepository.save(new Book(bookDTO.getBookID(),bookDTO.getTitle(),bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getBranchName(), bookDTO.getAvailability(),branch));
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
               book.getBranchName(),
               book.getAvailability(),
               book.getBranch().getBranchId()
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
        Branch branch = branchRepository.getBranch(bookDTO.getBranchId());
        bookRepository.update(new Book(bookDTO.getBookID(),bookDTO.getTitle(),bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getBranchName(), bookDTO.getAvailability(),branch));
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

    @Override
    public String getBranch(int bId) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        String branchName = bookRepository.getBranchId(bId);
        transaction.commit();
        session.close();
        return branchName;
    }

    @Override
    public BookDTO getBook(int bookId) {
        session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();

        bookRepository.setSession(session);
        Book book = bookRepository.getBook(bookId);
        BookDTO bookDTO = new BookDTO(book.getBookID(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getBranchName(),book.getAvailability(),book.getBranch().getBranchId());
//        transaction.commit();
        session.close();
        return bookDTO;
    }
}
