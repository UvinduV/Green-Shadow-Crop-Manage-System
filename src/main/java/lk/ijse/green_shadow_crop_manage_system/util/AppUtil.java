package lk.ijse.green_shadow_crop_manage_system.util;

import java.util.UUID;

public class AppUtil {
    public static String generateStaffId(){
        return "STAFF-"+ UUID.randomUUID();
    }
}
