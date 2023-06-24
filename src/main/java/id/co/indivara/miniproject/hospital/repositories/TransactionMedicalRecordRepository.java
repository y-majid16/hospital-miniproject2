package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionMedicalRecordRepository extends JpaRepository<MedicalRecord, String> {
    @Query(value = "Select * From mst_treatments a"+
    "JOIN trx_medical_records b ON a.treatment_id = b.treatment_id "+
    "JOIN trx_appointments c ON b.appointment_id = c.appointment_id "+
    "WHERE c.patient_id = :patientId", nativeQuery = true)
    List<MedicalRecord> getIdMedicalRecordPatient(@Param("patientId") String patientId);

}
