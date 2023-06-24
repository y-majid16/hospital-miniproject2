package id.co.indivara.miniproject.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.miniproject.hospital.controllers.TransactionAppointmentController;
import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repositories.DoctorRepository;
import id.co.indivara.miniproject.hospital.repositories.PatientRepository;
import id.co.indivara.miniproject.hospital.repositories.TransactionAppointmentRepository;
import id.co.indivara.miniproject.hospital.service.TransactionAppointmentService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionAppointmentService transactionAppointmentService;

    @InjectMocks
    private TransactionAppointmentController transactionAppointmentController;

    private TransactionAppointmentRepository transactionAppointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Before("")
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionAppointmentController).build();
    }

    @Test
    public void testCreateAppointment() throws Exception {
        // Mock data
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("4028818d88e6251f0188e627f07c0001");
        appointment.setPatient(new Patient("4028818d88e5ff130188e6011d470001", "Abi","327324232222223","abi@gmail.com", "081323232323","1977-06-23","Man","B+",166,59,"Jln, Jakarta no 3","081883233232"));
        appointment.setDoctor(new Doctor("4028818d88e6115d0188e6143e720000",new Specialization("4028818d88e5ff130188e60216590003","Penyakit Saraf"),"Richard","richard@gmail.com","08123232323","1997-03-12","Man", "Jln, Jakarta no 78"));
        appointment.setComplaint("Complaint");
        appointment.setStartTime("09:30");
        appointment.setEndTime("10:00");
        appointment.setRegisterStatus(false);


        // Mock service response
        Mockito.when(transactionAppointmentService.createAppointment(Mockito.any(Appointment.class)))
                .thenReturn(appointment);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/appointment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(appointment)))
                .andExpect(status().isOk());
    }
    @Test
    public void getAllScheduleDoctor() throws Exception {
        // Mock data
            List<Appointment> appointments = transactionAppointmentService.getAllScheduleDoctor();


        // Mock service response
        Mockito.when(transactionAppointmentService.getAllScheduleDoctor())
                .thenReturn(appointments);

        // Perform the Get request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicalrecord/MedicalRecordPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(appointments)))
                .andExpect(status().isOk());
    }
}