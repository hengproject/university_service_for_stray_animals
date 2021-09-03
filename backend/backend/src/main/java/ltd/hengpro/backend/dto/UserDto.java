package ltd.hengpro.backend.dto;

import ltd.hengpro.backend.enums.SpecialIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;

public class UserDto {
    public String userId;

    public String username;

    public UserGroupEnum userGroupEnum;

    public SpecialIdentityEnum specialIdentityEnum;

    public String token;

    public UserDto(String userId, String username, UserGroupEnum userGroupEnum,SpecialIdentityEnum specialIdentityEnum) {
        this.userId = userId;
        this.username = username;
        this.userGroupEnum = userGroupEnum;
        this.specialIdentityEnum=specialIdentityEnum;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public UserGroupEnum getUserGroupEnum() {
        return this.userGroupEnum;
    }

    public SpecialIdentityEnum getSpecialIdentityEnum() {
        return this.specialIdentityEnum;
    }

    public String getToken() {
        return this.token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserGroupEnum(UserGroupEnum userGroupEnum) {
        this.userGroupEnum = userGroupEnum;
    }

    public void setSpecialIdentityEnum(SpecialIdentityEnum specialIdentityEnum) {
        this.specialIdentityEnum = specialIdentityEnum;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$userGroupEnum = this.getUserGroupEnum();
        final Object other$userGroupEnum = other.getUserGroupEnum();
        if (this$userGroupEnum == null ? other$userGroupEnum != null : !this$userGroupEnum.equals(other$userGroupEnum))
            return false;
        final Object this$specialIdentityEnum = this.getSpecialIdentityEnum();
        final Object other$specialIdentityEnum = other.getSpecialIdentityEnum();
        if (this$specialIdentityEnum == null ? other$specialIdentityEnum != null : !this$specialIdentityEnum.equals(other$specialIdentityEnum))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $userGroupEnum = this.getUserGroupEnum();
        result = result * PRIME + ($userGroupEnum == null ? 43 : $userGroupEnum.hashCode());
        final Object $specialIdentityEnum = this.getSpecialIdentityEnum();
        result = result * PRIME + ($specialIdentityEnum == null ? 43 : $specialIdentityEnum.hashCode());
        return result;
    }

    public String toString() {
        return "UserDto(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", userGroupEnum=" + this.getUserGroupEnum() + ", specialIdentityEnum=" + this.getSpecialIdentityEnum() + ", token=" + this.getToken() + ")";
    }
}
