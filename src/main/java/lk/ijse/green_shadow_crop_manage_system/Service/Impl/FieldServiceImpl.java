package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.FieldService;
import lk.ijse.green_shadow_crop_manage_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping fieldMapping;
    @Override
    public void saveField(FieldDTO buildFieldDTO) {
        FieldEntity savedField =fieldDao.save(fieldMapping.toFieldEntity(buildFieldDTO));
        if (savedField == null) {
            throw new DataPersistException("Field is not saved!");
        }
    }

    @Override
    public void uploadImages(String fieldCode, FieldDTO buildFieldDTO) {
        Optional<FieldEntity>fieldField =fieldDao.findById(fieldCode);
        if (fieldField.isPresent()) {
            fieldField.get().setFieldImage1(buildFieldDTO.getFieldImage1());
            fieldField.get().setFieldImage2(buildFieldDTO.getFieldImage2());
        }else {
            throw new FieldNotFoundException("This Field-" + fieldCode + " is not found");
        }
    }

}
