package pl.goooldzik.virtualLibrary.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.goooldzik.virtualLibrary.user.dto.UserDTO;
import pl.goooldzik.virtualLibrary.user.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryContract
        extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
    List<User> findAllActive();

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.user.dto.UserDTO(u.id, u.firstName, u.lastName, u.email, u.status, u.birthdayDate, h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM User u LEFT JOIN u.hires h WHERE u.status = 'ACTIVE'")
    List<UserDTO> findAllActiveWithRelation();

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.user.dto.UserDTO(u.id, u.firstName, u.lastName, u.email, u.status, u.birthdayDate, h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM User u LEFT JOIN u.hires h")
    List<UserDTO> findAllWithRelation();
}
