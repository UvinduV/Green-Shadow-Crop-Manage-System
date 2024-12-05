package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.*;

import java.awt.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point location;
    private Double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CropEntity> crops;
    @ManyToMany(mappedBy = "fields")
    @JsonBackReference
    private List<StaffEntity> staff;
}
