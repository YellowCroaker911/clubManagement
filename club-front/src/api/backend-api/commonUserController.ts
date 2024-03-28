// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /common/join */
export async function clubJoin1(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/common/join`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/pay */
export async function activityPay(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/common/pay`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/register */
export async function clubRegister(
  body: API.ClubRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/signIn */
export async function activitySignIn(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/common/signIn`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/signUp */
export async function activitySignUp(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/common/signUp`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
