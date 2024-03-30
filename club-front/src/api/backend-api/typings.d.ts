declare namespace API {
  type Activity = {
    id?: number;
    clubId?: number;
    name?: string;
    info?: string;
    title?: string;
    beginTime?: string;
    endTime?: string;
    signBeginTime?: string;
    signEndTime?: string;
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
    beginTime: string;
    endTime: string;
    signBeginTime: string;
    signEndTime: string;
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
    beginTime: string;
    endTime: string;
    signBeginTime: string;
    signEndTime: string;
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

  type ActivityWithUserStateVO = {
    id?: number;
    clubId?: number;
    clubName?: string;
    payStatus?: number;
    joinStatus?: number;
    name?: string;
    info?: string;
    title?: string;
    beginTime?: string;
    endTime?: string;
    signBeginTime?: string;
    signEndTime?: string;
    address?: string;
    sign?: string;
    money?: number;
    joinPeople?: number;
    attendancePeople?: number;
    summary?: string;
    createTime?: string;
    updateTime?: string;
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
    isAdmitted?: number;
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

  type clubFileUploadParams = {
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

  type ClubWithUserStateVO = {
    id?: number;
    contributionMoney?: number;
    isPassed?: number;
    name?: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
    member?: number;
    activityNumber?: number;
    money?: number;
    isAdmitted?: number;
    presidentId?: number;
    createTime?: string;
    updateTime?: string;
  };

  type getActivitiesParams = {
    id: string;
  };

  type getActivityByIdParams = {
    id: string;
  };

  type getClubAvatarParams = {
    id: string;
  };

  type getClubByIdParams = {
    id: string;
  };

  type getSelfActivityByIdParams = {
    activityId: string;
  };

  type getSelfClubActivityByClubIdParams = {
    clubId: string;
  };

  type getUserActivityByIdParams = {
    userId: string;
    activityId: string;
  };

  type getUserClubExtendClubParams = {
    clubId: string;
  };

  type getUserClubExtendUserParams = {
    clubId: string;
  };

  type listUserByActivityIdParams = {
    activityId: number;
  };

  type ResultDataActivityWithUserStateVO = {
    status?: number;
    message?: string;
    data?: ActivityWithUserStateVO;
    timestamp?: number;
  };

  type ResultDataClub = {
    status?: number;
    message?: string;
    data?: Club;
    timestamp?: number;
  };

  type ResultDataListActivity = {
    status?: number;
    message?: string;
    data?: Activity[];
    timestamp?: number;
  };

  type ResultDataListActivityWithUserStateVO = {
    status?: number;
    message?: string;
    data?: ActivityWithUserStateVO[];
    timestamp?: number;
  };

  type ResultDataListClub = {
    status?: number;
    message?: string;
    data?: Club[];
    timestamp?: number;
  };

  type ResultDataListClubWithUserStateVO = {
    status?: number;
    message?: string;
    data?: ClubWithUserStateVO[];
    timestamp?: number;
  };

  type ResultDataListUser = {
    status?: number;
    message?: string;
    data?: User[];
    timestamp?: number;
  };

  type ResultDataListUserActivityExtendVO = {
    status?: number;
    message?: string;
    data?: UserActivityExtendVO[];
    timestamp?: number;
  };

  type ResultDataListUserClubExtendUserVO = {
    status?: number;
    message?: string;
    data?: UserClubExtendUserVO[];
    timestamp?: number;
  };

  type ResultDataListUserWithUserActivityStateVO = {
    status?: number;
    message?: string;
    data?: UserWithUserActivityStateVO[];
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

  type ResultDataUserActivityExtendVO = {
    status?: number;
    message?: string;
    data?: UserActivityExtendVO;
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

  type UserActivityExtendVO = {
    id?: number;
    payStatus?: number;
    joinStatus?: number;
    createTime?: string;
    updateTime?: string;
    clubId?: number;
    clubName?: string;
    name?: string;
    info?: string;
    title?: string;
    beginTime?: string;
    endTime?: string;
    signBeginTime?: string;
    signEndTime?: string;
    address?: string;
    sign?: string;
    money?: number;
    joinPeople?: number;
    attendancePeople?: number;
    summary?: string;
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

  type UserClubExtendUserVO = {
    userClubId?: number;
    userId?: number;
    clubId?: number;
    contributionMoney?: number;
    createTime?: string;
    updateTime?: string;
    isPassed?: number;
    username?: string;
    name?: string;
    avatar?: string;
    gender?: number;
    phone?: string;
    email?: string;
    money?: number;
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

  type UserWithUserActivityStateVO = {
    id?: number;
    payStatus?: number;
    joinStatus?: number;
    username?: string;
    name?: string;
    avatar?: string;
    gender?: number;
    phone?: string;
    email?: string;
  };
}
