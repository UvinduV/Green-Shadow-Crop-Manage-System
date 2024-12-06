package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(UserDTO userDTO);

    UserDetailsService userDetailsService();
}
