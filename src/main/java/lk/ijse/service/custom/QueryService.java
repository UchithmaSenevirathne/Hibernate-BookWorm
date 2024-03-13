package lk.ijse.service.custom;

import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.dto.OverDueDTO;
import lk.ijse.service.SuperService;

import java.util.List;

public interface QueryService extends SuperService {

    List<BorrowingDTO> getAllBorrowings();

    List<OverDueDTO> getAllOverDues();

    boolean checkOverDues();

    List<LibraryDTO> getLibrary(String username);
}
