package lk.ijse.repository.custom.impl;

import lk.ijse.embedded.BorrowingDetailPK;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.repository.custom.BorrowingDetailsRepository;
import org.hibernate.Session;

import java.util.List;

public class BorrowingDetailsRepositoryImpl implements BorrowingDetailsRepository {

    private Session session;

    @Override
    public boolean save(BorrowingDetails entity) {
        BorrowingDetailPK borrowingDetailPK = (BorrowingDetailPK) session.save(entity);
        System.out.println(entity.getDueDate()+ " and " +entity.getReturnDate());
        if (borrowingDetailPK != null){
            return true;
        }
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<BorrowingDetails> getAll() {
        return null;
    }

    @Override
    public void update(BorrowingDetails entity) {

    }

    @Override
    public void delete(int id) {

    }
}
