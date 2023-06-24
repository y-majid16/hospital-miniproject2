package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.*;
import id.co.indivara.miniproject.hospital.repositories.DoctorRepository;
import id.co.indivara.miniproject.hospital.repositories.PatientRepository;
import id.co.indivara.miniproject.hospital.repositories.TransactionAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionAppointmentService  {
    @Autowired
    private TransactionAppointmentRepository transactionAppointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;


    public Appointment createAppointment(Appointment appointment) {
        Patient patient= patientRepository.findById(appointment.getPatient().getPatientId()).get();
        Doctor doctor=doctorRepository.findById(appointment.getDoctor().getDoctorId()).get();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setRegisterStatus(false);
        transactionAppointmentRepository.save(appointment);
        return appointment;
    }

    public List<Appointment> getAllScheduleDoctor() {
        List<Appointment> appointments = transactionAppointmentRepository.findAll();
        return appointments;
    }
}
