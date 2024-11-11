package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "monitoringLog")
public class MonitoringLogEntity implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @OneToMany(mappedBy = "fieldCode", cascade = CascadeType.ALL)
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "cropCode", cascade = CascadeType.ALL)
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "staffId", cascade = CascadeType.ALL)
    private List<StaffEntity> staff;
}
