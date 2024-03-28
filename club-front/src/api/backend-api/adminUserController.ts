// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 GET /admin/admit */
export async function clubAdmit(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubAdmitParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/admin/admit`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /admin/delete */
export async function clubDelete(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubDeleteParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/admin/delete`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
