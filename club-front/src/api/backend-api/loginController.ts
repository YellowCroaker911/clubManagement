// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/account/token/ */
export async function getToken(body: API.UserLoginRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultDataUserLoginTokenVO>(`/api/user/account/token/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
