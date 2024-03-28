declare namespace API {
  type ActivityReleaseRequestDTO = {
    clubId: string;
    id?: string;
    name: string;
    info: string;
    title: string;
    beginTime: string;
    endTime: string;
    address: string;
    sign: string;
    money: string;
  };

  type ClubAlterInfoRequestDTO = {
    id: string;
    avatar?: string;
    info?: string;
    address?: string;
    contact?: string;
  };

  type ClubRegisterRequestDTO = {
    name: string;
  };

  type Id1DTO = {
    id: string;
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
