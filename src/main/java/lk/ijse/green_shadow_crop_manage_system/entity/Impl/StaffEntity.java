package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lk.ijse.green_shadow_crop_manage_system.entity.Gender;
import lk.ijse.green_shadow_crop_manage_system.entity.Role;
import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joinedDate;
    private String dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(name = "FieldStaffDetails",joinColumns = @JoinColumn(name = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode"))
    @JsonBackReference
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "assignedStaff", cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;
}
