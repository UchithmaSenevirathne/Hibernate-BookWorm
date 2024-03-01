package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    private Session session;
    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    @Override
    public boolean searchUser(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            User user = userRepository.search(userName);
//            transaction.commit();
            session.close();

            if (user!=null){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
//            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkAdmin(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
//        Transaction transaction = session.beginTransaction();
        boolean admin = userRepository.checkAdmin(userName);
        session.close();
        return admin;
    }
}
