package lk.ijse.repository.custom.impl;

import lk.ijse.embedded.BorrowingDetailPK;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.repository.custom.BorrowingDetailsRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        String sql = "FROM borrowing_detail";
        Query query = session.createQuery(sql);
        List<BorrowingDetails> list = query.list();
        return list;
    }

    @Override
    public void update(BorrowingDetails entity) {

    }

    @Override
    public void delete(int id) {

    }
}
