<template>
    <div class="list">
        <tar-bar :title="title" />
        <div class="body" >
            <div class="top">
                <div class="top_left">
                    <van-image width="72" height="72" :src="img3" />
                </div>
                <div class="top_right">
                  <div style="font-size:20px;display: flex;">
                    <div>{{myInfo.username}}</div>
                    <div class="sign" @click="singUp">
                      <img :src="img1" style="width:10px;height:10px" alt=""> 每日签到</div>
                  </div>
                  <div style="font-size:20px;text-align: left;">{{myInfo.email}}</div>
                </div>
            </div>
            <div class="lunbo">
                <div class="imgs">
                    <div class="house" @click="showContract">
                        <div class="house_left">
                        <van-image width="50" height="50" :src="img1" />
                        </div>
                        <div class="house_right">
                        <div>修改信息</div>
                        </div>
                    </div>
                    <div class="house2" @click="showPopup">
                        <div class="house_left">
                        <van-image width="50" height="50" :src="img2" />
                        </div>
                        <div class="house_right">
                        <div>签到天数</div>
                        </div>
                    </div>
                    </div>
            </div>
            <div class="content">
                <div  @click="about">
                    <div>
                        <van-icon name="friends-o" />
                        <span>设置</span>
                    </div>
                   <div>
                       <van-icon name="arrow" />
                   </div>

                </div>
                <div  @click="about">
                    <div>
                        <van-icon name="question-o" />
                        <span> 关于我们</span>
                    </div>
                   <div>
                       <van-icon name="arrow" />
                   </div>
                </div>
                <div>
                    <div>
                        <van-icon name="description" />
                        <span>  当前软件版本</span>
                    </div>
                   <div>
                        <span>v0.01</span>
                       <van-icon name="arrow" />
                   </div>
                </div>
            </div>
        </div>
        <van-button class="btn" @click="loginOut" >退出登录</van-button>
        <van-popup style="width:80%;height:150px;" v-model="show">
          <img style="margin-top:10px" src="../../assets/images/gongxi.png" alt="">
          <div style="text-align:center">恭喜您</div>
          <div style="text-align:center">已经连续签到{{all || 3}}天啦</div>
        </van-popup>
    </div>
  </template>

  <script>
import tarBar from "../../components/tarBar/index.vue";
  export default {
    data() {
      return {
        all:0,
        show: false,
        myInfo:{},
        img1: require("../../assets/images/perctInfo.png"),
        img2: require("../../assets/images/sign.png"),
        img3: require("../../assets/images/touxiang.png"),
        title: "我的",
      };
    },
    components: { tarBar },
    created(){
      //  this.havInfo()
      this.userInfo()
    },
    methods:{
      showPopup() {
      let username = sessionStorage.getItem('username')
        this.$http.get("/sign/signDay?userName="+username).then((res)=>{
          if(res.code == 0){
           this.all = res.data
           this.show = true;
          }
        })
    },
      singUp(){
        let username = sessionStorage.getItem('username')
        this.$http.get("/sign/sign?userName="+username).then((res)=>{
          if(res.code == 0){
              this.$toast({
                msg: res.data,
                type: "success",
            })
          }
        })
      },
      userInfo(){
        let username = sessionStorage.getItem('username')
        this.$http.get("/user/findOne?username="+username).then((res)=>{
          if(res.code == 0){
            this.myInfo = res.data
            sessionStorage.setItem('userInfo',JSON.stringify(res.data))
          }
        })
      },
        my(){
          let username = sessionStorage.getItem('username')
          this.$http.get("/sign/signDay?userName="+username).then((res)=>{
          if(res.code == 0){
            console.log('====',res)
          }
        })

          // this.$router.push({name:'myInfo'})
            // this.$toast({
            //     msg: '暂未开放',
            //     type: "warn",
            // })
        },
        about(){
            this.$toast({
                msg: '暂未开放',
                type: "warn",
            })
        },
        havInfo(){
            this.myInfo = JSON.parse(sessionStorage.getItem('userInfo'))
            this.name =  this.myInfo.name || ''
            this.code =  this.myInfo.username || ''
            this.department = JSON.parse(this.myInfo.extInfo.department).name
        },
        showContract(){
            this.$router.push({name:'contract'})
        },
        showBill(){
          this.$router.push({name:'contract2'})
          //   this.$toast({
          //   msg: '暂未开放',
          //   type: "warn",
          // });
        },
        //登出
        loginOut(){
          let that = this
          that.$dialog({
              title: '登出提示',
              text: '是否确定退出登录',
              showCancelBtn: true,
              confirmText: '确认',
              cancel(content){
                this.$toast({
                  msg:'已取消'
                })
              },
              confirm(content) {
                this.$toast({
                  msg:'已登出'
                })
                sessionStorage.clear()
                // sessionStorage.removeItem('userInfo')
                that.$router.push({path:'/login'})
              },
            })
        }
    }
  };
  </script>

  <style scoped lang="less">
  .list {
    height: 94vh;
    width: 100%;
    overflow: scroll;
    // background-color: #F4F4F4;
    .body {
    // margin-top: 45px;
    .top {
      height: 160px;
      width: 100%;
      background-color: #6ea9f1;
      display: flex;
      padding: 34px 24px;
      .top_left{
        // opacity: 0.7;
        line-height: 60px;
        border-radius: 30px;
      }
      .top_right{
        .sign{
          margin-left: 10px;
          // color:red;
          text-align: center;
          font-size: 12px;
          width: 80px;
          border: 1px solid white;
          border-radius: 15px;
          height: 25px;
          line-height: 25px;
        }
        // padding-top: 20px;
        color: #DFE0E4;
        >:nth-child(1){
            margin-top: 5px;
            text-align: left;
            margin-left: 14px;
        }
        >:nth-child(2){
            // margin-top: 8px;
            margin-left: 14px;
        }
      }
    }
    .lunbo {
      background: #ffffff;
      box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
      border-radius: 8px;
      // width: 355px;
      width: 95%;
      height: 120px;
      margin-top: -35px;
      margin-left: 10px;
      .imgs {
        display: flex;
        .house {
          margin-top: 20px;
          margin-left: 60px;
          .house_left {
            margin-top: 3px;
          }
          .house_right {
            :nth-child(1) {
                font-weight: 400;
              color: #666666;
              line-height: 21px;
              font-size: 18px;
            }
          }
        }
        .house2 {
          margin-top: 20px;
          margin-left: 90px;
          .house_left {
            margin-top: 3px;
          }
          .house_right {
            :nth-child(1) {
                font-weight: 400;
              color: #666666;
              line-height: 21px;
              font-size: 18px;
            }
          }
        }
      }
    }
    .content{
        margin-top: 20px;
        background-color: #FFFFFF;
        >div{
            display: flex;
            height: 40px;
            line-height: 40px;
            justify-content: space-between;
            margin: 5px 20px;
            border-bottom: 1px solid #EAEBF1;
            font-size: 17px;
        }
        :nth-child(3){
            border-bottom: 0px
        }
    }
}
.btn{
    background-color: #D1D1D1;
    // position: absolute;
    position: fixed;
    width: 85%;
    bottom:100px;
    left: 30px;
    // right: 30px;
    border-radius: 23px;
}
  }
  </style>
