package ma.enset.jpapracticen2.repositories;

import ma.enset.jpapracticen2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String name);
}
