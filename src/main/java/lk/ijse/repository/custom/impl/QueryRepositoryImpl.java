package lk.ijse.repository.custom.impl;

import lk.ijse.repository.custom.QueryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import lk.ijse.entity.BorrowingDetails;

import java.sql.Timestamp;
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

        String sql = "SELECT u.name, b.book_id, b.title, b.branch, br.borrow_date, br.due_date, br.return_date\n" +
                "FROM user u \n" +
                "JOIN borrowing_detail br ON u.user_name = br.user_name \n" +
                "JOIN book b ON b.book_id = br.book_id";



        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.list();

        return results;
    }

    @Override
    public List<Object[]> getAllOverDues() {
        String sql = "SELECT b.book_id, b.title, u.name, br.borrow_date, br.due_date, br.return_date\n" +
                "FROM user u \n" +
                "JOIN borrowing_detail br ON u.user_name = br.user_name \n" +
                "JOIN book b ON b.book_id = br.book_id";

        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.list();

        return results;
    }


    //HQL
    @Override
    public List<BorrowingDetails> filterOverDues() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        String hql = "FROM borrowing_detail bd WHERE bd.returnDate = null AND bd.dueDate < :currentDate";
        Query query = session.createQuery(hql);
        query.setParameter("currentDate", currentDate);

        List<BorrowingDetails> list = query.list();

        return list;
    }

    @Override
    public List<Object[]> getLibrary(String username) {
        String sql = "SELECT b.title, b.branch, br.borrow_date, br.due_date\n" +
                "FROM user u \n" +
                "JOIN borrowing_detail br ON u.user_name = br.user_name \n" +
                "JOIN book b ON b.book_id = br.book_id \n" +
                "WHERE u.user_name = ?";

        Query query = session.createNativeQuery(sql);
        query.setParameter(1, username);
        List<Object[]> results = query.list();

        return results;
    }
}
