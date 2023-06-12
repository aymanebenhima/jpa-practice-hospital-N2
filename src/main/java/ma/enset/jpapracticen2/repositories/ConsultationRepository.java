package ma.enset.jpapracticen2.repositories;

import ma.enset.jpapracticen2.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
