package lk.ijse.repository;

import lk.ijse.repository.custom.impl.BookRepositoryImpl;
import lk.ijse.repository.custom.impl.BorrowingDetailsRepositoryImpl;
import lk.ijse.repository.custom.impl.BranchRepositoryImpl;
import lk.ijse.repository.custom.impl.UserRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;

    private RepositoryFactory(){}

    public static RepositoryFactory getRepositoryFactory() {
        return (repositoryFactory == null)? repositoryFactory = new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        USER,BOOK,BRANCH,BORROWINGDETAILS
    }

    public SuperRepository getRepository(RepositoryTypes repositoryTypes){

        switch (repositoryTypes){
            case USER:
                return new UserRepositoryImpl();
            case BOOK:
                return new BookRepositoryImpl();
            case BRANCH:
                return new BranchRepositoryImpl();
            case BORROWINGDETAILS:
                return new BorrowingDetailsRepositoryImpl();
            default:
                return null;
        }
    }
}
