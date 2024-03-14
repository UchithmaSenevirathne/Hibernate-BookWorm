package lk.ijse.repository.custom;

import lk.ijse.repository.SuperRepository;
import org.hibernate.Session;

public interface CountRepository extends SuperRepository {
    void setSession(Session session);

    int getUserCount();

    int getBookCount();

    int getBorrowCount();

    int getReturnCount();

    int getBranchCount();
}
