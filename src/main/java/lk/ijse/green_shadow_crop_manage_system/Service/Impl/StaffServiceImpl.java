package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.StaffService;
import lk.ijse.green_shadow_crop_manage_system.dao.StaffDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.StaffDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private Mapping staffMapping;
    @Autowired
    private StaffDao staffDao;

    @Override
    public void saveMember(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.generateStaffId());
        StaffEntity saveMember =staffDao.save(staffMapping.toStaffEntity(staffDTO));
        if (saveMember == null) {
            throw new DataPersistException("Staff member Not Saved!");
        }
    }
}
