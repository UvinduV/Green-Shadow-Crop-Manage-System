package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.AuthService;
import lk.ijse.green_shadow_crop_manage_system.Service.UserService;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.UserDTO;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.secure.JWTAuthResponse;
import lk.ijse.green_shadow_crop_manage_system.secure.SignIn;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthUserController {
    private final AuthService authService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO userDTO) {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setUserId(AppUtil.generateUserId());
            /*userService.saveUser(userDTO);*/
            return ResponseEntity.ok(authService.signUp(userDTO));
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "signIn",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> RefreshToken(@RequestParam ("existingToken") String existingToken) {
        return ResponseEntity.ok(authService.refreshToken(existingToken));
    }
}
