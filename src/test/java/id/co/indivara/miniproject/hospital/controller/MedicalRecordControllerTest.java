package id.co.indivara.miniproject.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.miniproject.hospital.controllers.TransactionMedicalController;
import id.co.indivara.miniproject.hospital.entity.*;
import id.co.indivara.miniproject.hospital.service.TransactionMedicalRecordService;
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
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionMedicalRecordService transactionMedicalRecordService;

    @InjectMocks
    private TransactionMedicalController transactionMedicalController;



    @Before("")
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionMedicalController).build();
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {
        // Mock data
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAppointment(new Appointment("2c948a8688fae6230188fae72e9d0000",
                new Patient("2c948a8688fad57d0188fad59eec0000", "Abi","327324232222223","abi@gmail.com", "081323232323","1977-06-23","LAKI-LAKI","B+",166,59,"Jln, Jakarta no 3","081883233232"),
                new Doctor("2c948a8688fae0520188fae2e3ba0000",new Specialization("4028818d88e5ff130188e60216590003","Penyakit Saraf"),"Richard","richard@gmail.com","08123232323","1997-03-12","Man", "Jln, Jakarta no 78"),"sakit","09:00","10:00",false));
        medicalRecord.setTreatment(new Treatment("2c948a8688fae6230188fae8bfbf0001","Pengobatan","Radang Usus","Kortikosteroid","3x Sehari"));
        medicalRecord.setNote("banyak gerak");

        // Mock service response
        Mockito.when(transactionMedicalRecordService.createMediacalRecord(Mockito.any(MedicalRecord.class)))
                .thenReturn(medicalRecord);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/medicalrecord/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(medicalRecord)))
                .andExpect(status().isOk());
    }
    @Test
    public void getAllMedicalRecord() throws Exception {
        // Mock data
            List<MedicalRecord> medicalRecords = transactionMedicalRecordService.getAllMedicalRecordPatient();


        // Mock service response
        Mockito.when(transactionMedicalRecordService.getAllMedicalRecordPatient())
                .thenReturn(medicalRecords);

        // Perform the Get request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicalrecord/MedicalRecordPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(medicalRecords)))
                .andExpect(status().isOk());
    }
}