package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.form.superuser.EditUserForm;

public interface StaffInfoService {
    StaffDto getStuffDtoByUserID(String userId) throws Exception;
    StaffInfo register(StaffInfo staffInfo);
    void delete(String staffId);
    void edit(EditUserForm editUserForm);
}
