package lk.ijse.service.custom.impl;

import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.service.custom.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    @Override
    public boolean searchUser(String userName, String password) {
       return userRepository.search(userName, password);
    }

    @Override
    public boolean checkAdmin(String userName, String password) {
        return userRepository.checkAdmin(userName, password);
    }
}
