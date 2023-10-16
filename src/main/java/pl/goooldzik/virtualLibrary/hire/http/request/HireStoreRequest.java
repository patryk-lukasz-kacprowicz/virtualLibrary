package pl.goooldzik.virtualLibrary.hire.http.request;

import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;

public class HireStoreRequest {
    private HireStatusEnum status;
    private Long userId;
    private Long bookId;

    public HireStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HireStatusEnum status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
