package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.FieldService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_manage_system.dto.FieldStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.CropEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<FieldDTO> getAllField() {
        return fieldMapping.asFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus searchField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity selectedField=fieldDao.getReferenceById(fieldCode);
            return fieldMapping.toFieldDTO(selectedField);

        }else {
            return new SelectedErrorStatus(2,"This Field is not found");
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> findField = fieldDao.findById(fieldCode);
        if(findField.isPresent()){
            findField.get().setFieldName(fieldDTO.getFieldName());
            //findField.get().setLocation(fieldDTO.getLocation());
            findField.get().setExtentSize(fieldDTO.getExtentSize());
            List<CropEntity> cropEntityList = fieldMapping.toCropEntityList(fieldDTO.getCrops());
            findField.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = fieldMapping.toStaffEntityList(fieldDTO.getStaff());
            findField.get().setStaff(staffEntityList);
        }else{
            throw new FieldNotFoundException("This Field-" + fieldCode + " is not found");
        }
    }

}
