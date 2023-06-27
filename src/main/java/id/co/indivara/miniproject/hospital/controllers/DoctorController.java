package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    //Create Doctor
    @PostMapping("/create")
    public ResponseEntity<ResponseData<Doctor>> createDoctor(@Valid @RequestBody Doctor doctor, Errors errors){
        ResponseData<Doctor> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(doctorService.createDoctor(doctor));
        return ResponseEntity.ok(responseData);
    }

    //update Doctor
    @PutMapping("/update")
    public ResponseEntity<ResponseData<Doctor>> updateDoctor( String doctorId, @RequestBody @Valid Doctor doctor){
        ResponseData<Doctor> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(doctorService.updateDoctor(doctorId,doctor));
        return ResponseEntity.ok(responseData);
    }

    //findById Doctor
    @GetMapping("/find/{doctorId}")
    public ResponseEntity<ResponseData<Doctor>> findIdDoctor(@PathVariable String doctorId){
        ResponseData<Doctor> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(doctorService.getIdDoctor(doctorId));
        return ResponseEntity.ok(responseData);

    }

    //findAll Doctor
    @GetMapping("/findAll")
    public ResponseEntity<ResponseData<List<Doctor>>> findallDoctor(){
        ResponseData<List<Doctor>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(doctorService.getAllDoctor());
        return ResponseEntity.ok(responseData);
    }

    //delete by id
    @DeleteMapping("/remove/{doctorId}")
    public ResponseEntity<String> removeIdDoctor(@PathVariable ("doctorId") String doctorId){
        return new ResponseEntity<>(doctorService.removeDoctor(doctorId),HttpStatus.OK);
    }
}
