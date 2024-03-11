package lk.ijse.service.custom;

import lk.ijse.dto.BorrowingDetailsDTO;
import lk.ijse.service.SuperService;

public interface BorrowingDetailsService extends SuperService {
    boolean save(BorrowingDetailsDTO borrowingDetailsDTO);

}
