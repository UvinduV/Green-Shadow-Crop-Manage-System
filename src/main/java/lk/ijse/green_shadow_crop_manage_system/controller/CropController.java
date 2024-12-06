package lk.ijse.green_shadow_crop_manage_system.controller;

import lk.ijse.green_shadow_crop_manage_system.Service.CropService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.CropStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.CropDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;
import lk.ijse.green_shadow_crop_manage_system.exception.CropNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin(origins = "http://localhost:63342")
public class CropController {
    @Autowired
    private CropService cropService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestPart("commonName") String commonName,
                                         @RequestPart ("scientificName") String scientificName,
                                         @RequestPart ("cropImage") MultipartFile cropImage,
                                         @RequestPart ("category") String category,
                                         @RequestPart ("season") String season,
                                         @RequestPart ("field") String fieldId
    ){
        String base64CropImage = "";

        try {
            String cropCode = AppUtil.generateCropId();

            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);

            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCropCode(cropCode);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setCropImage(base64CropImage);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
            buildCropDTO.setFieldId(fieldId);

            System.out.print(buildCropDTO);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("cropCode") String cropCode){
        if (!RegexProcess.cropCodeMatcher(cropCode)) {
             return new SelectedErrorStatus(1,"Crop Code is not valid!");
        }
        return cropService.getSelectedCrop(cropCode);
    }

    @PutMapping(value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCrop(@PathVariable("cropCode") String cropCode,
                                           @RequestPart ("commonName") String commonName,
                                           @RequestPart ("scientificName") String scientificName,
                                           @RequestPart ("cropImage") MultipartFile cropImage,
                                           @RequestPart ("category") String category,
                                           @RequestPart ("season") String season,
                                           @RequestPart ("field") String fieldId){
        String base64CropImage = "";
        try {
            if (!RegexProcess.cropCodeMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);
            CropDTO updateCropDTO = new CropDTO();
            updateCropDTO.setCropCode(cropCode);
            updateCropDTO.setCommonName(commonName);
            updateCropDTO.setScientificName(scientificName);
            updateCropDTO.setCropImage(base64CropImage);
            updateCropDTO.setCategory(category);
            updateCropDTO.setSeason(season);
            updateCropDTO.setFieldId(fieldId);

            cropService.updateCrop(cropCode,updateCropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode){
        try {
            if (!RegexProcess.cropCodeMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "getAllCropNames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCropName(){
        List<String> cropNames = cropService.getAllCropNames();
        return ResponseEntity.ok(cropNames);
    }
}
