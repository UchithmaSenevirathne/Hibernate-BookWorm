package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BorrowingDetailsDTO;
import lk.ijse.embedded.BorrowingDetailPK;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BookRepository;
import lk.ijse.repository.custom.BorrowingDetailsRepository;
import lk.ijse.repository.custom.BranchRepository;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.BorrowingDetailsService;
import lk.ijse.service.custom.BranchService;
import lk.ijse.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDetailsServiceImpl implements BorrowingDetailsService {

    BorrowingDetailsRepository borrowingDetailsRepository = (BorrowingDetailsRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BORROWINGDETAILS);

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);

    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BRANCH);

    private Session session;

    @Override
    public boolean save(BorrowingDetailsDTO borrowingDetailsDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        borrowingDetailsRepository.setSession(session);

        try {
            System.out.println(borrowingDetailsDTO.getDueDate()+ "and" +borrowingDetailsDTO.getReturnDate());

            boolean save = borrowingDetailsRepository.save(new BorrowingDetails(borrowingDetailsDTO.getBorrowingDetailPK(), borrowingDetailsDTO.getBorrowDate(), borrowingDetailsDTO.getDueDate(), borrowingDetailsDTO.getReturnDate()));

            if (save){
                System.out.println("book Id  : "+borrowingDetailsDTO.getBorrowingDetailPK().getBookID());

                bookRepository.setSession(session);
                Book book = bookRepository.getBook(borrowingDetailsDTO.getBorrowingDetailPK().getBookID());
                book.setAvailability("NO");
                bookRepository.update(book);

                transaction.commit();
                session.close();
                return true;
            }
            transaction.rollback();
            session.close();
            return false;

        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkReturns(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        borrowingDetailsRepository.setSession(session);
        List<BorrowingDetails> borrowingDetails = borrowingDetailsRepository.getAll();

        for (BorrowingDetails bDs : borrowingDetails){
            if (bDs.getBorrowingDetailPK().getUserName().equals(userName)){

                Timestamp currentDate = new Timestamp(System.currentTimeMillis());

                Timestamp today = Timestamp.valueOf(currentDate.toLocalDateTime());
                Timestamp due = Timestamp.valueOf(bDs.getDueDate().toLocalDateTime());

                if (due.before(today) && bDs.getReturnDate() == null){
                    session.close();
                    return false;
                }
            }
        }
        session.close();
        return true;
    }

    @Override
    public boolean checkNumberOfBooks(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        borrowingDetailsRepository.setSession(session);
        List<BorrowingDetails> borrowingDetails = borrowingDetailsRepository.getAll();

        int count = 0;

        for (BorrowingDetails bDs : borrowingDetails){
            if (bDs.getBorrowingDetailPK().getUserName().equals(userName)){

                if (bDs.getReturnDate() == null){
                    count++;
                }
            }
        }

        if (count == 2){
            session.close();
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public boolean updateReturn(int id) {
        System.out.println(id);
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            borrowingDetailsRepository.setSession(session);

            BorrowingDetails borrowingDetails = borrowingDetailsRepository.getBorrowing(id);
            Timestamp returnDate = new Timestamp(System.currentTimeMillis());
            borrowingDetails.setReturnDate(returnDate);
            borrowingDetailsRepository.update(borrowingDetails);

            bookRepository.setSession(session);
            Book book = bookRepository.getBook(id);
            book.setAvailability("YES");
            bookRepository.update(book);

            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}
