package lk.ijse.green_shadow_crop_manage_system.Service;

import lk.ijse.green_shadow_crop_manage_system.dto.Impl.MonitoringLogDTO;

import java.util.List;

public interface LogService {
    void saveLog(MonitoringLogDTO buildMonitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();
}
