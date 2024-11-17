package lk.ijse.green_shadow_crop_manage_system.entity.Impl;

import jakarta.persistence.*;

import lk.ijse.green_shadow_crop_manage_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String season;
    @ManyToOne
    @JoinColumn(name = "fieldCode",nullable = false)
    private FieldEntity field;
}
