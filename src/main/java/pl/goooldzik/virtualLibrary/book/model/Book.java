package pl.goooldzik.virtualLibrary.book.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import pl.goooldzik.virtualLibrary.book.enums.BookStatusEnum;
import pl.goooldzik.virtualLibrary.hire.model.Hire;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "'ACTIVE'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatusEnum status = BookStatusEnum.ACTIVE;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private int amount;

    @Column(name = "created_at")
    private final LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Hire> hires = new HashSet<>();

    public Book(Long id, String author, String title, int amount) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.amount = amount;
    }

    public Book(String author, String title, int amount) {
        this.author = author;
        this.title = title;
        this.amount = amount;
    }

    public Book() {

    }

    public Set<Hire> getHires() {
        return this.hires;
    }

    public Long getId() {
        return this.id;
    }

    public BookStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(BookStatusEnum status) {
        this.status = status;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public boolean isAvailable() {
        return !(this.amount <= 0);
    }

    public void reduceAmount() {
        this.amount--;
    }

    public void increaseAmount() {
        this.amount++;
    }

}
