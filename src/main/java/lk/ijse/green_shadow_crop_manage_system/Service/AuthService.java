package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.UserDTO;
import lk.ijse.green_shadow_crop_manage_system.secure.JWTAuthResponse;
import lk.ijse.green_shadow_crop_manage_system.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
