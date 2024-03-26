// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/account/alter/ */
export async function getToken1(
  body: API.UserAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataUserLoginTokenVO>(`/api/user/account/alter/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
