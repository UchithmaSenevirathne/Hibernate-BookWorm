package lk.ijse.service.custom;

import lk.ijse.dto.BorrowingDTO;
import lk.ijse.service.SuperService;

import java.util.List;

public interface QueryService extends SuperService {

    List<BorrowingDTO> getAllBorrowings();
}
