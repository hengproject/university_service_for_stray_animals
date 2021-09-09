package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.entity.StaffInfo;

public interface StaffInfoService {
    StaffDto getStuffDtoByUserID(String userId) throws Exception;
    StaffInfo register(StaffInfo staffInfo);

}
