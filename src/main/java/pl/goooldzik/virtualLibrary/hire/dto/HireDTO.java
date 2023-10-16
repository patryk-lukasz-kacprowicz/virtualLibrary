package pl.goooldzik.virtualLibrary.hire.dto;

import org.apache.juli.logging.Log;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;

import java.time.LocalDate;

public class HireDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private HireStatusEnum status;
    private LocalDate dateHiredAt;
    private LocalDate dateReturnedAt;
    private LocalDate shouldReturnedAt;

    public HireDTO(Long id, Long userId, Long bookId, HireStatusEnum status, LocalDate dateHiredAt, LocalDate dateReturnedAt, LocalDate shouldReturnedAt) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
        this.dateHiredAt = dateHiredAt;
        this.dateReturnedAt = dateReturnedAt;
        this.shouldReturnedAt = shouldReturnedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public HireStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HireStatusEnum status) {
        this.status = status;
    }

    public LocalDate getDateHiredAt() {
        return dateHiredAt;
    }

    public void setDateHiredAt(LocalDate dateHiredAt) {
        this.dateHiredAt = dateHiredAt;
    }

    public LocalDate getDateReturnedAt() {
        return dateReturnedAt;
    }

    public void setDateReturnedAt(LocalDate dateReturnedAt) {
        this.dateReturnedAt = dateReturnedAt;
    }

    public LocalDate getShouldReturnedAt() {
        return shouldReturnedAt;
    }

    public void setShouldReturnedAt(LocalDate shouldReturnedAt) {
        this.shouldReturnedAt = shouldReturnedAt;
    }
}
