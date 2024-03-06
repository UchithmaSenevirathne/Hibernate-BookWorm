package lk.ijse.repository.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.custom.BranchRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {

    private Session session;
    @Override
    public boolean save(Branch entity) {
        int id = (Integer) session.save(entity);
        if (id > 0){
            return true;
        }
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Branch> getAll() {
        String sql = "FROM branch";
        Query query = session.createQuery(sql);
        List<Branch> list = query.list();
        return list;
    }

    @Override
    public void update(Branch entity) {

    }

    @Override
    public void delete(int id) {
        Branch branch = session.get(Branch.class, id);
        session.delete(branch);
    }

    @Override
    public Branch getBranch(int branchId) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Branch branch = session.get(Branch.class, branchId);
        System.out.println("branch * "+branch);
        transaction.commit();
        session.close();
        return branch;
    }

    @Override
    public int getId(String branchName) {
        String sql = "FROM branch";
        Query query = session.createQuery(sql);
        List<Branch> list = query.list();

        for (Branch branch : list){
            if (branch.getBranchName().equals(branchName)){
                return branch.getBranchId();
            }
        }
        return 0;
    }
}
