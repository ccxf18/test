<template>
    <div id='order'>
      <div class="Top">
        <div style="display:flex">
          <span style="width:80px;line-height:40px;color: cadetblue">用户名:</span>
          <el-input style="margin-top:5px" v-model="username" size="small" placeholder="根据用户名模糊查询" @change='fuQuery'></el-input>
        </div>
      </div>
         <el-tabs v-model="limitQuery.status" @tab-click="handleClick">
            <el-tab-pane label="已处理" name="已处理"></el-tab-pane>
            <el-tab-pane label="未处理" name="未处理"></el-tab-pane>
         </el-tabs>
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
               prop="username"
               label="用户名"
               show-overflow-tooltip>
           </el-table-column>
           <el-table-column
               align="center"
               prop="realName"
               label="姓名"
               show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                align="center"
            prop="content"
            label="报修内容">
            </el-table-column>
            <el-table-column
                align="center"
            prop="time"
            label="报修时间"
            :formatter='dateFormat'
            show-overflow-tooltip>
            </el-table-column>
           <el-table-column label="操作" align="center" width="170">
             <template slot-scope="scope">
               <el-button
                   @click="handleToDelete(scope.row.id)"
                   type="danger"
                   size="small"
               >删除</el-button
               >
               <el-button
                   v-if="scope.row.status == '未处理'"
                   type="primary"
                   @click.prevent="handleToEdit(scope.row.id)"
                   size="mini"
               >处理</el-button
               >
             </template>
           </el-table-column>

        </el-table>
        <!-- 分页 -->
      <pagination
          :total= this.total
          :page.sync="limitQuery.page"
          :limit.sync="limitQuery.limit"
          @pagination="query()"
      />
         <!-- 模态框 -->
    </div>
</template>

<script>
import {post,get,post_arry,Del} from '../../store/axios'
import Pagination from "@/components/Pagination";
export default {
    components: { Pagination },
    data(){
        return {
          ids:[],
          username:"",
          limitQuery:{
            status:"已处理",
            page:1,
            pagesize:10
          },
          list:[],
          total:0,
          visible:false,
          form:{},
          waiterId:{},
          order:{},
        }
    },
    methods: {
      //模糊查询
      fuQuery(){
        get("/repair/searchByOldMan?username="+this.username).then((res)=>{
          this.list = res.data.data
          this.total = res.data.total
        })
      },
      //查询
      query(){
        post('/repair/findAll',this.limitQuery).then((res)=>{
          this.list = res.data.data
          this.total = res.data.total
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
      //删除
      handleToDelete(id) {
        Del('/repair/delete?id='+id).then((res)=>{
          this.$notify({
            title: "删除成功",
            message: res.data.data,
            type: "success",
          });
          this.query();
        })
      },
      //  获取id
      handleSelectionChange(val) {
        this.ids = val.map((item) => item.id);
      },
      handleToEdit(id) {
        get("/repair/complete?id="+id).then((res)=>{
          this.$notify({
            title: "处理成功",
            message: res.data.data,
            type: "success",
          });
          this.query();
        })
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
#order{
    padding: 20px;
   
}
</style>