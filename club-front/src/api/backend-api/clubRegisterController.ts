// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /club/register/ */
export async function register1(
  body: API.ClubRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultData>(`/api/club/register/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
