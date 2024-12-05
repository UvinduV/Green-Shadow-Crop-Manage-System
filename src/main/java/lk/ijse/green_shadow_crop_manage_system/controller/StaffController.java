package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.StaffService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.exception.StaffNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMember(@RequestBody StaffDTO staffDTO){
        try {
            staffService.saveMember(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff(){
        return staffService.getAllStaff();
    }
    @GetMapping(value="/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus searchStaff(@PathVariable ("staffId") String staffId){
        if(!RegexProcess.staffIdMatcher(staffId)){
            return new SelectedErrorStatus(1,"Staff ID does not match");
        }
        return staffService.searchStaff(staffId);
    }
    @PutMapping(value = "/{staffId}")
    public ResponseEntity<Void> updateStaff(@PathVariable ("staffId") String staffId,
                                            @RequestBody StaffDTO staffDTO){

        try {
            if(!RegexProcess.staffIdMatcher(staffId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staffId, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable ("staffId") String staffId){
        try {
            if(!RegexProcess.staffIdMatcher(staffId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "getAllStaffNames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllStaffName(){
        List<String> staffNames = staffService.getAllStaffNames();
        return ResponseEntity.ok(staffNames);
    }

    @GetMapping( "/getStaffId/{firstName}")
    public ResponseEntity<String> getStaffId(@PathVariable("firstName") String firstName){
        try {
            System.out.println(firstName);
            Optional<StaffEntity> staffEntity = staffService.getIdByName(firstName);
            System.out.println(staffEntity);
            return ResponseEntity.ok(staffEntity.get().getStaffId());
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
