<template>
  <div id='order'>
<!--    头部    -->
    <div class="Top">
      <div style="display:flex">
        <span style="width:100px;line-height:40px;color: cadetblue">评论内容:</span>
        <el-input style="margin-top:5px" v-model="content" size="small" placeholder="根据评论内容模糊查询" @change='fuQuery'></el-input>
      </div>
      <div class="btns">
        <el-button size="small" type="danger" @click="handleToDel"
          >批量删除</el-button>
      </div>
    </div>
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
          prop="id"
          label="id"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          prop="author"
          label="发布者"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          prop="content"
          label="内容"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          prop="collect"
          label="收藏数">
      </el-table-column>
      <el-table-column
          align="center"
          prop="like"
          label="点赞数">
      </el-table-column>
      <el-table-column
          align="center"
          prop="time"
          label="创建时间"
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
        </template>
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
      limitQuery:{
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
      get("/chat/fuzzyQuery?content="+this.content).then((res)=>{
        this.list = res.data.data
        this.total = res.data.total
      })
    },
    //查询
    query(){
      post('/chat/findAll',this.limitQuery).then((res)=>{
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
      Del('/chat/delete?id='+id).then((res)=>{
        this.$notify({
          title: "删除成功",
          message: res.data.data,
          type: "success",
        });
        this.query();
      })
    },
    //批量删除
    handleToDel(){
      var param = this.ids+ ""
      Del("/chat/deleteBatch?ids="+param).then((res)=>{
        this.$notify({
          title: "批量删除成功",
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