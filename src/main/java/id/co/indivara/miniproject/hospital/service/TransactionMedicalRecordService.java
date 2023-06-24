package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.*;
import id.co.indivara.miniproject.hospital.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionMedicalRecordService {

    @Autowired
    private TransactionMedicalRecordRepository transactionMedicalRecordRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private TransactionAppointmentRepository trxAppointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    public MedicalRecord createMediacalRecord(MedicalRecord medicalRecord) {
        Appointment appointment = trxAppointmentRepository.findById(medicalRecord.getAppointment().getAppointmentId()).get();
        Treatment treatment = treatmentRepository.findById(medicalRecord.getTreatment().getTreatmentId()).get();
        medicalRecord.setAppointment(appointment);
        medicalRecord.setTreatment(treatment);
        appointment.setRegisterStatus(true);
        transactionMedicalRecordRepository.save(medicalRecord);
        return medicalRecord;
    }

    public List<MedicalRecord> getAllMedicalRecordPatient() {
        List<MedicalRecord> medicalRecords = transactionMedicalRecordRepository.findAll();
        return medicalRecords;
    }

    public List<MedicalRecord> getIdMedicalRecordPatient(String patientId){
        List<MedicalRecord> medicalRecords = transactionMedicalRecordRepository.getIdMedicalRecordPatient(patientId);
       return medicalRecords;
    }

}
