import {get1,get,post,post_arry} from '../axios'
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
import ElementUI from 'element-ui'
Vue.use(ElementUI)
const waiters = {
    //使用子仓库时，要开启命名空间
    namespaced:true,
    state:function(){
        return {
            title:'',
            list:[],
            total: 0,
            listQuery: {
              page:1,
              limit:5,
            },
            dialogVisible:false,
            waiter_info:{}
        }
    },
    mutations:{
        resetList(state,list){
            state.list = list
        },
        vclist(state,list){
            state.list = list
        }
    },
    actions:{
        fetchData(context){
            // get1('/movieTicket/userFindAll').then((res)=>{
            //     console.log('+++',res)
            //     context.state.list=res.data     
            // })
            // 分页
            post('/user/findAll',{page:context.state.listQuery.page,
                pagesize:context.state.listQuery.limit}).then((res)=>{
                let list = res.data.data
                list.forEach(item => {
                    item.disable = !!(item.disable)
                });
                context.state.list=list;
                context.state.total = Number(res.data.total)          
            })    
        },
        handleAdd(context){
            context.state.title='新增用户信息'
            context.state.waiter_info={}
            context.state.dialogVisible = true
        },
        handleClose(context){
            context.state.dialogVisible = false
        },
        handleSave(context){
            if((context.state.waiter_info.realname == undefined) || (context.state.waiter_info.age == undefined) || (context.state.waiter_info.phone == undefined)
                || (context.state.waiter_info.email == undefined) || (context.state.waiter_info.address == undefined) ){
               alert('您有未填写的消息!')
            }else{
                console.log('00000',context.state.waiter_info.name) 
                post('/user/updateOne',context.state.waiter_info).then((data)=>{
                    context.dispatch('fetchData')//操作完成后更新一次数据
                    ElementUI.Notification({
                        title: '保存',
                        message: '保存成功',
                        type: 'success',
                        duration: 2000
                    })
                })
                context.state.dialogVisible = false
            }        
        },
        handleClick(context,data){
            context.state.waiter_info = data
            context.state.dialogVisible = true
            context.state.title='用户信息'

        },
        handleDelect(context,id){
            get("greenHouse/employeeDelete?id="+id).then((resp)=>{
                ElementUI.Notification({
                    title: '删除',
                    message: '删除成功',
                    type: 'warning',
                    duration: 2000
                })
                context.dispatch('fetchData')
            })
        },
        //模糊查询
        fuzzyQuery({commit},query){
            get('/user/fuzzyQuery',query).then((result)=>{
                commit('vclist',result.data.data)
           })
        },
    }
}
export default waiters