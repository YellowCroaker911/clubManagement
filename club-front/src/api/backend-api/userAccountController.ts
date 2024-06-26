// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /account/alterPassword */
export async function alterPassword(
  body: API.UserAlterPasswordRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/account/alterPassword`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /account/alterSelfInfo */
export async function alterSelfInfo(
  body: API.UserAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/account/alterSelfInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /account/getSelfAvatar */
export async function getSelfAvatar(options?: { [key: string]: any }) {
  return request<API.ResultDataString>(`/api/account/getSelfAvatar`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /account/getSelfInfo */
export async function getSelfInfo(options?: { [key: string]: any }) {
  return request<API.ResultDataUser>(`/api/account/getSelfInfo`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /account/getToken */
export async function getToken(body: API.UserLoginRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultDataUserLoginTokenVO>(`/api/account/getToken`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /account/register */
export async function userRegister(
  body: API.UserRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/account/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /account/upload */
export async function userFileUpload(body: {}, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/account/upload`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
