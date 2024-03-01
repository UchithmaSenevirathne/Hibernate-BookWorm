package lk.ijse.repository;

import lk.ijse.entity.User;

public interface CRUDRepository <T> extends SuperRepository{
    User search(String password);

}
