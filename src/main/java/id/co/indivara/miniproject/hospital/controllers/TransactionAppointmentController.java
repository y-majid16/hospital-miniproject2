package id.co.indivara.miniproject.hospital.controllers;

import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.models.ResponseData;
import id.co.indivara.miniproject.hospital.service.TransactionAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class TransactionAppointmentController {
    @Autowired
    private TransactionAppointmentService transactionAppointmentService;
    @PostMapping("/create")
    public ResponseEntity<ResponseData<Appointment>> createAppointment(@RequestBody Appointment appointment, Errors errors){
        ResponseData<Appointment> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(transactionAppointmentService.createAppointment(appointment));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/DoctorSchedule")
    public ResponseEntity<ResponseData<List<Appointment>>> findAllDoctorSchedulefing(){
        ResponseData<List<Appointment>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setData(transactionAppointmentService.getAllScheduleDoctor());
        return ResponseEntity.ok(responseData);
    }
}
