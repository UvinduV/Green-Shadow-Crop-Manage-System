package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.VehicleService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.VehicleDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.VehicleStatus;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.VehicleNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            System.out.println(vehicleDTO);
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping(value = "/{licenceNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus searchVehicleById(@PathVariable("licenceNumber") String licenceNumber) {
        if (!RegexProcess.licenceNumberMatcher(licenceNumber)) {
            return new SelectedErrorStatus(1,"Licence number is invalid");
        }
        return vehicleService.searchVehicleByNumber(licenceNumber);
    }

    @PutMapping(value = "/{licenceNumber}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable("licenceNumber") String licenceNumber,
                                              @RequestBody VehicleDTO vehicleDTO) {
        try {
            if (!RegexProcess.licenceNumberMatcher(licenceNumber)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(licenceNumber,vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{licenceNumber}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("licenceNumber") String licenceNumber){
        try {
            if (!RegexProcess.licenceNumberMatcher(licenceNumber)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(licenceNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
