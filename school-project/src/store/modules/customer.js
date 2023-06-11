import {post,get,post_arry,Del} from '../axios'
export default{
    namespaced:true,
    state:{
        name:"customer",
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
        //重置list
        resetList(state,list){
            state.list = list;
        },
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
        }

    },   
    actions:{
        // 批量删除
        handleToBatchDelete(context,ids){
            if(ids.length!==0){return post_arry('greenHouse/greenHouseBatchDelete',{ids:ids}).then((result)=>{
                context.commit("actionMsg",result.statusText)
                context.dispatch("findAll")
            })}
        },
        deleteById(context,id){
          Del('/people/delete?id='+id).then((result)=>{
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
          return post('/people/update',customer).then((result)=>{
                // context.commit("actionMsg",result.statusText)
                // context.dispatch("closeModel")
                // context.dispatch("findAll")
            })
        },
        saveOrAdd(context,customer){   
            return post('/people/insert',customer).then((result)=>{
                  // context.commit("actionMsg",result.statusText)
                  // context.dispatch("closeModel")
                  // context.dispatch("findAll")
              })
          },
        // findAll(context){
        //     context.commit("resetLoading",true)
        //     get('/movieTicket/memberFindAll').then((result)=>{
        //         context.commit('resetList',result.data);
        //     }).finally(()=>{
        //     context.commit("resetLoading",false)
        //     })
        // },
        //分页查询
        findAll(context){
            console.log('00000')
            post('/people/findAll',{page:context.state.listQuery.page,
                pagesize:context.state.listQuery.limit}).then((res)=>{
                context.state.list=res.data.data;
                context.state.type=res.data.type;
                context.state.employee=res.data.employee;
                context.state.total = res.data.total;           
            })  
        },
        //模糊查询
        fuzzyQuery({commit},query){
            get('/people/fuzzyQuery',query).then((result)=>{
                commit('vclist',result.data.data)
           })
        },
    }}