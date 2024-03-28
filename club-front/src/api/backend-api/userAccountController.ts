// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /account/alterInfo */
export async function alterInfo(
  body: API.UserAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/account/alterInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

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

/** 此处后端没有提供注释 GET /account/getAvatar */
export async function getAvatar(options?: { [key: string]: any }) {
  return request<API.ResultDataString>(`/api/account/getAvatar`, {
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
export async function singleFileUpload(body: {}, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/account/upload`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
