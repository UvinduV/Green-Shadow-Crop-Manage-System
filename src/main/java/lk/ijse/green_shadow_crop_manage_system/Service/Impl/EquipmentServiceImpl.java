package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.EquipmentService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dao.EquipmentDao;
import lk.ijse.green_shadow_crop_manage_system.dto.EquipmentStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.EquipmentDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.EquipmentEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.EquipmentNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping equipmentMapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
        EquipmentEntity SavedEquipment =equipmentDao.save(equipmentMapping.toEquipmentEntity(equipmentDTO));
        if (SavedEquipment == null) {
            throw new DataPersistException("equipment is not saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentMapping.asEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public EquipmentStatus searchEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
            return equipmentMapping.toEquipmentDTO(equipmentDao.getReferenceById(equipmentId));
        }else {
            return new SelectedErrorStatus(2,"equipment is not found");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> findEquipment =equipmentDao.findById(equipmentId);
        if (findEquipment.isPresent()) {
            findEquipment.get().setName(equipmentDTO.getName());
            findEquipment.get().setType(equipmentDTO.getType());
            findEquipment.get().setStatus(equipmentDTO.getStatus());
            StaffEntity assignStaff=equipmentMapping.toStaffEntity(equipmentDTO.getAssignedStaff());
            findEquipment.get().setAssignedStaff(assignStaff);
            FieldEntity assignField=equipmentMapping.toFieldEntity(equipmentDTO.getAssignedField());
            findEquipment.get().setAssignedField(assignField);
        }else {
            throw new EquipmentNotFoundException("Equipment is not found");
        }
    }

}
