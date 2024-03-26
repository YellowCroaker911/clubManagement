import axios from 'axios'
import {Exception} from "sass";

const httpRequest = axios.create({
  timeout: 1000 * 30,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json; charset=utf-8'
  }
})

export default httpRequest;

/**
 * 请求拦截器
 */
httpRequest.interceptors.request.use(config=>{
  console.log("请求拦截器", config);
  if(sessionStorage.getItem('jwt')){
    config.headers.Authorization = sessionStorage.getItem('jwt');
  }
  return config
},err=>{
  return Promise.reject(err);
})


/**
 * 相应拦截器
 */
httpRequest.interceptors.response.use(response => {
  console.log("相应拦截器", response);
  if(response.data.status !== 100){
    throw new Error(response.data.message);
  }
  return response
}, error => {
  return Promise.reject(error)
})
