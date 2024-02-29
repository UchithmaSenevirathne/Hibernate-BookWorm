package lk.ijse.repository;

import lk.ijse.repository.custom.impl.UserRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;

    private RepositoryFactory(){}

    public static RepositoryFactory getRepositoryFactory() {
        return (repositoryFactory == null)? repositoryFactory = new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        USER
    }

    public SuperRepository getRepository(RepositoryTypes repositoryTypes){

        switch (repositoryTypes){
            case USER:
                return new UserRepositoryImpl();
            default:
                return null;
        }
    }
}