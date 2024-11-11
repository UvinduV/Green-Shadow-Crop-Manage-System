package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_manage_system.entity.EquipmentType;
import lk.ijse.green_shadow_crop_manage_system.entity.Status;
import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity assignedStaff;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity assignedField;
}
