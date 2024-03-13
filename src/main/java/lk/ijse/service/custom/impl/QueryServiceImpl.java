package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.dto.OverDueDTO;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.QueryRepository;
import lk.ijse.service.custom.QueryService;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QueryServiceImpl implements QueryService {

    private Session session;

    QueryRepository queryRepository = (QueryRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.QUERY);

    @Override
    public List<BorrowingDTO> getAllBorrowings() {
        session = SessionFactoryConfig.getInstance().getSession();

        queryRepository.setSession(session);

        List<Object[]> objects = queryRepository.getAllBorrowings();

        List<BorrowingDTO> borrowingDTOS = new ArrayList<>();

        for (Object[] o : objects){
            borrowingDTOS.add(new BorrowingDTO(
                    (String) o[0],
                    (Integer) o[1],
                    (String) o[2],
                    (String) o[3],
                    (Timestamp) o[4],
                    (Timestamp) o[5],
                    (Timestamp) o[6]
            ));
        }
        session.close();
        return borrowingDTOS;
    }

    @Override
    public List<OverDueDTO> getAllOverDues() {
        session = SessionFactoryConfig.getInstance().getSession();

        queryRepository.setSession(session);

        List<Object[]> objects = queryRepository.getAllOverDues();

        List<OverDueDTO> overDueDTOS = new ArrayList<>();

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        for (Object[] o : objects){
            Timestamp returnDate = (Timestamp) o[5];
            Timestamp dueDate = (Timestamp) o[4];

            if (returnDate == null && dueDate.before(currentDate)) {
                overDueDTOS.add(new OverDueDTO(
                        (Integer) o[0],
                        (String) o[1],
                        (String) o[2],
                        (Timestamp) o[3],
                        (Timestamp) o[4]
                ));
            }
        }
        session.close();
        return overDueDTOS;
    }

    @Override
    public boolean checkOverDues() {
        session = SessionFactoryConfig.getInstance().getSession();

        queryRepository.setSession(session);
        List<BorrowingDetails> borrowingDetails = queryRepository.filterOverDues();

        if (borrowingDetails.isEmpty()){
            session.close();
            return false;

        }else {
            session.close();
            return true;
        }
    }

    @Override
    public List<LibraryDTO> getLibrary(String username) {
        session = SessionFactoryConfig.getInstance().getSession();

        queryRepository.setSession(session);

        List<Object[]> objects = queryRepository.getLibrary(username);

        List<LibraryDTO> libraryDTOS = new ArrayList<>();

        for (Object[] o : objects){
            libraryDTOS.add(new LibraryDTO(
                    (String) o[0],
                    (String) o[1],
                    (Timestamp) o[2],
                    (Timestamp) o[3]
            ));
        }
        session.close();
        return libraryDTOS;
    }
}
