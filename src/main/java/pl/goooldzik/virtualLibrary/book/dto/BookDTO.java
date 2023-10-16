package pl.goooldzik.virtualLibrary.book.dto;

import pl.goooldzik.virtualLibrary.book.enums.BookStatusEnum;
import pl.goooldzik.virtualLibrary.hire.dto.HireDTO;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    private String author;
    private String title;
    private Integer amount;
    private BookStatusEnum status;
    private LocalDate createdAt;
    private HireDTO hireDTO;

    public BookDTO(
            Long id,
            String author,
            String title,
            Integer amount,
            BookStatusEnum status,
            LocalDate createdAt,
            Long hireId,
            Long userId,
            Long bookId,
            HireStatusEnum hireStatus,
            LocalDate hireDateHiredAt,
            LocalDate hireDateReturnedAt,
            LocalDate hireShouldReturnedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;

        if (hireId != null) {
            this.hireDTO = new HireDTO(hireId, userId, bookId, hireStatus, hireDateHiredAt, hireDateReturnedAt, hireShouldReturnedAt);
        } else {
            this.hireDTO = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BookStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookStatusEnum status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public HireDTO getHireDTO() {
        return hireDTO;
    }

    public void setHireDTO(HireDTO hireDTO) {
        this.hireDTO = hireDTO;
    }
}
