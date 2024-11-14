package lk.ijse.green_shadow_crop_manage_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GreenShadowCropManageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenShadowCropManageSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}


