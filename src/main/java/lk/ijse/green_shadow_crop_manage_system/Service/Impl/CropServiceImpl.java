package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.CropService;
import lk.ijse.green_shadow_crop_manage_system.dao.CropDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.CropDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.CropEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping cropMapping;

    @Override
    public void saveCrop(CropDTO buildCropDTO) {
        CropEntity savedCrop =cropDao.save(cropMapping.toCropEntity(buildCropDTO));
        if (savedCrop == null) {
            throw new DataPersistException("Crop is not Saved!");
        }

    }
}
