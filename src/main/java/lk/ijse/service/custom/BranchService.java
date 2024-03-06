package lk.ijse.service.custom;

import lk.ijse.dto.BranchDTO;
import lk.ijse.service.SuperService;

import java.util.List;

public interface BranchService extends SuperService {
    List<BranchDTO> getAllBranches();

    boolean addBranch(BranchDTO branchDTO);

    void closeBranch(int id);

    int getId(String branchName);

}
