package lk.ijse.service;

import lk.ijse.service.custom.impl.BookServiceImpl;
import lk.ijse.service.custom.impl.BranchServiceImpl;
import lk.ijse.service.custom.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory() {
        return (serviceFactory == null)? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum ServiceTypes{
        USER,BOOK,BRANCH
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER:
                return new UserServiceImpl();
            case BOOK:
                return new BookServiceImpl();
            case BRANCH:
                return new BranchServiceImpl();
            default:
                return null;
        }
    }
}
