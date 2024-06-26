// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /common/cancel */
export async function activityCancel(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activityCancelParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/cancel`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/exit */
export async function clubExit(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubExitParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/exit`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /common/getAllClubWithCurrentUser */
export async function getAllClubWithCurrentUser(options?: { [key: string]: any }) {
  return request<API.ResultDataListClubWithUserStateVO>(`/api/common/getAllClubWithCurrentUser`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /common/getSelfActivityById */
export async function getSelfActivityById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getSelfActivityByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataUserActivityExtendVO>(`/api/common/getSelfActivityById`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /common/getSelfClubActivities */
export async function getSelfClubActivities(options?: { [key: string]: any }) {
  return request<API.ResultDataListActivityWithUserStateVO>(`/api/common/getSelfClubActivities`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /common/getSelfClubActivityByClubId */
export async function getSelfClubActivityByClubId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getSelfClubActivityByClubIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataListUserActivityExtendVO>(
    `/api/common/getSelfClubActivityByClubId`,
    {
      method: 'GET',
      params: {
        ...params,
      },
      ...(options || {}),
    },
  );
}

/** 此处后端没有提供注释 GET /common/getSelfClubs */
export async function getSelfClubs(options?: { [key: string]: any }) {
  return request<API.ResultDataListClub>(`/api/common/getSelfClubs`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /common/getSelfClubsAsPresident */
export async function getSelfClubsAsPresident(options?: { [key: string]: any }) {
  return request<API.ResultDataListClub>(`/api/common/getSelfClubsAsPresident`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/join */
export async function clubJoin(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubJoinParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/join`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/pay */
export async function activityPay(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activityPayParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/pay`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/register */
export async function clubRegister(
  body: API.ClubRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/signIn */
export async function activitySignIn(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activitySignInParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/signIn`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /common/signUp */
export async function activitySignUp(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.activitySignUpParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/common/signUp`, {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
