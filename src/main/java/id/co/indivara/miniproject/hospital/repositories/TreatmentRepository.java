package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, String> {
}
