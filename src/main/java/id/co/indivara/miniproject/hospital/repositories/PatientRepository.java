package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
