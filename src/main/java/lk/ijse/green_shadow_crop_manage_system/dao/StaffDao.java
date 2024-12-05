package lk.ijse.green_shadow_crop_manage_system.dao;

import lk.ijse.green_shadow_crop_manage_system.entity.Impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
    @Query("SELECT s FROM StaffEntity s WHERE s.firstName = :first_name")
    Optional<StaffEntity> findByStaffName(@Param("first_name") String assignedStaff);
}
