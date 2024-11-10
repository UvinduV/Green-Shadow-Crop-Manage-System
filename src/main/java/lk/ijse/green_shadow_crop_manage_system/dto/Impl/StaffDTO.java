package lk.ijse.green_shadow_crop_manage_system.dto.Impl;

import lk.ijse.green_shadow_crop_manage_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_manage_system.entity.Gender;
import lk.ijse.green_shadow_crop_manage_system.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String id;
    private String first_name;
    private String last_name;
    private String designation;
    private Gender gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    private String email;
    private Role role;

}
