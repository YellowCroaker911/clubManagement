// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 GET /globalQuery/getActivitiesByClubId */
export async function getActivities(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getActivitiesParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListActivity>(`/api/globalQuery/getActivitiesByClubId`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getActivityById */
export async function getActivityById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getActivityByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataActivityWithUserStateVO>(`/api/globalQuery/getActivityById`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getAllActivity */
export async function getAllActivity(options?: { [key: string]: any }) {
  return request<API.ResultDataListActivityWithUserStateVO>(`/api/globalQuery/getAllActivity`, {
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

/** 此处后端没有提供注释 GET /globalQuery/getClubAvatar */
export async function getClubAvatar(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getClubAvatarParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataString>(`/api/globalQuery/getClubAvatar`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getClubById */
export async function getClubById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getClubByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataClub>(`/api/globalQuery/getClubById`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getUserActivityById */
export async function getUserActivityById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserActivityByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataUserActivityExtendVO>(`/api/globalQuery/getUserActivityById`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getUserClubExtendClub */
export async function getUserClubExtendClub(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserClubExtendClubParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListClubWithUserStateVO>(`/api/globalQuery/getUserClubExtendClub`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /globalQuery/getUserClubExtendUser */
export async function getUserClubExtendUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserClubExtendUserParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListUserClubExtendUserVO>(`/api/globalQuery/getUserClubExtendUser`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
