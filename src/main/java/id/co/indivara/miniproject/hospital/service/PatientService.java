package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }


    public Patient updatePatient(String patientId, Patient patient) {
        Patient update = patientRepository.findById(patient.getPatientId()).orElse(null);
        if (update != null) {
            update.setPatientName(patient.getPatientName());
            update.setIdentityCardNumber(patient.getIdentityCardNumber());
            update.setEmail(patient.getEmail());
            update.setPhoneNumber(patient.getPhoneNumber());
            update.setDateOfBirth(patient.getDateOfBirth());
            update.setGender(patient.getGender());
            update.setBloodType(patient.getBloodType());
            update.setPatientHeight(patient.getPatientHeight());
            update.setPatientWeight(patient.getPatientWeight());
            update.setAddress(patient.getAddress());
            update.setEmergencyContactNumber(patient.getEmergencyContactNumber());
            patientRepository.save(patient);
            return update;
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
