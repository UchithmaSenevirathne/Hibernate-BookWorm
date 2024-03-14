package lk.ijse.repository.custom.impl;

import lk.ijse.repository.custom.CountRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CountRepositoryImpl implements CountRepository {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public int getUserCount() {
        String sql = "SELECT count(user_name) FROM user WHERE role = 'CUS' ";
        Query query = session.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public int getBookCount() {
        String sql = "SELECT count(book_id) FROM book";
        Query query = session.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public int getBorrowCount() {
        String sql = "SELECT count(borrow_date) FROM borrowing_detail";
        Query query = session.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public int getReturnCount() {
        String sql = "SELECT count(return_date) FROM borrowing_detail";
        Query query = session.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public int getBranchCount() {
        String sql = "SELECT count(branch_id) FROM branch";
        Query query = session.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
