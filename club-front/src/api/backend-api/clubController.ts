// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 POST /club/admit */
export async function clubAdmit(body: API.ClubAdmitRequestDTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/club/admit`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /club/alterInfo */
export async function clubAlterInfo(
  body: API.ClubAlterInfoRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/club/alterInfo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /club/getInfo */
export async function clubGetInfo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.clubGetInfoParams,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataClub>(`/api/club/getInfo`, {
    method: 'GET',
    params: {
      ...params,
      clubGetInfoRequestDTO: undefined,
      ...params['clubGetInfoRequestDTO'],
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /club/register/ */
export async function clubRegister(
  body: API.ClubRegisterRequestDTO,
  options?: { [key: string]: any },
) {
  return request<API.ResultDataObject>(`/api/club/register/`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
