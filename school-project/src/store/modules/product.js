import {get, post,get1,Del} from '../axios'
export default{
    namespaced:true,

    state:{
        name:"product",
        list:[],
        visible:false,
        message:"",
        status:[],
        listQuery: {
            page: 1,
            limit: 5
          },
        total:null,
        limit:0,
    },
    getters:{

    },
    mutations:{
        resetList(state,list){
            state.list = list
        },
        resetVisible(state,visible){
            state.visible = visible
        },
        // resetTitle(state,title){
        //     state.title = title
        // },
        resetMessage(state,message){
            state.message = message
        },
        actionMsg(state,message){
            state.message = message
        },
        resetState(state,message){
            state.status = message
        },
        vclist(state,message){
            state.list = message
        }
    },
    actions:{
        //模糊查询
        vaguequery(context,query){
            query.page = context.state.listQuery.page
            query.pageSize = context.state.listQuery.limit
            get('/worker/fuzzyQuery',query).then((result)=>{
                context.commit('vclist',result.data.data)
           })
        },
        // 打开模态框
        openModel({commit},visible){
            commit("resetVisible",true)
        },
        //关闭模态框
        closeModel({commit},visible){
            commit("resetVisible",false)
        },
        // 查询
        query(context){
            post("/worker/findAll",{page:context.state.listQuery.page,
                pagesize:context.state.listQuery.limit}).then((result)=>{
                context.commit('resetList',result.data.data)
                context.state.limit = result.data.total
                // commit('resetState',result.status)
            })
        },
        //删除
        deleteById(context,id){
            return Del("/worker/delete?id="+id)

        }, 
        //修改
      
        // 保存并更新
        saveOrUpdate(context,product){
           return post('/worker/update',product).then((result)=>{
                context.commit('resetMessage',result.statusText);
                // 关闭模态框
                context.dispatch('closeModel')
            })
        },
         // 新增
         saveOrAdd(context,product){
            return post('/worker/insert',product).then((result)=>{
                 context.commit('resetMessage',result.statusText);
                 // 关闭模态框
                 context.dispatch('closeModel')
             })
         }
    }
}