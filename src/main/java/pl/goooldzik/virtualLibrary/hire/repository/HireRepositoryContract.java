package pl.goooldzik.virtualLibrary.hire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.goooldzik.virtualLibrary.hire.dto.HireDTO;
import pl.goooldzik.virtualLibrary.hire.model.Hire;

import java.util.List;

@Repository
public interface HireRepositoryContract
        extends JpaRepository<Hire, Long> {

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.hire.dto.HireDTO(h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM Hire h WHERE h.status = ?1")
    List<HireDTO> findAllByStatusWithRelation(String status);

    @Query("SELECT NEW pl.goooldzik.virtualLibrary.hire.dto.HireDTO(h.id, h.user.id, h.book.id, h.status, h.dateHiredAt, h.dateReturnedAt, h.shouldReturnedAt) FROM Hire h")
    List<HireDTO> findAllWithRelation();
}
