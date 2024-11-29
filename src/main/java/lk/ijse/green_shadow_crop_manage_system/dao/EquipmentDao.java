package lk.ijse.green_shadow_crop_manage_system.dao;

import lk.ijse.green_shadow_crop_manage_system.entity.Impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
}
