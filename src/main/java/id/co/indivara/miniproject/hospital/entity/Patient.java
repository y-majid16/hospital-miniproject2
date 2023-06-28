package id.co.indivara.miniproject.hospital.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mst_patients")
public class Patient {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "patient_name", nullable = false)
    @NotBlank
    private String patientName;

    @Column(name = "identity_card_number", unique = true)
    private String identityCardNumber;

    @Email
    private String email;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String gender;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "patient_height")
    private Integer patientHeight;


    @Column(name = "patient_weight")
    private Integer patientWeight;


    private String address;


    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;

}
