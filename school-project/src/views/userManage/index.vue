<template>
  <div class="app-container">
    <div class="Top">
      <div style="display: flex;margin-bottom:10px">
        <span
          style="width: 100px;line-height: 40px;color: cadetblue;">用户姓名:</span>
        <el-input
        size="small"
        style="margin-top:5px"
          v-model="waiter_info.name"
          placeholder="请输入用户姓名"
          @change="fuQuery"
        ></el-input>
      </div>
      <div>
        <!-- <el-button class="btn" @click="handleAdd()" type="primary" size="small"
          >新增</el-button
        > -->
      </div>
    </div>
    <!-- 表格 -->
    <el-table :data="list" border  style="width: 100%;" max-height="500">
      <!-- <el-table-column prop="id" align="center" label="用户编号"> </el-table-column> -->
      <el-table-column prop="realname" align="center" label="用户姓名" width="150">
      </el-table-column>
      <el-table-column prop="age" label="年龄" align="center" width="90"> </el-table-column>
      <el-table-column prop="gender" label="性别" align="center" width="90">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" align="center" width="150">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" align="center" width="180">
      </el-table-column>
      <el-table-column prop="username" align="center" label="账号" width="150">
      </el-table-column>
      <el-table-column prop="userpwd" align="center" label="密码" width="150">
      </el-table-column>
      <el-table-column prop="address" align="center" width="160" label="用户地址"> </el-table-column>
      <el-table-column prop="disable" align="center" label="权限">
        <template slot-scope="scope">
          <div>
            <el-switch
                @change='isDisable(scope.row)'
                v-model="scope.row.disable"
                active-color="#ff4949"
                inactive-color="#13ce66">
             </el-switch>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center"  fixed="right" width="150">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="primary" size="small"
            >查看</el-button
          >
          <!-- <el-button
            @click="handleDelect(scope.row.id)"
            type="danger"
            size="small"
            >删除</el-button
          > -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 表格结束 -->
    <!-- 分页 -->
    <template>
      <pagination
        :total= this.total
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="fetchData"
      />
    </template>
    <!-- 分页结束 -->
    <!-- 模态框 -->
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <p style="margin-top:-10px">
        员工姓名：<el-input
          disabled
          v-model="waiter_info.realname"
          placeholder="请输入姓名"
        ></el-input>
      </p>
      <!-- <p>密码：<el-input type="password" :rules="loginRules" v-model="waiter_info.password" placeholder="请输入内容"></el-input></p> -->
      <p>
        年龄：<el-input
        disabled
          v-model="waiter_info.age"
          placeholder="请输入年龄"
        ></el-input>
      </p>
      <p>
        手机号：<el-input
        disabled
          v-model="waiter_info.phone"
          placeholder="请输入手机号"
        ></el-input>
      </p>
      <p>
        邮箱<el-input
        disabled
          v-model="waiter_info.email"
          placeholder="请输入邮箱"
        ></el-input>
      </p>
      <p>
        地址：
        <el-input  disabled v-model="waiter_info.address" placeholder="请输入用户地址">
        </el-input>
      </p>
      <!-- <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </span> -->
    </el-dialog>
    <!-- /模态框 -->
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Pagination from "@/components/Pagination";
export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger",
      };
      return statusMap[status];
    },
  },
  data() {
    return {
      listLoading: false,
      loginRules: [
        { min: 6, max: 20, message: "密码长度6-20字符", trigger: "blur" },
      ],
    };
  },
  computed: {
    ...mapState("waiters", [
      "list",
      "title",
      "dialogVisible",
      "waiter_info",
      "listQuery",
      'total'
    ]),
  },
  created() {
    this.fetchData();
  },
  methods: {
    ...mapActions("waiters", [
      "fetchData",
      "handleAdd",
      "fuzzyQuery",
      "handleClose",
      "handleSave",
      "handleClick",
      "handleDelect",
    ]),
    //是否禁用用户
    isDisable(item){
      console.log('======',item)
      this.get('/user/disableUser',{username:item.username}).then((res)=>{
        if(res.data.code == 0){
          this.$message({
                message: res.data.data,
                type: 'success'
            });
          this.fetchData();
        }
      })
    },
    confirmTelephone() {
      if (!/^1[0-9]{10}$/.test(this.waiter_info.telephone)) {
        alert("请输入正确的手机号!");
        this.waiter_info.telephone = "";
      }
    },
    fuQuery() {
      this.fuzzyQuery({
        page: 1,
        pageSize: 10,
        name: this.waiter_info.name,
      });
    },
  },
};
</script>
<style>
#app-container {
  padding: 20px;
}
.btn {
  margin: 10px;
}
.Top {
  display: flex;
  width: 100%;
  justify-content:space-between;
}
</style>