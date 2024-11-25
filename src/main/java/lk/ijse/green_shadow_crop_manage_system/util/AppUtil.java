package lk.ijse.green_shadow_crop_manage_system.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateStaffId(){
        return "STAFF-"+ UUID.randomUUID();
    }
    public static String generateFieldId(){
        return "FIELD-"+ UUID.randomUUID();
    }
    public static String generateCropId(){
        return "CROP-"+ UUID.randomUUID();
    }
    public static String generateVehicleId(){
        return "VEHICLE-"+ UUID.randomUUID();
    }
    public static String fieldImageToBase64(byte[] fieldImage){
        return Base64.getEncoder().encodeToString(fieldImage);
    }
    public static String cropImageToBase64(byte[] fieldImage){
        return Base64.getEncoder().encodeToString(fieldImage);
    }
}
