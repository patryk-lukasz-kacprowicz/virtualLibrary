package pl.goooldzik.virtualLibrary.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import pl.goooldzik.virtualLibrary.hire.model.Hire;
import pl.goooldzik.virtualLibrary.user.enums.UserStatusEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "'ACTIVE'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatusEnum status = UserStatusEnum.ACTIVE;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "created_at")
    private final LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Hire> hires = new HashSet<>();

    public User(Long id,
                String firstName,
                String lastName,
                String email,
                LocalDate birthdayDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdayDate = birthdayDate;
    }

    public User(String firstName,
                String lastName,
                String email,
                LocalDate birthdayDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdayDate = birthdayDate;
    }

    public User() {}

    public Set<Hire> getHires() {
        return this.hires;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Long getId() {
        return id;
    }

    public UserStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }
}
