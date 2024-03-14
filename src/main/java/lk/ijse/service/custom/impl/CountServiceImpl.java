package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.CountRepository;
import lk.ijse.service.custom.CountService;
import org.hibernate.Session;

public class CountServiceImpl implements CountService {

    private Session session;

    CountRepository countRepository = (CountRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.COUNT);

    @Override
    public int getUserCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        countRepository.setSession(session);

        int count = countRepository.getUserCount();
        session.close();
        return count;
    }

    @Override
    public int getBookCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        countRepository.setSession(session);

        int count = countRepository.getBookCount();
        session.close();
        return count;
    }

    @Override
    public int getBorrowCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        countRepository.setSession(session);

        int count = countRepository.getBorrowCount();
        session.close();
        return count;
    }

    @Override
    public int getReturnCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        countRepository.setSession(session);

        int count = countRepository.getReturnCount();
        session.close();
        return count;
    }

    @Override
    public int getBranchCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        countRepository.setSession(session);

        int count = countRepository.getBranchCount();
        session.close();
        return count;
    }
}
