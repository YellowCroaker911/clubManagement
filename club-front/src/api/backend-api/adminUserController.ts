// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /admin/admit */
export async function clubAdmit(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubAdmitParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/admin/admit`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /admin/backup */
export async function backup(options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/admin/backup`, {
    method: 'POST',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /admin/delete */
export async function clubDelete(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubDeleteParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/admin/delete`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /admin/download */
export async function download(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.downloadParams,
  options?: { [key: string]: any },
) {
  return request<any>(`/api/admin/download`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /admin/getAllBackUpFiles */
export async function getAllBackUpFiles(options?: { [key: string]: any }) {
  return request<API.ResultDataListFileDetailsVO>(`/api/admin/getAllBackUpFiles`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /admin/recovery */
export async function recovery(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.recoveryParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/admin/recovery`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
