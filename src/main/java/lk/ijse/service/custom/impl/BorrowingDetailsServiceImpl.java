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

//        User user = userRepository.getUser(borrowingDetailsDTO.getUserName());
//
//        if (user == null){
//            throw new IllegalArgumentException("User with userName " + borrowingDetailsDTO.getUserName() + " does not exist.");
//        }

//        BorrowingDetailPK pk = new BorrowingDetailPK(borrowingDetailsDTO.getUserName(), borrowingDetailsDTO.getBookID());
//
//        BorrowingDetails borrowingDetails = new BorrowingDetails(pk, borrowingDetailsDTO.getBorrowDate(), borrowingDetailsDTO.getDueDate(), borrowingDetailsDTO.getReturnDate());
//
//        // Set the User entity in BorrowingDetails
//        borrowingDetails.setUser(user);
//
//        boolean save = borrowingDetailsRepository.save(borrowingDetails);

        System.out.println(borrowingDetailsDTO.getDueDate()+ "and" +borrowingDetailsDTO.getReturnDate());

        boolean save = borrowingDetailsRepository.save(new BorrowingDetails(borrowingDetailsDTO.getBorrowingDetailPK(), borrowingDetailsDTO.getBorrowDate(), borrowingDetailsDTO.getDueDate(), borrowingDetailsDTO.getReturnDate()));

        transaction.commit();
        session.close();
        return save;
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
}
