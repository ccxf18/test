<template>
  <div class="list">
    <van-image class="bg" :src="img2" />
    <div class="body">
      <div class="logo">
        <van-image width="98" height="92" :src="img1" />
      </div>
      <div class="title">个人账单管理系统</div>
      <div class="login">
        <van-form>
        <!-- <van-form @submit="onSubmit"> -->
          <van-cell-group inset>
            <div>
              <van-field
                v-model="Obj.username"
                name="用户名"
                label="用户名"
                placeholder="请输入用户名/手机号/邮箱"
                :rules="[{ required: true, message: '请填写用户名' }]"
              />
            </div>
            <div style="display: flex">
              <van-field
                v-model="Obj.password"
                :type="passType"
                name="密码"
                label="密码"
                placeholder="请输入密码"
                :rules="[{ required: true, message: '请填写密码' }]"
              />
              <van-icon @click="openEye" class="eye" name="eye-o" />
              <van-icon @click="closeEye" class="eye" name="closed-eye" />
            </div>
          </van-cell-group>
          <div style="margin: 39px 16px">
            <van-button
              round
              block
              @click="onSubmit"
              type="primary"
              native-type="submit"
              class="btn"
            >
              登 录
            </van-button>
            <van-button
             style="margin-top:10px"
             @click="goRegister"
              round
              block
              type="primary"
              native-type="goRegister"
              class="btn"
            >
              注册
            </van-button>
          </div>
        </van-form>
        <van-button style="color:white;background:none;border:none"  @click="goforgetPwd" type="primary">忘记密码</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import CryptoJS from "crypto-js";
export default {
  data() {
    return {
      Obj: {
        username:'test',
        password:'test'
      },
      passType: "password",
      img1: require("../assets/images/bill.png"),
      img2: require("../assets/images/bg2.png"),
    };
  },
  methods: {
    //忘记密码
      goforgetPwd(){
        this.$router.push({name:'forgetPwd'});
    },
    //去注册
    goRegister(){
        this.$router.push({name:'register'});
        },
    onSubmit() {
      let params = this.Obj;
      //登陆
      this.$http.get("/user/login", {params}).then(
        (res) => {
          if (res.code == 0) {
            sessionStorage.setItem("username", this.Obj.username);
            sessionStorage.setItem("userInfo", JSON.stringify(this.Obj));
            this.$router.push("/");
            // sessionStorage.setItem("token", res.token);
            //获取用户信息
            // this.$http.get("nvwa/getLoginContext").then((res) => {
            //    if(res.code == 0){
            //     this.Obj = {}
            //     sessionStorage.setItem("userInfo", JSON.stringify(res.context));
            //     this.$router.push("/");
            //    }else{
            //     this.$toast({
            //         msg: res.message,
            //         type: "warning",
            //       });
            //    }
            // });
          }else{
            this.$toast({
              msg: res.message,
              type: "warning",
            });
          }
        }
      );
    },
    openEye() {
      this.passType = "text";
    },
    closeEye() {
      this.passType = "password";
    },
    goModel() {
      this.$toast({
        msg: "成功提示",
        type: "success",
      });
    },
  },
};
</script>

<style scoped lang="less">
.list {
  height: 100vh;
  width: 100%;
  background-color: white;
  .bg{
    position: absolute;
    left: 0px;
    right: 0px;
    top:0px;
    bottom:0px;
  }
  .body {
    .logo {
      padding-top: 121px;
    }
    .title {
      margin-top: 26px;
      font-size: 28px;
      font-weight: 500;
      color: #6ea9f1;
      position: relative;
    }
    .login {
      margin-top: 89px;
    }
  }
  .eye {
    line-height: 50px;
    margin-right: 10px;
    height: 24px;
    width: 24px;
  }
  .btn {
    border:none;
    background: linear-gradient(135deg, #6ea9f1 0%, #608d71 100%);
  }
  .noPass {
    font-size: 16px;
    font-weight: 400;
    color: #7f7f7f;
  }
}
</style>
