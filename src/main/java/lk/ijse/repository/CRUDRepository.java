package lk.ijse.repository;

public interface CRUDRepository <T> extends SuperRepository{
    boolean search(String userName, String password);

}
