package id.co.indivara.miniproject.hospital.dto;

import lombok.Data;

@Data
public class AppUserData {
    private String fullName;
    private String email;
    private String password;
    private String appUserRole;
}
