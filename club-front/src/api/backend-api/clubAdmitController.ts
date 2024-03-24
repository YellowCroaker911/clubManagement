// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /club/admit/ */
export async function admit(body: API.ClubAdmitRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultData>(`/api/club/admit/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
