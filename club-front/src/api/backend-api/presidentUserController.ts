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

/** 此处后端没有提供注释 GET /president/activityDelete */
export async function activityDelete(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activityDeleteParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/activityDelete`, {
    method: 'GET',
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

/** 此处后端没有提供注释 GET /president/getActivitiesByClubId */
export async function getActivities(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getActivitiesParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListActivity>(`/api/president/getActivitiesByClubId`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /president/getUsersByClubId */
export async function getUsers(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUsersParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListUser>(`/api/president/getUsersByClubId`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /president/kick */
export async function userKick(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userKickParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/kick`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /president/pass */
export async function clubPass(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubPassParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/president/pass`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
