package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDTO implements CropStatus {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String season;
    private FieldDTO field;
}
