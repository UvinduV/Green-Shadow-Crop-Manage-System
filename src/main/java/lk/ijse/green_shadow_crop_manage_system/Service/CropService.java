package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.CropStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO buildCropDTO);

    List<CropDTO> getAllCrops();

    CropStatus getSelectedCrop(String cropCode);
}
