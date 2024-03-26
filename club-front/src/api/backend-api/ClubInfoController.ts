// @ts-ignore
/* eslint-disable */
import request from '@/api/httpRequest';

/** 此处后端没有提供注释 GET /user/club/info/ */
export async function getinfo(options?: { [key: string]: any }) {
    return request<API.ResultDataUser>(`/api/user/club/info/`, {
        method: 'GET',
        ...(options || {}),
    });
}
