package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.FieldService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.FieldStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.AddFieldDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestBody AddFieldDTO addFieldDTO){

        try {
            String fieldCode = AppUtil.generateFieldId();
            Point location = new Point(addFieldDTO.getX().intValue(),addFieldDTO.getY().intValue());

            FieldDTO buildFieldDTO=new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldName(addFieldDTO.getFieldName());
            buildFieldDTO.setLocation(location);
            buildFieldDTO.setExtentSize(addFieldDTO.getExtentSize());

            buildFieldDTO.setCrops(addFieldDTO.getCrops());
            buildFieldDTO.setStaff(addFieldDTO.getStaff());

            fieldService.saveField(buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{fieldCode}")
    public ResponseEntity<Void> AddImages(@RequestPart ("fieldImage1") MultipartFile fieldImage1,
                                          @RequestPart ("fieldImage2") MultipartFile fieldImage2,
                                          @PathVariable("fieldCode") String fieldCode){
        String base64FieldImage1 = "";
        String base64FieldImage2 = "";

        try {
            byte[] bytesFieldImage1 = fieldImage1.getBytes();
            base64FieldImage1 = AppUtil.fieldImageToBase64(bytesFieldImage1);

            byte[] bytesFieldImage2 = fieldImage2.getBytes();
            base64FieldImage2 = AppUtil.fieldImageToBase64(bytesFieldImage2);

            FieldDTO buildFieldDTO=new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldImage1(base64FieldImage1);
            buildFieldDTO.setFieldImage2(base64FieldImage2);

            fieldService.uploadImages(fieldCode,buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllField(){
        return fieldService.getAllField();
    }
    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus searchField(@PathVariable("fieldCode") String fieldCode){
        if (!RegexProcess.fieldCodeMatcher(fieldCode)) {
            return new SelectedErrorStatus(1,"field Code is not valid!");
        }
        return fieldService.searchField(fieldCode);
    }
    @PutMapping(value = "/{fieldCode}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(@PathVariable("fieldCode") String fieldCode,
                                            @RequestBody AddFieldDTO addFieldDTO){
        try {
            Point location = new Point(addFieldDTO.getX().intValue(),addFieldDTO.getY().intValue());

            FieldDTO fieldDTO=new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(addFieldDTO.getFieldName());
            fieldDTO.setLocation(location);
            fieldDTO.setExtentSize(addFieldDTO.getExtentSize());

            fieldDTO.setCrops(addFieldDTO.getCrops());
            fieldDTO.setStaff(addFieldDTO.getStaff());

            fieldService.updateField(fieldCode,fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try {
            if (!RegexProcess.fieldCodeMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}