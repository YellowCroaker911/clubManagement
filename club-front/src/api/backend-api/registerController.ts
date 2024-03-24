// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/account/register/ */
export async function register(body: API.UserRegisterRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultData>(`/api/user/account/register/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
