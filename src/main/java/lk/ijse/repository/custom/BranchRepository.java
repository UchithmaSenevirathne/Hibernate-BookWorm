package lk.ijse.repository.custom;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.CRUDRepository;

public interface BranchRepository  extends CRUDRepository<Branch> {
    Branch getBranch(int branchId);

    int getId(String branchName);

}
