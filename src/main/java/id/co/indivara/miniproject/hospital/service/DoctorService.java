package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repositories.DoctorRepository;
import id.co.indivara.miniproject.hospital.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;


    public Doctor createDoctor(Doctor doctor) {
        Specialization specialization = specializationRepository.findById(doctor.getSpecialization().getSpecializationId()).get();
        doctor.setSpecialization(specialization);
        return doctorRepository.save(doctor);
    }


    public Doctor updateDoctor(String doctorId, Doctor doctor) {
        Doctor update = doctorRepository.findById(doctor.getDoctorId()).get();
        Specialization specialization= specializationRepository.findById(doctor.getSpecialization()
                .getSpecializationId())
                .get();
        update.setName(doctor.getName());
        update.setEmail(doctor.getEmail());
        update.setPhoneNumber(doctor.getPhoneNumber());
        update.setDateOfBirth(doctor.getDateOfBirth());
        update.setGender(doctor.getGender());
        update.setAddress(doctor.getAddress());
        doctorRepository.save(doctor);
        return update;
    }

    public Doctor getIdDoctor(String doctorId) {
        Doctor doctor=(Doctor)doctorRepository.findById(doctorId).orElseThrow(()->new RuntimeException("Data Not Found"));;
        return doctor;
    }


    public String removeDoctor(String doctorId) {
        doctorRepository.deleteById(doctorId);
        return "Delete Data Success";
    }

    public List<Doctor> getAllDoctor() {
        List<Doctor> doctors=doctorRepository.findAll();
        return doctors;
    }
}
