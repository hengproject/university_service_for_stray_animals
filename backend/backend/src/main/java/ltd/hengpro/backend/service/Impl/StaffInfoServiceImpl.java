package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.StaffInfoDao;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.service.StaffInfoService;
import ltd.hengpro.backend.utils.EnumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

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

    public void delete(String staffId){
        try {
            staffInfoDao.deleteById(staffId);
        }catch (EmptyResultDataAccessException e){
            throw new UserAuthException(ExceptionEnum.DELETE_INFO_DAO_NOT_FOUND);
        }
    }
    public void edit(EditUserForm editUserForm){
        StaffInfo byId = staffInfoDao.getById(editUserForm.getStaffId());
        if(ObjectUtils.isEmpty(byId)) throw new UserAuthException(ExceptionEnum.EDIT_USER_NOT_FOUND);
        byId.setUpdateTime(new Date());
        Integer code = StaffIdentityEnum.valueOf(editUserForm.getStaffIdentity()).getCode();
        byId.setStaffIdentity(code);
        byId.setStaffName(editUserForm.getStaffName());
        staffInfoDao.saveAndFlush(byId);
    }

    public StaffInfo getStaffInfoFromUserId(String userId){
        return staffInfoDao.findByUserId(userId);
    }

    @Override
    public void edit(StaffInfo staffInfo) {
        staffInfoDao.saveAndFlush(staffInfo);
    }
}
