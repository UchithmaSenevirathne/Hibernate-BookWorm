package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserDTO> getAdmins() {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userRepository.getAll()){
            if (user.getRole().equals("ADM") && !user.getPassword().equals("1234")){
                userDTOS.add(new UserDTO(
                        user.getUserName(),
                        user.getName(),
                        user.getPassword(),
                        user.getRole()
                ));
            }

        }
        transaction.commit();
        session.close();
        return userDTOS;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        userRepository.update(userDTO.toEntity());
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteAdmin(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        userRepository.deleteAdmin(username);
        transaction.commit();
        session.close();
    }

    @Override
    public String getName(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);

        User user = userRepository.getUser(username);

        String name = null;

        if (user.getUserName().equals(username)){
            name = user.getName();
        }
        transaction.commit();
        session.close();
        return name;
    }

    @Override
    public String getUserName(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);

        String id = null;

        for (User user : userRepository.getAll()){
            if (user.getName().equals(username)){
                id = user.getUserName();
            }
        }

        System.out.println("username : " + id);

        transaction.commit();
        session.close();
        return id;
    }
}
