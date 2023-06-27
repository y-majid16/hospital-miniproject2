package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;


    public Specialization createSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }


    public Specialization updateSpecialization(String specializationId, Specialization specialization) {
        Specialization update = specializationRepository.findById(specialization.getSpecializationId()).orElse(null);
        if (update != null) {
            update.setSpecializationName(specialization.getSpecializationName());
            specializationRepository.save(specialization);
            return update;
        }
        throw new RuntimeException("Specialization does not Exist.");
    }


    public Specialization getIdSpecialization(String specializationId) {
        Specialization specialization=(Specialization) specializationRepository.findById(specializationId).orElseThrow(()->new RuntimeException("Data Not Found"));
        return specialization;
    }

    public String removeSpecialization(String specializationId) {
        specializationRepository.deleteById(specializationId);
        return "Delete Data Success";
    }

    public List<Specialization> getAllSpecialization() {
        List<Specialization> specializations=specializationRepository.findAll();
        return specializations;
    }
}
