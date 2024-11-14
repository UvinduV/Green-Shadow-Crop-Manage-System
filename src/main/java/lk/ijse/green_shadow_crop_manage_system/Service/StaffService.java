package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveMember(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();
}
