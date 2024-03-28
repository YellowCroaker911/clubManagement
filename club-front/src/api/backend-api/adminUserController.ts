// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /admin/admit */
export async function clubAdmit(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/admin/admit`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
