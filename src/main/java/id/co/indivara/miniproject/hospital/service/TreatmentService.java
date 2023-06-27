package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }


    public Treatment updateTreatment(String treatmentId, Treatment treatment) {
        Treatment update = treatmentRepository.findById(treatment.getTreatmentId()).orElse(null);
        if (update != null) {
            update.setTreatmentType(treatment.getTreatmentType());
            update.setDiagnosis(treatment.getDiagnosis());
            update.setMedication(treatment.getMedication());
            update.setDosage(treatment.getDosage());
            treatmentRepository.save(treatment);
            return update;
        }
        throw new RuntimeException("Treatment does not Exist.");
    }


    public Treatment getIdTreatment(String treatmentId) {
        Treatment treatment=(Treatment) treatmentRepository.findById(treatmentId).orElseThrow(()->new RuntimeException("Data Not Found"));
        return treatment;
    }


    public String removeTreatment(String treatmentId) {
        treatmentRepository.deleteById(treatmentId);
        return "Delete Data Success";
    }


    public List<Treatment> getAllTreatment() {
        List<Treatment> treatments=treatmentRepository.findAll();
        return treatments;
    }
}
