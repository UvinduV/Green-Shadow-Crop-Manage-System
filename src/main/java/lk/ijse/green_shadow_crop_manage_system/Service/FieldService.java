package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO buildFieldDTO);

    void uploadImages(String fieldCode, FieldDTO buildFieldDTO);

    List<FieldDTO> getAllField();
}
