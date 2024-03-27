// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/alterInfo */
export async function alterInfo(
  body: API.UserAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/user/alterInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /user/alterPassword */
export async function alterPassword(
  body: API.UserAlterPasswordRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/user/alterPassword`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /user/getAvatar */
export async function getAvatar(options?: { [key: string]: any }) {
  return request<API.ResultDataString>(`/api/user/getAvatar`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /user/getSelfInfo */
export async function getSelfInfo(options?: { [key: string]: any }) {
  return request<API.ResultDataUser>(`/api/user/getSelfInfo`, {
    method: 'POST',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /user/getToken */
export async function getToken(body: API.UserLoginRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultDataUserLoginTokenVO>(`/api/user/getToken`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /user/register */
export async function userRegister(
  body: API.UserRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/user/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /user/upload */
export async function singleFileUpload(body: {}, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/user/upload`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
