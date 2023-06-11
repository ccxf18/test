import qs from 'qs'
import axios from 'axios'
//全局配置
// const token=
// axios.defaults.headers.common["token"]="token"
axios.defaults.headers.post["Content-Type"]="application/x-www-form-urlencoded;charset=UTF-8";
axios.defaults.baseURL = '/bill'
// axios.defaults.baseURL = '/vue-admin-template'


//配置请求拦截
// axios.interceptors.request.use(function (config) {
//     // 在发送请求之前把data转换为查询字符串
//     if(config.method=="post"){
//       config.data=qs.stringify(config.data)
//     }
   
//     return config;
//   }, function (error) {
//     // 对请求错误做些什么
//     return Promise.reject(error);
//   });
export function post(url,data){
    return axios({
        method: "post",
        url,
        data:JSON.stringify(data),
        timeout:10000,
        headers:{
            'X-Requested-With':'XMLHttpRequest',
            'Content-Type':'application/json '
        }
    })
}
export function post_arry(url,data){
    return axios({
        method: "post",
        url,
        data:JSON.stringify(data),
        // data:qs.stringify(data,{arrayFormat:"repeat"}),
        timeout:10000,
        headers:{
            'X-Requested-With':'XMLHttpRequest',
            // 'Content-Type':'application/x-www-form-urlencoded;;charset=UTF-8'
            'Content-Type':'application/json '
        }
    })
}

export function post_form(url,data){
    return axios({
        method: "post",
        url,
        data:data,
        // data:qs.stringify(data,{arrayFormat:"repeat"}),
        timeout:10000,
        headers:{
            // 'X-Requested-With':'XMLHttpRequest',
            'Content-Type':'multipart/form-data'
            // 'Content-Type':'application/json '
        }
    })
}
export function get(url,params){
    return axios({
        method:'get',
        url,
        params,
        timeout:10000,
        headers:{
            // 'X-Requested-With':'XMLHttpRequest',
            'Content-Type':'application/x-www-form-urlencoded;;charset=UTF-8'
        }
    })
}
export function get1(url){
    return axios({
        method:'get',
        url,
        timeout:10000,
        headers:{
            // 'X-Requested-With':'XMLHttpRequest',
            'Content-Type':'application/x-www-form-urlencoded;;charset=UTF-8'
        }
    })
}
//删除
export function Del(url){
    return axios({
        method:'delete',
        url,
        timeout:10000,
        headers:{
            // 'X-Requested-With':'XMLHttpRequest',
            'Content-Type':'application/x-www-form-urlencoded;;charset=UTF-8'
        }
    })
}
//配置返回拦截器,将后台的参数结果设置到response
axios.interceptors.response.use(function (response){
    //解构将response.data解构出来
    let {data} = response;
    response.data = data;
    response.status = data.status;
    response.statusText = data.message
    
    return response 
},function(error){
    //Do something with response error
    return Promise.reject(error);
});
export default axios;
 