package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doctorspecialization")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    //Create DoctorSpecialization
    @PostMapping("/create")
    public ResponseEntity<ResponseData<Specialization>> createSpecialization(@RequestBody @Valid Specialization specialization, Errors errors){
        ResponseData<Specialization> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(specializationService.createSpecialization(specialization));
        return ResponseEntity.ok(responseData);
    }

    //update Patient
    @PutMapping("/update")
    public ResponseEntity<ResponseData<Specialization>> updateSpecialization(@RequestBody @Valid String specializationId, Specialization specialization){
        ResponseData<Specialization> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(specializationService.updateSpecialization(specializationId,specialization));
        return ResponseEntity.ok(responseData);
    }

    //findById DoctorSpecialization
    @GetMapping("/find/{specializationId}")
    public ResponseEntity<ResponseData<Specialization>> findIdSpecialization(@PathVariable String specializationId){
        ResponseData<Specialization> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(specializationService.getIdSpecialization(specializationId));
        return ResponseEntity.ok(responseData);
    }

    //findAll DoctorSpecialization
    @GetMapping("/findAll")
    public ResponseEntity <ResponseData<List<Specialization>>> findAllSpecialization(){
        ResponseData<List<Specialization>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(specializationService.getAllSpecialization());
        return ResponseEntity.ok(responseData);
    }

    //delete by id
    @DeleteMapping("/remove/{specializationId}")
    public ResponseEntity<String> removeIdSpecilization(@PathVariable ("specializationId") String specializationId){
        return new ResponseEntity<>(specializationService.removeSpecialization(specializationId),HttpStatus.OK);
    }
}
