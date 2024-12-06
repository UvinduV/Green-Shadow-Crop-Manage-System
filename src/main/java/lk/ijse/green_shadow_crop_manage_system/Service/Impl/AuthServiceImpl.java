package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.AuthService;
import lk.ijse.green_shadow_crop_manage_system.Service.JWTService;
import lk.ijse.green_shadow_crop_manage_system.dao.UserDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.UserDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.UserEntity;
import lk.ijse.green_shadow_crop_manage_system.secure.JWTAuthResponse;
import lk.ijse.green_shadow_crop_manage_system.secure.SignIn;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var user=userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generateToken=jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        //save user
        UserEntity saveUser= userDao.save(mapping.toUserEntity(userDTO));
        //generate token and return
        var generateToken= jwtService.generateToken(saveUser);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract user name
        var userName = jwtService.extractUserName(accessToken);
        //check the user availability
        var findUser = userDao.findByEmail(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
