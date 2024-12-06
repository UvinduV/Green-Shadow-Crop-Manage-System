package lk.ijse.green_shadow_crop_manage_system.dao;

import lk.ijse.green_shadow_crop_manage_system.entity.Impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<MonitoringLogEntity,String> {
}
