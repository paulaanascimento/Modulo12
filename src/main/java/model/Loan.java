package model;

import java.sql.Date;

public class Loan {
    private Long id;
    private final Date startDate;
    private final Date returnDate;
    private final Long bookCode;
    private final Long userId;

    public Loan(Date startDate, Date returnDate, Long bookCode, Long userId) {
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.bookCode = bookCode;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }


    public Long getBookCode() {
        return bookCode;
    }


    public Long getUserId() {
        return userId;
    }

}
