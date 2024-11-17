package lk.ijse.green_shadow_crop_manage_system.customStatusCode;

import lk.ijse.green_shadow_crop_manage_system.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedErrorStatus implements UserStatus, StaffStatus, FieldStatus, CropStatus, VehicleStatus,EquipmentStatus,MonitoringLogStatus,AddFieldStatus {
    private int statusCode;
    private String statusMassage;
}
