declare namespace API {
  type ClubAdmitRequestDTO = {
    id: string;
  };

  type ClubRegisterRequestDTO = {
    name: string;
    president_id: string;
  };

  type ResultData = {
    status?: number;
    message?: string;
    data?: Record<string, any>;
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
    avatar?: string;
    gender?: string;
    phone?: string;
    email?: string;
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
