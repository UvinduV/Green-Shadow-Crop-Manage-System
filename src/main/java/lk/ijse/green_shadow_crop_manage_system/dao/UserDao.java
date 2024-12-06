package lk.ijse.green_shadow_crop_manage_system.dao;

import lk.ijse.green_shadow_crop_manage_system.entity.Impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
