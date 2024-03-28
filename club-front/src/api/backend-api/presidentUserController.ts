// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

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

/** 此处后端没有提供注释 POST /president/checkMember */
export async function checkMember(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataListUser>(`/api/president/checkMember`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /president/pass */
export async function clubJoin(body: API.Id1DTO, options?: { [key: string]: any }) {
  return request<API.ResultDataObject>(`/api/president/pass`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
