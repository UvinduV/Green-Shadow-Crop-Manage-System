package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.AddFieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddFieldDTO implements AddFieldStatus {
    private String fieldCode;
    private String fieldName;
    private Double x;
    private Double y;
    private Double extentSize;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
}
