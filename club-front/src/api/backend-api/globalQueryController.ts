// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 GET /globalQuery/getActivityById */
export async function getActivityById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getActivityByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataActivity>(`/api/globalQuery/getActivityById`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getAllActivity */
export async function getAllActivity(options?: { [key: string]: any }) {
  return request<API.ResultDataListActivity>(`/api/globalQuery/getAllActivity`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getAllClub */
export async function getAllClub(options?: { [key: string]: any }) {
  return request<API.ResultDataListClub>(`/api/globalQuery/getAllClub`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getAllUser */
export async function getAllUser(options?: { [key: string]: any }) {
  return request<API.ResultDataListUser>(`/api/globalQuery/getAllUser`, {
    method: 'GET',
    ...(options || {}),
  });
}
