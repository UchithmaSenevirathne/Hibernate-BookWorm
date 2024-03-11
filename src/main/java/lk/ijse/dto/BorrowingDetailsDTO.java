package lk.ijse.dto;

import lk.ijse.embedded.BorrowingDetailPK;

import java.sql.Timestamp;

public class BorrowingDetailsDTO {

    private BorrowingDetailPK borrowingDetailPK;

    private Timestamp borrowDate;

    private Timestamp dueDate;

    private Timestamp returnDate;

    public BorrowingDetailsDTO() {
    }

    public BorrowingDetailsDTO(BorrowingDetailPK borrowingDetailPK, Timestamp borrowDate, Timestamp dueDate, Timestamp returnDate) {
        this.borrowingDetailPK = borrowingDetailPK;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public BorrowingDetailPK getBorrowingDetailPK() {
        return borrowingDetailPK;
    }

    public void setBorrowingDetailPK(BorrowingDetailPK borrowingDetailPK) {
        this.borrowingDetailPK = borrowingDetailPK;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }
}
