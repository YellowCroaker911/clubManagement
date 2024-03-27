// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /user/account/alterInfo/ */
export async function alterInfo(
  body: API.UserAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/user/account/alterInfo/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
