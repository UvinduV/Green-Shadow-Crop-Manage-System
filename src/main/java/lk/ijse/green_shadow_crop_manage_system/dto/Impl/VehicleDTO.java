package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.VehicleStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Fuel;
import lk.ijse.green_shadow_crop_manage_system.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private Fuel fuelType;
    private Status status;
    private String remarks;
    private StaffDTO assignedStaff;
}
