<template>
  <div id='order'>
    <!--    表格   -->
    <el-table
        ref="multipleTable"
        :data="list"
        max-height="500"
        tooltip-effect="dark"
        border
        @selection-change="handleSelectionChange"
        style="width: 100%"
    >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="userName"
          label="用户名"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          prop="total"
          label="签到天数"
          show-overflow-tooltip>
      </el-table-column>

    </el-table>
    <!--    分页    -->
    <pagination
        :total= this.total
        :page.sync="limitQuery.page"
        :limit.sync="limitQuery.pagesize"
        @pagination="query()"
    />
  </div>
</template>

<script>
import {post,get,Del_arry,Del} from '../../store/axios'
import Pagination from "@/components/Pagination";
export default {
  components: { Pagination },
  data(){
    return {
      pay:{},
      dialogVisible:false,
      title:"",
      ids:[],
      content:"",
      list:[],
      limitQuery:{
        page:1,
        pagesize:10
      },
      total:0,
      visible:false,
      form:{},
      waiterId:{},
      order:{},
    }
  },
  methods: {
    query(){
      get('/sign/signAll').then((res)=>{
        this.list = res.data.data
        this.total = res.data.data.length
      })
    },
    //将时间转化为正常格式
    dateFormat(row, column) {
      var date = new Date(row.time) ;//时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + '-';
      var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-'
      var D = date.getDate() + ' '
      var h = date.getHours() + ':'
      var m = date.getMinutes() + ':'
      var s = date.getSeconds()
      return Y+M+D+h+m+s
    },
    //  获取id
    handleSelectionChange(val) {
      this.ids = val.map((item) => item.id);
    },
    //修改
    handleToEdit(row) {
      this.dialogVisible = true
      this.title = "修改账单";
      this.pay = row
    },
    handleClick(){
      this.query(this.status);
    },
  },
  //加载数据
  created() {
    this.query();
  },


}
</script>

<style>
.Top{
  margin-bottom:10px;
  display: flex;
  color: rgb(233, 141, 4);
  justify-content: space-between;
}
.btns {
  margin-left: 10px;
}
#order{
  padding: 20px;
}
</style>