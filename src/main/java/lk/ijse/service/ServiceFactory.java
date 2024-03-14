package lk.ijse.service;

import lk.ijse.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory() {
        return (serviceFactory == null)? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum ServiceTypes{
        USER,BOOK,BRANCH,BORROWINGDETAILS,QUERY,COUNT
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER:
                return new UserServiceImpl();
            case BOOK:
                return new BookServiceImpl();
            case BRANCH:
                return new BranchServiceImpl();
            case BORROWINGDETAILS:
                return new BorrowingDetailsServiceImpl();
            case QUERY:
                return new QueryServiceImpl();
            case COUNT:
                return new CountServiceImpl();
            default:
                return null;
        }
    }
}
