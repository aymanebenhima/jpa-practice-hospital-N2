package ma.enset.jpapracticen2.repositories;

import ma.enset.jpapracticen2.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String name);
}
