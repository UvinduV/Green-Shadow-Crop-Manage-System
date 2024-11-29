package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.EquipmentService;
import lk.ijse.green_shadow_crop_manage_system.dao.EquipmentDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.EquipmentDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.EquipmentEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new DataPersistException("equipment not saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentMapping.asEquipmentDTOList(equipmentDao.findAll());
    }
}
