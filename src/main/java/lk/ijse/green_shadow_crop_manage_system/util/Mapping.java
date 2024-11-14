package lk.ijse.green_shadow_crop_manage_system.util;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public StaffEntity toStaffEntity(StaffDTO staffDTO){
        return modelMapper.map(staffDTO, StaffEntity.class);
    }
    public StaffDTO toStaffDTO(StaffEntity staffEntity){
        return modelMapper.map(staffEntity, StaffDTO.class);
    }
    public List<StaffDTO> asStaffDTOList(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDTO>>() {}.getType());
    }
}
