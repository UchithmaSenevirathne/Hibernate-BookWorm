package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDTO {
    private int branchId;
    private String branchName;
}
