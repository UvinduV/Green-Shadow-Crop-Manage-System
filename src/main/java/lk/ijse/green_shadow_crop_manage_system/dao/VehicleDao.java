package lk.ijse.green_shadow_crop_manage_system.dao;

import lk.ijse.green_shadow_crop_manage_system.entity.Impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
    @Query("SELECT v FROM VehicleEntity v WHERE v.licensePlateNumber = :licensePlateNumber")
    Optional<VehicleEntity> findByLicensePlateNumber(@Param("licensePlateNumber") String licensePlateNumber);

}
