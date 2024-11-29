package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.VehicleDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.VehicleStatus;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleStatus searchVehicleByNumber(String licenceNumber);
}
