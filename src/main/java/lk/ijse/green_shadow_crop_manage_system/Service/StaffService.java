package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    void saveMember(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus searchStaff(String staffId);

    void updateStaff(String staffId, StaffDTO staffDTO);

    void deleteStaff(String staffId);

    List<String> getAllStaffNames();

    Optional<StaffEntity> getIdByName(String firstName);
}
