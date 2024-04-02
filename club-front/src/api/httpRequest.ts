import axios from 'axios'

const httpRequest = axios.create({
  timeout: 1000 * 30,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json; charset=utf-8'
  }
})

export default httpRequest;


/*
 * @params {string} url 请求地址
 * @params {object} resOpts 请求配置参数
 */
export const downloadByUrl = (url: string, resOpts = {type:'get'}) => {
  const { type = 'get' } = resOpts
  const queryArgs = {
    url,
    method: type,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json; charset=utf-8',
      withCredentials: true,
    },
  }
  // tips: 这里直接返回的是response整体!
  return httpRequest.request(queryArgs)
}



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
  // console.log(response.headers["content-type"]);
  // console.log(response.headers);
  if((!response.headers["content-type"].toString().includes("x-download")) && response.data.status !== 100){
    throw new Error(response.data.message);
  }
  return response
}, error => {
  return Promise.reject(error)
})
