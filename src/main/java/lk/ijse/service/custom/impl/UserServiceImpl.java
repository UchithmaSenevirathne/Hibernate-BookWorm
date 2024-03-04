package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.UserDTO;
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
    public boolean searchUser(String userName, String password) {
        session = SessionFactoryConfig.getInstance().getSession();
            userRepository.setSession(session);
            boolean isUser = userRepository.search(userName, password);
//            transaction.commit();
            session.close();
            return isUser;
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

    @Override
    public boolean saveUser(UserDTO userDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        boolean save = userRepository.save(userDTO.toEntity());
        transaction.commit();
        session.close();
        return save;
    }
}
