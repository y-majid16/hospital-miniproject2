package id.co.indivara.miniproject.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_doctors")
public class Doctor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "doctor_id", nullable = false)
    private String doctorId;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Column(name = "doctor_name",length = 100)
    @NotEmpty
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    @Column(name = "phone_number", length = 12)
    private String phoneNumber;


    @Column(name = "date_of_birth", length = 25)
    @NotEmpty
    private String dateOfBirth;

    @NotNull
    private String gender;

    @NotEmpty
    @Column(length = 200)
    private String address;
}
