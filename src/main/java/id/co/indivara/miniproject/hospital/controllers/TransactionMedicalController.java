package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.entity.MedicalRecord;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.repositories.TransactionMedicalRecordRepository;
import id.co.indivara.miniproject.hospital.service.TransactionMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicalrecord")
public class TransactionMedicalController {
    @Autowired
    private TransactionMedicalRecordRepository transactionMedicalRecordRepository;
    @Autowired
    private TransactionMedicalRecordService transactionMedicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<MedicalRecord>> createMedicalRecord(@RequestBody MedicalRecord medicalRecord, Errors errors){
        ResponseData<MedicalRecord> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(transactionMedicalRecordService.createMediacalRecord(medicalRecord));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/MedicalRecordPatient")
    public ResponseEntity<ResponseData<List<MedicalRecord>>> getAllMedicalRecordPatient(){
        ResponseData<List<MedicalRecord>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(transactionMedicalRecordService.getAllMedicalRecordPatient());
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/MedicalRecordPatientg/{patientId}")
    public ResponseEntity<List<MedicalRecord>> findIdMedicalRecordPatient(@PathVariable("patientId") String patientId){
        return new ResponseEntity<>(transactionMedicalRecordService.getIdMedicalRecordPatient(patientId),HttpStatus.OK);
    }

}
