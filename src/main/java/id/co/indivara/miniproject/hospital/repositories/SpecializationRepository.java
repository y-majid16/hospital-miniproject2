package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, String> {
}
