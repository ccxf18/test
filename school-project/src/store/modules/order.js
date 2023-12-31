import {get, post} from '../axios'
var qs = require('qs')
export default{
    namespaced:true,
    state:{
        list:[],
        params:{
            page:0,
            pageSize:5
            },
        // fenye:{}
        employee:[],
        orderStauts:[],//订单状态
    },
    getters:{

    },
    mutations:{
        resetList(state,list){
            state.list = list
        },
        // resetfenye(state,fenye){
        //     state.fenye = fenye
        // },
        resetOrder(state,employee){
            state.employee = employee
        },
        orderStatus(state,status){
            state.orderStauts = status
        }
        
    },
    actions:{
        query(context,status){
            get("greenHouse/orderFindAll?status="+status)
            .then((result)=>{
                //  result.data.list.map((item)=>{
                //                         item.orderTime= moment(item.orderTime).format("YYYY-MM-DD HH:mm:ss")
                //                     })
                context.commit('resetList',result.data.data)
            })
        },
        queryAll(context){
            get("greenHouse/orderFindAll")
            .then((result)=>{
                //  result.data.list.map((item)=>{
                //                         item.orderTime= moment(item.orderTime).format("YYYY-MM-DD HH:mm:ss")
                //                     })
                context.commit('resetList',result.data.data)
                context.commit('orderStatus',result.data.orderStatus)
            })
        },
        // 查询员工
        // sendOrder(context){
        //     get('/waiter/findAll').then((result)=>{
        //         context.commit('resetOrder',result.data)
        //     })
        // },
        // //派单
        // handler(context,order){
        //    return get('/order/sendOrder?'+qs.stringify(order))
        // }
    }
}