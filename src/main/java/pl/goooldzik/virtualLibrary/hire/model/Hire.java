package pl.goooldzik.virtualLibrary.hire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import pl.goooldzik.virtualLibrary.book.model.Book;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;
import pl.goooldzik.virtualLibrary.user.model.User;

import java.time.LocalDate;

@Entity
@Table(name = "hires")
public class Hire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "'HIRED'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HireStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

    @Column(name = "date_hired_at")
    private final LocalDate dateHiredAt = LocalDate.now();

    @Column(name = "should_returned_at")
    private LocalDate shouldReturnedAt = LocalDate.now().plusDays(60);

    @Column(name = "date_returned_at", nullable = true)
    private LocalDate dateReturnedAt;

    public Hire(Long id, HireStatusEnum status, User user, Book book) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.book = book;
    }

    public Hire(HireStatusEnum status, User user, Book book) {
        this.status = status;
        this.user = user;
        this.book = book;
    }

    public Hire() {}

    public Long getId() {
        return this.id;
    }

    public HireStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(HireStatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateHiredAt() {
        return this.dateHiredAt;
    }

    public LocalDate getShouldReturnedAt() {
        return this.shouldReturnedAt;
    }

    public void setShouldReturnedAt(LocalDate shouldReturnedAt) {
        this.shouldReturnedAt = shouldReturnedAt;
    }

    public LocalDate getDateReturnedAt() {
        return this.dateReturnedAt;
    }

    public void setDateReturnedAt(LocalDate dateReturnedAt) {
        this.dateReturnedAt = dateReturnedAt;
    }
}
