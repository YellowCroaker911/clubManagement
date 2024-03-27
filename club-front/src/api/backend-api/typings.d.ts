declare namespace API {
  type Club = {
    id?: number;
    name?: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
    member?: number;
    money?: number;
    presidentId?: number;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type ClubAdmitRequestDTO = {
    id: string;
  };

  type ClubAlterInfoRequestDTO = {
    id: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
  };

  type clubGetInfoParams = {
    clubGetInfoRequestDTO: ClubGetInfoRequestDTO;
  };

  type ClubGetInfoRequestDTO = {
    id: string;
  };

  type ClubRegisterRequestDTO = {
    name: string;
    presidentId: string;
  };

  type ResultDataClub = {
    status?: number;
    message?: string;
    data?: Club;
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
