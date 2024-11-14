package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.StaffService;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMember(@RequestBody StaffDTO staffDTO){ //serialization
        try {
            staffService.saveMember(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
