// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/account/alterPassword/ */
export async function alterPassword(
  body: API.UserAlterPasswordDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/user/account/alterPassword/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
