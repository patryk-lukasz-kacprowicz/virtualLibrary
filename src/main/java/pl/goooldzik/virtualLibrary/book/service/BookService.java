package pl.goooldzik.virtualLibrary.book.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.goooldzik.virtualLibrary.book.dto.BookDTO;
import pl.goooldzik.virtualLibrary.book.enums.BookStatusEnum;
import pl.goooldzik.virtualLibrary.book.model.Book;
import pl.goooldzik.virtualLibrary.book.repository.BookRepositoryContract;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepositoryContract bookRepository;

    @Autowired
    public BookService(BookRepositoryContract bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> index(boolean showAll) {
        if (showAll) {
            return bookRepository.findAllWithRelation();
        }
        return bookRepository.findAllActiveWithRelation();
    }

    public BookDTO show(Long bookId) {
        return this.bookRepository.findByIdWithRelation(bookId);
    }

    public void store(String author, String title, Integer amount) {
        Book book = new Book(author, title, amount);

        this.bookRepository.save(book);
    }

    @Transactional
    public void update(Long bookId, String author, String title, Integer amount) {
        Book book = this.bookRepository.findById(bookId)
                .filter(b -> BookStatusEnum.ACTIVE.equals(b.getStatus()))
                .orElseThrow(() -> new IllegalStateException("Book does not exists!"));

        if (author != null &&
                author.length() > 0 &&
                !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }

        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if (amount != null &&
                amount >= 0 &&
                !Objects.equals(book.getAmount(), amount)) {
            book.setAmount(amount);
        }
    }

    @Transactional
    public boolean destroy(Long bookId) {
        Book book = this.bookRepository.findById(bookId)
                .filter(b -> BookStatusEnum.ACTIVE.equals(b.getStatus()))
                .orElseThrow(() -> new IllegalStateException("Book does not exists or book status is inactive!"));

        book.setStatus(BookStatusEnum.INACTIVE);

        return true;
    }

    @Transactional
    public boolean recovery(Long bookId) {
        Book book = this.bookRepository.findById(bookId)
                .filter(b -> BookStatusEnum.INACTIVE.equals(b.getStatus()))
                .orElseThrow(() -> new IllegalStateException("Book does not exists or book status is active!"));

        book.setStatus(BookStatusEnum.ACTIVE);

        return true;
    }
}
