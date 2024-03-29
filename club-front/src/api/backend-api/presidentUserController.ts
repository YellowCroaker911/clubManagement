// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /president/activityAlterInfo */
export async function activityAlterInfo(
  body: API.ActivityAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/activityAlterInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/activityDelete */
export async function activityDelete(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activityDeleteParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/activityDelete`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/activityRelease */
export async function activityRelease(
  body: API.ActivityReleaseRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/activityRelease`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/alterInfo */
export async function clubAlterInfo(
  body: API.ClubAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/alterInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/kick */
export async function userKick(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userKickParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/kick`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/pass */
export async function clubPass(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubPassParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/pass`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/upload */
export async function clubFileUpload(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubFileUploadParams,
  body: {},
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/upload`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    params: {
      ...params,
    },
    data: body,
    ...(options || {}),
  });
}
