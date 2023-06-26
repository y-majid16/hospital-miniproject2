package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    //Create Patient
    @PostMapping("/create")
    public ResponseEntity<ResponseData<Patient>> createPatient(@RequestBody @Valid Patient patient, Errors errors){
        ResponseData<Patient> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(patientService.createPatient(patient));
        return ResponseEntity.ok(responseData);
    }

    //update Patient
    @PutMapping("/update")
    public ResponseEntity<ResponseData<Patient>> updatePatient( @RequestBody @Valid String patientId, Patient patient, Errors errors){
        ResponseData<Patient> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(patientService.updatePatient(patientId,patient));
        return ResponseEntity.ok(responseData);
    }

    //findById Patient
    @GetMapping("/find/{patientId}")
    public ResponseEntity<ResponseData<Patient>> findIdPatient(@PathVariable String patientId){
        ResponseData<Patient> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(patientService.getIdPatient(patientId));
        return ResponseEntity.ok(responseData);
    }

    //findAll Patient
    @GetMapping("/findAll")
    public ResponseEntity <ResponseData<List<Patient>>> findAllPatient(){
        ResponseData<List<Patient>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(patientService.getAllPatient());
        return ResponseEntity.ok(responseData);
    }

    //delete by id
    @DeleteMapping("/remove/{patientId}")
    public ResponseEntity<String> removeIdPatient(@PathVariable ("patientId") String patientId){
        return new ResponseEntity<>(patientService.removePatient(patientId),HttpStatus.OK);
    }
}



