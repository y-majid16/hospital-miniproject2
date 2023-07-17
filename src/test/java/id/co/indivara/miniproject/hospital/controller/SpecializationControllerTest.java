package id.co.indivara.miniproject.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.miniproject.hospital.controllers.SpecializationController;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repositories.SpecializationRepository;
import id.co.indivara.miniproject.hospital.service.SpecializationService;
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
class SpecializationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private SpecializationService specializationService;

    @InjectMocks
    private SpecializationController specializationController;

    @Autowired
    private SpecializationRepository specializationRepository;
    @Before("")
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(specializationController).build();
    }

    @Test
    public void testCreateSpecialization() throws Exception{
        //Mock Data
        Specialization specialization = new Specialization();
        specialization.setSpecializationId("4028818d88e5ff130188e60216590003");
        specialization.setSpecializationName("Penyakit Dalam");

        // Mock service response
        Mockito.when(specializationService.createSpecialization(Mockito.any(Specialization.class)))
                .thenReturn(specialization);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doctorspecialization/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specialization)))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllSpecialization() throws Exception {
        // Mock data
        List<Specialization> specializations = specializationService.getAllSpecialization();


        // Mock service response
        Mockito.when(specializationService.getAllSpecialization())
                .thenReturn(specializations);

        // Perform the Get request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctorspecialization/findAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specializations)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIdSpecialization() throws Exception {
        // Mock data
        Specialization specialization= specializationRepository.findById("2c948a8688fad57d0188fadaa1e50002").get();

        // Mock service response
        Mockito.when(specializationService.getIdSpecialization("2c948a8688fad57d0188fadaa1e50002"))
                .thenReturn(specialization);

        // Perform the Get request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctorspecialization/find/{specialization_id}","2c948a8688fad57d0188fadaa1e50002")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specialization)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateSpecialization() throws Exception {
        // Mock data
        Specialization specialization= specializationRepository.findById("2c948a8688fad57d0188fadaa1e50002").get();

        // Mock service response
        Mockito.when(specializationService.updateSpecialization("2c948a8688fad57d0188fadaa1e50002", new Specialization("2c948a8688fad57d0188fadaa1e50002", "Penyakit Hati")))
                .thenReturn(specialization);

        // Perform the Get request
        mockMvc.perform(MockMvcRequestBuilders.put("/api/doctorspecialization/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specialization)))
                .andExpect(status().isOk());
    }

}
