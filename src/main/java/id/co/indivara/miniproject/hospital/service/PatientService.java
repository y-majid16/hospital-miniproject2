package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired(required = false)
    private PatientRepository patientRepository;

    @Autowired
    JdbcTemplate jdbc;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }


    public Integer updatePatient( Patient patient) {
        Patient update = patientRepository.findById(patient.getPatientId()).get();
        if (update != null) {
            return jdbc.update("UPDATE mst_patients SET patient_name=?, identity_card_number=?," +
                            "email=?,phone_number=?,date_of_birth=?,gender=?,blood_type=?,patient_height=?,patient_weight=?," +
                            "address=? WHERE patient_id=?",
                    patient.getPatientName(),
                    patient.getIdentityCardNumber(),
                    patient.getEmail(),
                    patient.getPhoneNumber(),
                    patient.getDateOfBirth(),
                    patient.getGender(),
                    patient.getBloodType(),
                    patient.getPatientHeight(),
                    patient.getPatientWeight(),
                    patient.getAddress(),
                    patient.getPatientId());
        }
        throw new RuntimeException("Patient does not Exist.");
    }


    public Patient getIdPatient(String patientId) {
        Patient patient=(Patient) patientRepository.findById(patientId).orElseThrow(()->new RuntimeException("Data Not Found"));
        return patient;
    }


    public String removePatient(String patientId) {
        patientRepository.deleteById(patientId);
        return "Delete Data Success";
    }


    public List<Patient> getAllPatient() {
        List<Patient> patients=patientRepository.findAll();
        return patients;
    }
}
