package lk.ijse.repository.custom;

import lk.ijse.entity.BorrowingDetails;
import lk.ijse.repository.CRUDRepository;

public interface BorrowingDetailsRepository extends CRUDRepository<BorrowingDetails> {
    BorrowingDetails getBorrowing(int id);
}
