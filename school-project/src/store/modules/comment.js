import {post,get,post_arry,Del} from '../axios'
export default{
    namespaced:true,
    state:{
        name:"comment",
        type:[],
        status:[],
        list:[],
        message:"",
        dialogVisible:false,
        loading:false,
        listQuery: {
            page: 1,
            limit: 5
          },
          type:[],
          employee:[],
          total:null
    },
    getters:{

    },
    mutations:{
        //重置message
        actionMsg(state,message){
            state.message = message;
        },
        // 模态框
        restVisible(state,visible){
            state.dialogVisible = visible;
        },
        //重置加载 
        resetLoading(state,loading){
            state.loading = loading;
        },
        digLog_flag(state){
            state.dialogVisible = false
        },
        vclist(state,list){
            state.list = list
        },
        findlist(state,list){
            state.list = list
        }

    },   
    actions:{
        // 批量删除
        handleToBatchDelete(context,ids){
            if(ids.length!==0){return post_arry('greenHouse/deviceBatchDelete',{ids:ids}).then((result)=>{
                context.commit("actionMsg",result.statusText)
                context.dispatch("findAll")
            })}
        },
        deleteById(context,id){
          Del('/inform/delete?id='+id).then((result)=>{
              console.log('----',result)
            context.commit("actionMsg",result.statusText)
            context.dispatch("findAll")
          }) 
        },
        openModel(context){
             context.state.dialogVisible = true
        },
        closeModel(context){
            context.commit('digLog_flag')
        },
        saveOrUpdate(context,customer){
          return post('/inform/update',customer).then((result)=>{
                // context.commit("actionMsg",result.statusText)
                // context.dispatch("closeModel")
                // context.dispatch("findAll")
            })
        },
        saveOrAdd(context,customer){
            return post('/inform/insert',customer).then((result)=>{
                  // context.commit("actionMsg",result.statusText)
                  // context.dispatch("closeModel")
                  // context.dispatch("findAll")
              })
          },
        //分页查询
        findAll(context){
            post('/inform/findAll',{page:context.state.listQuery.page,
                pagesize:context.state.listQuery.limit}).then((res)=>{
                context.commit("findlist",res.data.data)  
                context.state.type =res.data.type
                context.state.status =res.data.status
                context.state.total = res.data.total
            })  
        },
        //模糊查询
        fuzzyQuery({commit},query){
            get('/inform/fuzzyQuery',query).then((result)=>{
                commit('vclist',result.data.data)
           })
        },
    }}
   