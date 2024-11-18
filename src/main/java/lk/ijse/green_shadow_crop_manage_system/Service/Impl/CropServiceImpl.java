package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.CropService;
import lk.ijse.green_shadow_crop_manage_system.dao.CropDao;
import lk.ijse.green_shadow_crop_manage_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.CropDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.CropEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                dto.setFieldId(entity.getField().getFieldCode());
            }
        }
        return allCropDTOList;
    }
}
