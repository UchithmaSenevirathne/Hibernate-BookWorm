package lk.ijse.repository.custom;

import lk.ijse.repository.SuperRepository;
import org.hibernate.Session;

import java.util.List;

public interface QueryRepository extends SuperRepository {
    void setSession(Session session);

    List<Object[]> getAllBorrowings();
}
