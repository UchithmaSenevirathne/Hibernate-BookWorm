package lk.ijse.repository.custom.impl;

import lk.ijse.repository.custom.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean search(String userName, String password) {
        return false;
    }

    @Override
    public boolean checkAdmin(String userName, String password) {
        return false;
    }
}
