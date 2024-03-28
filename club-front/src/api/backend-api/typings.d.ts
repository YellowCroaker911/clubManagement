declare namespace API {
  type Activity = {
    id?: number;
    clubId?: number;
    name?: string;
    info?: string;
    title?: string;
    beginTime?: string;
    endTime?: string;
    address?: string;
    sign?: string;
    money?: number;
    joinPeople?: number;
    attendancePeople?: number;
    summary?: string;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type ActivityAlterInfoRequestDTO = {
    id: string;
    name: string;
    info: string;
    title: string;
    beginTime?: string;
    endTime?: string;
    address: string;
    sign: string;
    money: string;
    summary?: string;
  };

  type activityCancelParams = {
    id: string;
  };

  type activityDeleteParams = {
    id: string;
  };

  type activityPayParams = {
    id: string;
  };

  type ActivityReleaseRequestDTO = {
    clubId: string;
    name: string;
    info: string;
    title: string;
    beginTime?: string;
    endTime?: string;
    address: string;
    sign: string;
    money: string;
  };

  type activitySignInParams = {
    id: string;
  };

  type activitySignUpParams = {
    id: string;
  };

  type Club = {
    id?: number;
    name?: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
    member?: number;
    activityNumber?: number;
    money?: number;
    presidentId?: number;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type clubAdmitParams = {
    id: string;
  };

  type ClubAlterInfoRequestDTO = {
    id: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
  };

  type clubDeleteParams = {
    id: string;
  };

  type clubExitParams = {
    id: string;
  };

  type clubJoinParams = {
    id: string;
  };

  type clubPassParams = {
    userId: string;
    clubId: string;
  };

  type ClubRegisterRequestDTO = {
    name: string;
  };

  type getActivitiesParams = {
    id: string;
  };

  type getUsersParams = {
    id: string;
  };

  type ResultDataListActivity = {
    status?: number;
    message?: string;
    data?: Activity[];
    timestamp?: number;
  };

  type ResultDataListClub = {
    status?: number;
    message?: string;
    data?: Club[];
    timestamp?: number;
  };

  type ResultDataListUser = {
    status?: number;
    message?: string;
    data?: User[];
    timestamp?: number;
  };

  type ResultDataObject = {
    status?: number;
    message?: string;
    data?: Record<string, any>;
    timestamp?: number;
  };

  type ResultDataString = {
    status?: number;
    message?: string;
    data?: string;
    timestamp?: number;
  };

  type ResultDataUser = {
    status?: number;
    message?: string;
    data?: User;
    timestamp?: number;
  };

  type ResultDataUserLoginTokenVO = {
    status?: number;
    message?: string;
    data?: UserLoginTokenVO;
    timestamp?: number;
  };

  type User = {
    id?: number;
    username?: string;
    password?: string;
    name?: string;
    avatar?: string;
    gender?: number;
    phone?: string;
    email?: string;
    role?: number;
    money?: number;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type UserAlterInfoRequestDTO = {
    name?: string;
    gender?: string;
    phone?: string;
    email?: string;
  };

  type UserAlterPasswordRequestDTO = {
    oldPassword: string;
    newPassword: string;
    confirmedPassword: string;
  };

  type userKickParams = {
    userId: string;
    clubId: string;
  };

  type UserLoginRequestDTO = {
    username: string;
    password: string;
  };

  type UserLoginTokenVO = {
    token?: string;
  };

  type UserRegisterRequestDTO = {
    username: string;
    password: string;
    confirmedPassword: string;
    role: string;
  };
}
