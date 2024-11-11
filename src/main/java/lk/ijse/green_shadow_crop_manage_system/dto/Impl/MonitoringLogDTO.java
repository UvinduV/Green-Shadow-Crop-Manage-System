package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.MonitoringLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStatus {
    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    private List<FieldDTO> fields;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;

}
