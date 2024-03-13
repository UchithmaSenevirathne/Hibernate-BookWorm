package lk.ijse.repository.custom.impl;

import lk.ijse.repository.custom.QueryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import lk.ijse.entity.BorrowingDetails;

import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Object[]> getAllBorrowings() {
//        String hql = "SELECT u.name, b.book_id, b.title, br.borrow_date, br.due_date, br.return_date " +
//                "FROM user u " +
//                "INNER JOIN borrowing_detail br ON u.user_name = br.user_name " +
//                "INNER JOIN book b ON br.book_id = b.book_id";

        String hql = "SELECT u.name, b.book_id, b.title, b.branch, br.borrow_date, br.due_date, br.return_date\n" +
                "FROM user u \n" +
                "JOIN borrowing_detail br ON u.user_name = br.user_name \n" +
                "JOIN book b ON b.book_id = br.book_id";



        Query query = session.createNativeQuery(hql);
        List<Object[]> results = query.list();

        return results;
    }

    @Override
    public List<Object[]> getAllOverDues() {
        String hql = "SELECT b.book_id, b.title, u.name, br.borrow_date, br.due_date, br.return_date\n" +
                "FROM user u \n" +
                "JOIN borrowing_detail br ON u.user_name = br.user_name \n" +
                "JOIN book b ON b.book_id = br.book_id";

        Query query = session.createNativeQuery(hql);
        List<Object[]> results = query.list();

        return results;
    }
}
