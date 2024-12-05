package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.FieldStatus;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.FieldDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.FieldEntity;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    void saveField(FieldDTO buildFieldDTO);

    void uploadImages(String fieldCode, FieldDTO buildFieldDTO);

    List<FieldDTO> getAllField();

    FieldStatus searchField(String fieldCode);

    void updateField(String fieldCode, FieldDTO fieldDTO);

    void deleteField(String fieldCode);

    List<String> getAllFieldNames();

    Optional<FieldEntity> getIdByName(String fieldName);
}
