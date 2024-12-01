package lk.ijse.green_shadow_crop_manage_system.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean cropCodeMatcher(String cropCode) {
        String regexForCropCode = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(cropCode).matches();
    }
    public static boolean fieldCodeMatcher(String fieldCode) {
        String regexForFieldCode = "^FIELD-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForFieldCode);
        return regexPattern.matcher(fieldCode).matches();
    }
    public static boolean staffIdMatcher(String staffId) {
        String regexForStaffId = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffId);
        return regexPattern.matcher(staffId).matches();
    }
    public static boolean licenceNumberMatcher(String licenceNumber) {
        String regexForLicenceNumber = "^[A-Z]{2,3}-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForLicenceNumber);
        return regexPattern.matcher(licenceNumber).matches();
    }
    public static boolean equipmentCodeMatcher(String equipmentId) {
        String regexForEquipmentId = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentId);
        return regexPattern.matcher(equipmentId).matches();
    }
}
