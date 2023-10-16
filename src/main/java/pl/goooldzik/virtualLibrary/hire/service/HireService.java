package pl.goooldzik.virtualLibrary.hire.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.goooldzik.virtualLibrary.book.enums.BookStatusEnum;
import pl.goooldzik.virtualLibrary.book.model.Book;
import pl.goooldzik.virtualLibrary.book.repository.BookRepositoryContract;
import pl.goooldzik.virtualLibrary.hire.dto.HireDTO;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;
import pl.goooldzik.virtualLibrary.hire.http.request.HireStoreRequest;
import pl.goooldzik.virtualLibrary.hire.http.request.HireUpdateRequest;
import pl.goooldzik.virtualLibrary.hire.model.Hire;
import pl.goooldzik.virtualLibrary.hire.repository.HireRepositoryContract;
import pl.goooldzik.virtualLibrary.user.enums.UserStatusEnum;
import pl.goooldzik.virtualLibrary.user.model.User;
import pl.goooldzik.virtualLibrary.user.repository.UserRepositoryContract;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class HireService {

    private final HireRepositoryContract hireRepository;

    private final BookRepositoryContract bookRepository;

    private final UserRepositoryContract userRepository;

    @Autowired
    public HireService(HireRepositoryContract hireRepository, BookRepositoryContract bookRepository, UserRepositoryContract userRepository) {
        this.hireRepository = hireRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<HireDTO> index(String status) {
        if (!Objects.equals(status, "any")) {
            return this.hireRepository.findAllByStatusWithRelation(status);
        }

        return this.hireRepository.findAllWithRelation();
    }

    public Hire show(Long hireId) {
        return this.hireRepository.findById(hireId)
                .orElseThrow(() -> new IllegalStateException("Hire does not exists!"));
    }

    public void store(HireStoreRequest request) {
        User user = this.userRepository.findById(request.getUserId())
                .filter(u -> UserStatusEnum.ACTIVE.equals(u.getStatus()))
                .orElseThrow(() -> new IllegalStateException("User does not exists!"));

        Book book = this.bookRepository.findById(request.getBookId())
                .filter(b -> BookStatusEnum.ACTIVE.equals(b.getStatus()))
                .orElseThrow(() -> new IllegalStateException("Book does not exists!"));

        Hire hire = new Hire(request.getStatus(), user, book);

        this.hireRepository.save(hire);
    }

    public boolean destroy(Long hireId) {
        Hire hire = this.hireRepository.findById(hireId)
                .orElseThrow(() -> new IllegalStateException("Hire does not exists!"));

        this.hireRepository.delete(hire);

        return  true;
    }

    @Transactional
    public void changeStatus(Long hireID, HireUpdateRequest request) {
        Hire hire = this.hireRepository.findById(hireID)
                .orElseThrow(() -> new IllegalStateException("Hire does not exists!"));

        if (request.getStatus().equals(HireStatusEnum.EXTENDED)) {
            LocalDate extendedDate = hire.getShouldReturnedAt().plusDays(14);

            hire.setShouldReturnedAt(extendedDate);
            hire.setStatus(HireStatusEnum.EXTENDED);
        }

        if (request.getStatus().equals(HireStatusEnum.RETURNED)) {
            Book book = hire.getBook();

            hire.setDateReturnedAt(LocalDate.now());
            hire.setStatus(HireStatusEnum.RETURNED);

            book.increaseAmount();
        }

        if (request.getStatus().equals(HireStatusEnum.NOT_RETURNED)) {
            hire.setStatus(HireStatusEnum.NOT_RETURNED);
        }
    }
}
