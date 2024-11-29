package lk.ijse.green_shadow_crop_manage_system.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_manage_system.Service.VehicleService;
import lk.ijse.green_shadow_crop_manage_system.customStatusCode.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_manage_system.dao.VehicleDao;
import lk.ijse.green_shadow_crop_manage_system.dto.Impl.VehicleDTO;
import lk.ijse.green_shadow_crop_manage_system.dto.VehicleStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Impl.VehicleEntity;
import lk.ijse.green_shadow_crop_manage_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_manage_system.exception.VehicleNotFoundException;
import lk.ijse.green_shadow_crop_manage_system.util.AppUtil;
import lk.ijse.green_shadow_crop_manage_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping vehicleMapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.generateVehicleId());
        VehicleEntity savedVehicle =vehicleDao.save(vehicleMapping.toVehicleEntity(vehicleDTO));
        if (savedVehicle == null) {
            throw new DataPersistException("Vehicle Not Saved");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleMapping.asVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public VehicleStatus searchVehicleByNumber(String licenceNumber) {
        Optional<VehicleEntity> findVehicle = vehicleDao.findByLicensePlateNumber(licenceNumber);
        if (findVehicle.isEmpty()) {
            return new SelectedErrorStatus(2, "vehicle is not found");
        }
        return vehicleMapping.toVehicleDTO(findVehicle.get());
    }
}
