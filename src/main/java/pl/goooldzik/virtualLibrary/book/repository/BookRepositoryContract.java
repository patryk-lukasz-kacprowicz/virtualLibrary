package pl.goooldzik.virtualLibrary.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.goooldzik.virtualLibrary.book.dto.BookDTO;
import pl.goooldzik.virtualLibrary.book.model.Book;

import java.util.List;

@Repository
public interface BookRepositoryContract
        extends JpaRepository<Book, Long> {

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.book.dto.BookDTO(b.id, b.author, b.title, b.amount, b.status, b.createdAt, h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM Book b LEFT JOIN b.hires h WHERE b.id = ?1 AND b.status = 'ACTIVE'")
    BookDTO findByIdWithRelation(Long id);

    @Query("SELECT b FROM Book b WHERE b.status = 'ACTIVE'")
    List<Book> findAllActive();

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.book.dto.BookDTO(b.id, b.author, b.title, b.amount, b.status, b.createdAt, h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM Book b LEFT JOIN b.hires h WHERE b.status = 'ACTIVE'")
    List<BookDTO> findAllActiveWithRelation();

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.book.dto.BookDTO(b.id, b.author, b.title, b.amount, b.status, b.createdAt, h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM Book b LEFT JOIN b.hires h")
    List<BookDTO> findAllWithRelation();
}
