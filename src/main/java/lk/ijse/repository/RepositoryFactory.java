package lk.ijse.repository;

import lk.ijse.repository.custom.impl.*;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;

    private RepositoryFactory(){}

    public static RepositoryFactory getRepositoryFactory() {
        return (repositoryFactory == null)? repositoryFactory = new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        USER,BOOK,BRANCH,BORROWINGDETAILS,QUERY
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
            case QUERY:
                return new QueryRepositoryImpl();
            default:
                return null;
        }
    }
}
