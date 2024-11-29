package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.EquipmentStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.EquipmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipments();

    EquipmentStatus searchEquipment(String equipmentId);

    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    void deleteEquipment(String equipmentId);
}
