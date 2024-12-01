package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.StaffService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dao.StaffDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.VehicleEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private Mapping staffMapping;
    @Autowired
    private StaffDao staffDao;

    @Override
    public void saveMember(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.generateStaffId());
        StaffEntity staffEntity=staffMapping.toStaffEntity(staffDTO);
        //set associate entity
        if (staffDTO.getFields() != null && !staffDTO.getFields().isEmpty()) {
            List<FieldEntity> fieldEntities = staffDTO.getFields().stream()
                    .map(staffMapping::toFieldEntity)
                    .collect(Collectors.toList());
            staffEntity.setFields(fieldEntities);
        }
        StaffEntity saveMember =staffDao.save(staffEntity);
        if (saveMember == null) {
            throw new DataPersistException("Staff member Not Saved!");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffEntity>allStaff=staffDao.findAll();
        return staffMapping.asStaffDTOList(allStaff);
    }

    @Override
    public StaffStatus searchStaff(String staffId) {
        if (staffDao.existsById(staffId)) {
            return staffMapping.toStaffDTO(staffDao.getReferenceById(staffId));
        }
        return new SelectedErrorStatus(2,"Staff Not Found");
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> findStaff =staffDao.findById(staffId);
        if (findStaff.isPresent()) {
            findStaff.get().setFirstName(staffDTO.getFirstName());
            findStaff.get().setLastName(staffDTO.getLastName());
            findStaff.get().setDesignation(staffDTO.getDesignation());
            findStaff.get().setGender(staffDTO.getGender());
            findStaff.get().setDob(staffDTO.getDob());
            findStaff.get().setJoinedDate(staffDTO.getJoinedDate());
            findStaff.get().setEmail(staffDTO.getEmail());
            findStaff.get().setAddressLine1(staffDTO.getAddressLine1());
            findStaff.get().setAddressLine2(staffDTO.getAddressLine2());
            findStaff.get().setAddressLine3(staffDTO.getAddressLine3());
            findStaff.get().setAddressLine4(staffDTO.getAddressLine4());
            findStaff.get().setAddressLine5(staffDTO.getAddressLine5());
            findStaff.get().setContactNo(staffDTO.getContactNo());
            List<FieldEntity> fields = staffDTO.getFields().stream()
                    .map(fieldDTO -> staffMapping.toFieldEntity(fieldDTO))
                    .collect(Collectors.toList());
            findStaff.get().setFields(fields);
            List<VehicleEntity> vehicleEntityList=staffMapping.toVehicleEntityList(staffDTO.getVehicles());
            findStaff.get().setVehicles(vehicleEntityList);

        }

    }
}
