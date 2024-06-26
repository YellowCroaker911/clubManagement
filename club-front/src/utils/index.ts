import Vue from 'vue'
import router from '@/router'

/**
 * 获取uuid
 */
export function getUUID () {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    // @ts-ignore
    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
  })
}

export const getCurrentUser = (): API.User | undefined => {
  var user = sessionStorage.getItem('user');
  if(user === null)return undefined;
  return JSON.parse(user);
}

export const fen2Yuan = (amount:string | number | undefined):string => {
  if(amount === undefined)return '0.00';
  return (Number(amount) / 100).toFixed(2)
}

export const yuanToFen = (amount:string | number | undefined):string => {
  if(amount === undefined)return '0.00';
  return Math.round(Number(amount) * 100).toString()
}