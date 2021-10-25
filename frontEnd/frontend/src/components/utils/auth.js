class AuthLevel {
  _userGroupEnum;
  _staffIdentityEnum;
  constructor(userGroupEnum, staffIdentityEnum) {
    this._userGroupEnum = userGroupEnum === "SUPER_USER" ? 0 : 1;
    this._staffIdentityEnum = staffIdentityEnum === "MANAGER" ? 0 : 1;
  }

  get _userGroupEnum() {
    return this._userGroupEnum;
  }

  get _staffIdentityEnum() {
    return this._staffIdentityEnum;
  }
}

class MenuList {
  _authName;
  _id;
  _path;
  _children;

  constructor(authName, id, path, children) {
    this._authName = authName;
    this._id = id;
    this._path = path;
    this._children = children;
  }

  get _authName() {
    return this._authName;
  }

  get _id() {
    return this._id;
  }

  get _path() {
    return this._path;
  }

  get _children() {
    return this._children;
  }
}

let normalMenuList = [
  // new MenuList("喂食记录", "00101", "/feed", null),
  new MenuList("记录猫猫出现", "00102", "/recordAppearance", null),
  new MenuList("修改账户信息", "00103", "/editInformation", null),
  new MenuList("申请流程查看", "00104", "/questions", null),
  // new MenuList("报表查看", "00105", "/echarts", null),
  new MenuList("猫猫档案查看", "00106", "/catCard", null),
  // new MenuList("Test", "00107", "/test", null),
];

let ManageMenuList = [
  new MenuList("审批", "00201", "/approval", null),
  new MenuList("猫猫档案", "000202", "/catInfo", null),
  new MenuList("修改基础信息", "000203", "/modifyBasicInformation", null),
];

let SuperUserList = [
  new MenuList("修改用户信息", "000301", "/users", null),
];

function getMenuByAuth(data) {
  let authLevel = new AuthLevel(data.userGroupEnum, data.staffIdentityEnum);
  let normalUserMenu = new MenuList("普通用户菜单", "001", "#", normalMenuList);
  let managerMenu = new MenuList("管理员菜单", "002", "#", ManageMenuList);
  let superUserMenu = new MenuList("超级用户菜单", "003", "#", SuperUserList);
  let authList = [normalUserMenu];
  if (authLevel._userGroupEnum === 0)
    authList = authList.concat(managerMenu).concat(superUserMenu);
  else if (authLevel._staffIdentityEnum === 0)
    authList = authList.concat(managerMenu);
  return authList;
}

export default {
  getMenuByAuth,
};
