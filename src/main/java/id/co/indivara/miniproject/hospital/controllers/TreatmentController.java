package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;
    //Create Treatment
    @PostMapping("/create")
    public ResponseEntity<ResponseData<Treatment>> createTreatment(@RequestBody @Valid Treatment treatment, Errors errors){
        ResponseData<Treatment> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(treatmentService.createTreatment(treatment));
        return ResponseEntity.ok(responseData);
    }

    //update Treatment
    @PutMapping("/update")
    public ResponseEntity<ResponseData<Treatment>> updateTreatment( @RequestBody @Valid  String treatmentId,Treatment treatment, Errors errors){
        ResponseData<Treatment> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(treatmentService.updateTreatment(treatmentId,treatment));
        return ResponseEntity.ok(responseData);
    }

    //findById Treatment
    @GetMapping("/find/{treatmentId}")
    public ResponseEntity<ResponseData<Treatment>> findIdTreatment(@PathVariable String treatmentId){
        ResponseData<Treatment> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(treatmentService.getIdTreatment(treatmentId));
        return ResponseEntity.ok(responseData);
    }

    //findAll Treatment
    @GetMapping("/findAll")
    public ResponseEntity <ResponseData<List<Treatment>>> findAllTreatment(){
        ResponseData<List<Treatment>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(treatmentService.getAllTreatment());
        return ResponseEntity.ok(responseData);
    }

    //delete by id
    @DeleteMapping("/remove/{treatmentId}")
    public ResponseEntity<String> removeIdTreatment(@PathVariable ("treatmentId") String treatmentId){
        return new ResponseEntity<>(treatmentService.removeTreatment(treatmentId),HttpStatus.OK);
    }
}
