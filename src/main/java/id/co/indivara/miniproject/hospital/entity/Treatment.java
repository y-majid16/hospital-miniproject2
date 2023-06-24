package id.co.indivara.miniproject.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_treatments")
public class Treatment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "treatment_id", nullable = false)
    private String treatmentId;

    @Column(name = "treatment_type")
    @NotEmpty
    private String treatmentType;

    @NotEmpty
    private String diagnosis;

    @NotEmpty
    private String medication;

    private String dosage;

}
