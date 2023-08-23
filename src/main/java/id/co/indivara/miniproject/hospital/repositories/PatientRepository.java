package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
//    @Query(value = "INSERT INTO mst_patients(patient_id,patient_name,identity_card_number," +
//            "                email,phone_number,date_of_birth,gender,blood_type,patient_height,patient_weight," +
//            "                address)VALUES (?,?,?,?,?,?,?,?,?,?,?)", nativeQuery = true)
//    int create(Patient patient);
}
