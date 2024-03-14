package lk.ijse.service.custom;

import lk.ijse.service.SuperService;

public interface CountService extends SuperService {
    int getUserCount();

    int getBookCount();

    int getBorrowCount();

    int getReturnCount();

    int getBranchCount();
}
