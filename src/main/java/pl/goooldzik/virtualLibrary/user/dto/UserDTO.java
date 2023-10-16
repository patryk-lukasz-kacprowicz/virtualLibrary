package pl.goooldzik.virtualLibrary.user.dto;

import pl.goooldzik.virtualLibrary.hire.dto.HireDTO;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;
import pl.goooldzik.virtualLibrary.user.enums.UserStatusEnum;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatusEnum status;
    private LocalDate birthdayDate;
    private HireDTO hireDTO;

    public UserDTO(
            Long id,
            String firstName,
            String lastName,
            String email,
            UserStatusEnum status,
            LocalDate birthdayDate,
            Long hireId,
            Long userId,
            Long bookId,
            HireStatusEnum hireStatus,
            LocalDate hireDateHiredAt,
            LocalDate hireDateReturnedAt,
            LocalDate hireShouldReturnedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.birthdayDate = birthdayDate;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public HireDTO getHireDTO() {
        return hireDTO;
    }

    public void setHireDTO(HireDTO hireDTO) {
        this.hireDTO = hireDTO;
    }
}
