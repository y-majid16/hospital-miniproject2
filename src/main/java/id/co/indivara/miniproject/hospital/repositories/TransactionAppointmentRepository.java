package id.co.indivara.miniproject.hospital.repositories;

import id.co.indivara.miniproject.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAppointmentRepository extends JpaRepository<Appointment, String> {
}
