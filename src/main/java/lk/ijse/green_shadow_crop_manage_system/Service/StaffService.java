package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.StaffStatus;

import java.util.List;

public interface StaffService {
    void saveMember(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus searchStaff(String staffId);

    void updateStaff(String staffId, StaffDTO staffDTO);

    void deleteStaff(String staffId);

    List<String> getAllStaffNames();
}
