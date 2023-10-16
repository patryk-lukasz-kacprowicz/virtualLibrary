package pl.goooldzik.virtualLibrary.user.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.goooldzik.virtualLibrary.user.dto.UserDTO;
import pl.goooldzik.virtualLibrary.user.enums.UserStatusEnum;
import pl.goooldzik.virtualLibrary.user.http.request.UserStoreRequest;
import pl.goooldzik.virtualLibrary.user.http.request.UserUpdateRequest;
import pl.goooldzik.virtualLibrary.user.model.User;
import pl.goooldzik.virtualLibrary.user.repository.UserRepositoryContract;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepositoryContract userRepository;

    @Autowired
    public UserService(UserRepositoryContract userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> index(boolean showAll) {
        if (showAll) {
            return this.userRepository.findAllWithRelation();
        }
        return this.userRepository.findAllActiveWithRelation();
    }

    public User show(Long userId) {
        return this.userRepository.findById(userId)
                .filter(user -> UserStatusEnum.ACTIVE.equals(user.getStatus()))
                .orElseThrow(() -> new IllegalStateException("User does not exists!"));
    }

    public void store(UserStoreRequest request) {
        Optional<User> optionalUser = this.userRepository.findUserByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Email already taken!");
        }

        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), LocalDate.parse(request.getBirthdayDate()));

        this.userRepository.save(user);
    }

    @Transactional
    public void update(Long userId, UserUpdateRequest request) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String email = request.getEmail();
        LocalDate birthdayDate = LocalDate.parse(request.getBirthdayDate());

        User user = this.userRepository.findById(userId)
                .filter(u -> UserStatusEnum.ACTIVE.equals(u.getStatus()))
                .orElseThrow(() -> new IllegalStateException("User does not exists!"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = this.userRepository.findUserByEmail(email);

            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email has been taken!");
            }

            user.setEmail(email);
        }

        LocalDate today = LocalDate.now();

        if (birthdayDate.isBefore(today) && !Objects.equals(user.getBirthdayDate(), birthdayDate)) {
            user.setBirthdayDate(birthdayDate);
        }
    }

    @Transactional
    public boolean destroy(Long userId) {
        User user = this.userRepository.findById(userId)
                .filter(u -> UserStatusEnum.ACTIVE.equals(u.getStatus()))
                .orElseThrow(() -> new IllegalStateException("User does not exists or user status is inactive!"));

        user.setStatus(UserStatusEnum.INACTIVE);

        return true;
    }

    @Transactional
    public boolean recovery(Long userId) {
        User user = this.userRepository.findById(userId)
                .filter(u -> UserStatusEnum.INACTIVE.equals(u.getStatus()))
                .orElseThrow(() -> new IllegalStateException("User does not exists or user status is active"));

        user.setStatus(UserStatusEnum.ACTIVE);

        return true;
    }
}
