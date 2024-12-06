package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.CropService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dao.CropDao;
import lk.ijse.green_shadow_crop_manage_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_manage_system.dto.CropStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.CropDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.CropEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.CropNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping cropMapping;

    @Override
    public void saveCrop(CropDTO buildCropDTO) {
        System.out.println("crop Dto:"+buildCropDTO);

        Optional<FieldEntity> findField =fieldDao.findById(buildCropDTO.getFieldId());
        if (findField.isPresent()) {
            CropEntity saveCrop=cropMapping.toCropEntity(buildCropDTO);
            saveCrop.setField(findField.get());
            CropEntity savedCrop =cropDao.save(saveCrop);

            System.out.println("Crop Entity: "+savedCrop);
            if (savedCrop == null) {
                throw new DataPersistException("Crop is not Saved!");
            }
        }else {
            throw new FieldNotFoundException("this Field is not found");
        }

    }

    @Override
    public List<CropDTO> getAllCrops() {
        //return cropMapping.asCropDTOList(cropDao.findAll());

        List<CropEntity> allCropEntityList = cropDao.findAll();
        List<CropDTO> allCropDTOList = cropMapping.asCropDTOList(allCropEntityList);

        for (int i = 0; i < allCropDTOList.size(); i++) {
            CropEntity entity = allCropEntityList.get(i);
            CropDTO dto = allCropDTOList.get(i);

            if (entity.getField() != null) {
                dto.setFieldId(entity.getField().getFieldName());
            }
        }
        return allCropDTOList;
    }

    @Override
    public CropStatus getSelectedCrop(String cropCode) {
        if(cropDao.existsById(cropCode)){
            var selectedCrop = cropDao.getReferenceById(cropCode);
            String fieldCode=selectedCrop.getField().getFieldCode();
            CropDTO selectedCropDTO=cropMapping.toCropDTO(selectedCrop);
            selectedCropDTO.setFieldId(fieldCode);
            return selectedCropDTO;
        }else {
            return new SelectedErrorStatus(2,"Search Crop not found!");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO updateCropDTO) {
        Optional<CropEntity> findCrop=cropDao.findById(cropCode);
        Optional<FieldEntity> findField=fieldDao.findById(updateCropDTO.getFieldId());
        if (findCrop.isPresent()) {
            findCrop.get().setCommonName(updateCropDTO.getCommonName());
            findCrop.get().setScientificName(updateCropDTO.getScientificName());
            findCrop.get().setCropImage(updateCropDTO.getCropImage());
            findCrop.get().setCategory(updateCropDTO.getCategory());
            findCrop.get().setSeason(updateCropDTO.getSeason());
            findCrop.get().setField(findField.get());
        }else {
            throw new CropNotFoundException("this Crop is not found!");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> findCrop=cropDao.findById(cropCode);
        if (!findCrop.isPresent()) {
            throw new CropNotFoundException("this Crop is not found!");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public List<String> getAllCropNames() {
        List<CropEntity> cropEntities = cropDao.findAll();
        return cropEntities.stream()
                .map(CropEntity::getCommonName)
                .collect(Collectors.toList());
    }
}
