// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 GET /user/account/info/ */
export async function getInfo(options?: { [key: string]: any }) {
  return request<API.ResultDataUser>(`/api/user/account/info/`, {
    method: 'GET',
    ...(options || {}),
  });
}
