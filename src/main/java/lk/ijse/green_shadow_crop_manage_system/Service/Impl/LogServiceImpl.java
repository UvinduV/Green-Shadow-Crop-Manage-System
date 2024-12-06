package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.LogService;
import lk.ijse.green_shadow_crop_manage_system.dao.LogDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.MonitoringLogDTO;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.MonitoringLogEntity;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.VehicleEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Autowired
    private Mapping logMapping;

    @Override
    public void saveLog(MonitoringLogDTO buildMonitoringLogDTO) {
        buildMonitoringLogDTO.setLogCode(AppUtil.generateLogId());
        MonitoringLogEntity savedLog =logDao.save(logMapping.toMonitoringLogEntity(buildMonitoringLogDTO));
        if(savedLog == null){
            throw new DataPersistException("log save failed");
        }

    }

    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return logMapping.asMonitoringLogDTOList(logDao.findAll());
    }
}
