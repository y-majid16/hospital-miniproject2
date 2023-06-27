package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.AppUser;
import id.co.indivara.miniproject.hospital.repositories.AppUserRepository;
import id.co.indivara.miniproject.hospital.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("user not found")));
    }

    public AppUser register(AppUser user) {
        boolean userExists = appUserRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new RuntimeException("USer email already exists");
        }
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return appUserRepository.save(user);
    }
}


