package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lk.ijse.green_shadow_crop_manage_system.entity.Fuel;
import lk.ijse.green_shadow_crop_manage_system.entity.Status;
import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    @Enumerated(EnumType.STRING)
    private Fuel fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "staffId")
    @JsonBackReference
    private StaffEntity assignedStaff;
}
