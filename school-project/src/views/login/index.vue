<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">个人账单管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button :loading="loading" type="warning" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登 录</el-button>

      <!-- <div class="tips">
        <div style="margin-right:20px;">用户名: admin/editor</div>
        <div style="margin-top:10px"> 密码: 6位字母或数字</div>
      </div> -->

    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback()
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length !== 6) {
        callback()
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: 'test',
        password: 'test'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        sessionStorage.setItem('username',this.loginForm.username)
        if (valid) {
          // this.$router.push({ name:'Dashboard' })
          // this.loading = true
          // this.loginForm.username == 'admin' ? sessionStorage.setItem('role',1) : sessionStorage.setItem('role',2)
          // setTimeout(()=>{
          //   //  this.$router.push({ path:'/dashboard' })
          //    this.$router.push({ name:'Dashboard' })
          // },2000) 
          this.$store.dispatch('user/login', this.loginForm).then((res) => {
            console.log('----',res)
            if(res.code == 1000){
              this.$message({
                message: res.data,
                type: 'warning'
            });
            this.loading = false
          }else{
            this.$router.push({ name:'Dashboard' })
            this.loading = false
          }
           
          }).catch(() => {
            this.loading = false
          })

        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:black;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#889aa4;

.login-container {
  min-height: 100%;
  width: 100%;
  // background-color: $bg;
  background-image: url('../../assets/404_images/1.jpeg');
   background-repeat: no-repeat; 
   background-size:cover;
  overflow: hidden;

  .login-form {
    background-color: aliceblue;
    position: relative;
    width: 500px;
    max-width: 100%;
    padding: 30px 35px 30px;
    margin: 13% auto;
    overflow: hidden;
    border-radius: 15px;
 
  }

  .tips {
    font-size: 14px;
    color: #889aa4;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
     color: black;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      // color: $light_gray;
      color: black;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: black;
    cursor: pointer;
    user-select: none;
  }
}
</style>
