package pl.sda.projectY.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.projectY.dto.AdminDto;
import pl.sda.projectY.entity.Admin;
import pl.sda.projectY.repository.AdminRepository;
import pl.sda.projectY.type.Role;

/**
 * author:
 * Mateusz
 * Marczak
 **/

@Service
public class AdminService {


    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    public void addNewAdmin(AdminDto adminDto) {
        Admin newAdmin = getAdmin(adminDto);
        adminRepository.save(newAdmin);

    }

    private Admin getAdmin(AdminDto adminDto) {
        Admin newAdmin = new Admin();

        newAdmin.setUserId(adminDto.getUserId());
        newAdmin.setLogin(adminDto.getLogin());
        newAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        newAdmin.setRole(Role.ADMIN);
        newAdmin.setEMail(adminDto.getEMail());
        newAdmin.setName(adminDto.getName());
        newAdmin.setSurname(adminDto.getSurname());
        newAdmin.setTelephone(adminDto.getTelephone());
        return newAdmin;
    }

    public void deleteAdminById(int userId) {
        adminRepository.delete(userId);
    }

    public void editAdmin(AdminDto adminDto) {
        Admin admin = adminRepository.findOne(adminDto.getUserId());

        admin.setLogin(adminDto.getLogin());
        if(adminDto.getPassword() != null){
            admin.setPassword(adminDto.getPassword());
        }

        admin.setTelephone(adminDto.getTelephone());
        admin.setSurname(adminDto.getSurname());
        admin.setName(adminDto.getName());
        admin.setEMail(adminDto.getEMail());
        adminRepository.save(admin);
    }
}
