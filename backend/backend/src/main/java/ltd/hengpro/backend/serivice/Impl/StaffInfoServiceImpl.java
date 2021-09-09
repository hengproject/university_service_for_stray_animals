package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.StaffInfoDao;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.StaffInfoService;
import ltd.hengpro.backend.utils.EnumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StaffInfoServiceImpl implements StaffInfoService {
    @Autowired
    StaffInfoDao staffInfoDao;

    @Override
    public StaffDto getStuffDtoByUserID(String userId) throws Exception {
        StaffDto staffDto = new StaffDto();
        StaffInfo staffInfo = staffInfoDao.findByUserId(userId);
        if(ObjectUtils.isEmpty(staffInfo)) throw new UserAuthException(ExceptionEnum.USERID_TO_STAFF_NOT_FOUND);
        BeanUtils.copyProperties(staffInfo,staffDto);
        staffDto.setStaffIdentityEnum(EnumUtil.getByCode(staffInfo.getStaffIdentity(),StaffIdentityEnum.class));
        return staffDto;
    }

    public StaffInfo register(StaffInfo staffInfo){
        return staffInfoDao.saveAndFlush(staffInfo);
    }
}
