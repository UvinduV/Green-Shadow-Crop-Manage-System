package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.EquipmentStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.EquipmentType;
import lk.ijse.green_shadow_crop_manage_system.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Status status;
    private StaffDTO assignedStaff;
    private FieldDTO assignedField;
}
