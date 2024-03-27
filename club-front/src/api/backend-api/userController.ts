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

/** 此处后端没有提供注释 POST /user/getInfo */
export async function getInfo(options?: { [key: string]: any }) {
  return request<API.ResultDataUser>(`/api/user/getInfo`, {
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
