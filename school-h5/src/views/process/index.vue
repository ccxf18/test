<template>
  <div class="list">
    <tar-bar :title="title" />
    <!-- <van-button type="primary" @click="showPopu" style="margin-top:100px">主要按钮</van-button> -->
    <div class="body">
      <div class="top"></div>
      <div class="tab">
        <van-notice-bar
              style="background-color: #6ea9f1;color:white"
              left-icon="volume-o"
              text="在代码阅读过程中人们说脏话的频率是衡量代码质量的唯一标准。"
            />
      </div>
          <van-checkbox-group style="height:690px;overflow:scroll"  v-model="result">
            <van-cell-group>
              <van-cell
                v-for="(item) in list"
                clickable
                :key="item.money"
              >
                <template #right-icon>
                  <div style="padding:10px;border-radius: 10px;box-shadow:2px 2px 10px #b4b4b4; justify-content: space-between; width: 100%;height: 100px;">
                    <!-- <div style="margin-top: 28px;"> -->
                      <!-- :disabled="item.status == '已缴费'" -->
                      <!-- <van-checkbox :disabled="item.status == '已缴费'" :name="item.money" ref="checkboxes" /> -->
                    <!-- </div> -->
                    <div style="line-height: 40px;display:flex;justify-content: space-between;">
                      <div>姓名：{{item.author}}</div>
                      <div style="display:flex">
                        <div style="display:flex">
                        <img v-if="item.collectStatus"  @click="havNocollection(item)" style="width:20px;height:20px;margin-top:11px;margin-left:10px" src="../../assets/images/shoucnag2.png" alt="">
                        <img v-else @click="havcollection(item)" style="width:20px;height:20px;margin-top:11px;margin-left:15px" src="../../assets/images/shoucang.png" alt="">
                         <div style="margin-left:10px;line-height: 43px;"> {{item.collect}}</div>
                        </div>
                        <div style="display:flex">
                            <img v-if="item.likeStatus"  @click="havNoLike(item)" style="width:20px;height:20px;margin-top:11px;margin-left:10px" src="../../assets/images/like2.png" alt="">
                            <img v-else @click="havLike(item)" style="width:20px;height:20px;margin-top:11px;margin-left:15px" src="../../assets/images/like1.png" alt="">
                            <div style="margin-left:10px;line-height: 43px;"> {{item.like}}</div>
                        </div>
                      </div>
                    </div>
                    <div style="line-height: 40px;text-align: left;">
                      <div style="width:100%;display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">内容：{{item.content}}</div>
                      <!-- <van-rate v-model="item.star" icon="star" void-icon="star" /> -->
                    </div>
                  </div>
                </template>
              </van-cell>
            </van-cell-group>
          </van-checkbox-group>
        
      <div  class="btns">
        <div class="bottom">
              <div style="display:flex">
                  <div class="first">
                      <input type="text" style="width:300px;background:#e2dddd;height: 40px;" v-model="remark">
                  </div>
                  <div>
                      <img @click="havPublish" style="width:30px;height:30px;margin-top:5px;margin-left:30px" src="../../assets/images/pinglun.png" alt="">
                  </div>
              </div>
          </div>
      </div>
    </div>
  </div>
</template>
  <script>
import tarBar from "../../components/tarBar/index1.vue";
import { dateFormat } from "../../JS/utils";
export default {
  data() {
    return {
      remark:'',
      money:0,
      ids:[],
      results:[],
      result:[],
      name:'',
      show:false,
      immediate:false,
      finished:false,
      active: 0,
      list: [],
      pageIndex:1,
      pageSize:100,
      img1: require("../../assets/images/house1.png"),
      img2: require("../../assets/images/house2.png"),
      img3: require("../../assets/images/house3.png"),
      loading: false,
      title: "社区",
      status:'取消收藏成功',
      status2:'取消点赞成功'
    };
  },
  components: { tarBar },
  created() {
    this.name = this.$route.params.name
    // this.havContract();
    this.havProperty()
  },
  // filters: {
  //   showDate(value) {
  //       let date = new Date(value * 1000);
  //       return this.dateFormat(date,'yyyy-MM-dd hh:mm:ss');
  //     }
  //   },
  methods: { 
         //收藏
         havcollection(item){
            let params = {
              chatId:item.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/collect/confirmCollect", params).then((res) => {
                if(res.code == 0){
                    this.status = res.data
                    this.havProperty()
                    this.$toast({
                        msg: '收藏成功',
                        type: "success",
                    });
                }
            })
        },
         //取消收藏
        havNocollection(item){
            let params = {
              chatId:item.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/collect/cancelCollect", params).then((res) => {
                if(res.code == 0){
                    this.status = res.data
                    this.havProperty()
                    this.$toast({
                        msg: '取消收藏成功',
                        type: "warning",
                    });
                }
            })
        },
        //点赞
        havLike(item){
            let params = {
              chatId:item.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/like/confirmLike", params).then((res) => {
                if(res.code == 0){
                    this.status2 = res.data
                    this.havProperty()
                    this.$toast({
                        msg: '点赞成功',
                        type: "success",
                    });
                }
            })
        },
        // 取消点赞
        havNoLike(item){
            let params = {
              chatId:item.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/like/cancelLike", params).then((res) => {
                if(res.code == 0){
                    this.status2 = res.data
                    this.havProperty()
                    this.$toast({
                        msg: '取消点赞成功',
                        type: "warning",
                    });
                }
            })
        },
    //新增评论
    havPublish(){
      let params = {
        userName:sessionStorage.getItem('username'),
        content:this.remark
      }
      this.$http.post("/chat/insert",params).then((res) => {
        if (res.code == 0) {
          this.remark = ''
          this.havProperty()
          this.$toast({
            msg: '评论成功',
            type: "success",
          });
        }
      });
    },
    //获取支付账单
    havProperty(){
      // this.name
      let params = {
        page:this.pageIndex,
        pagesize:this.pageSize,
        userName:sessionStorage.getItem('username'),
      }
      this.$http.post("/chat/findAll",params).then((res) => {
        if (res.code == 0) {
          this.list = res.data
        }
      });
    },
  },
};

</script>

  <style scoped lang="less">
.list {
  width: 100%;
  height: 100vh;
  background-color: white;
  .body {
    .btns{
      position: fixed;
      bottom: 60px;
      opacity: 0.5;
      width: 100%;
      left:3%;
      text-align: left;
      // background: linear-gradient(135deg, #6ea9f1 0%, #a7f0c3 100%);
    }
    .top {
      position: absolute;
      height: 80px;
      width: 100%;
      background-color: #6ea9f1;
    }
    .tab {
      margin-top: 45px;
      width: 100%;
      background-color: #6ea9f1;
      #tabs /deep/ .van-tabs__nav--line {
        background-color: #6ea9f1;
        color: white;
        > .van-tabs__line {
          background-color: white !important;
        }
      }
    }
    .all_list {
      padding: 10px;
      height: 700px;
      overflow: scroll;
    }
    .lunbo {
      position: relative;
      z-index: 100;
      background: white;
      box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
      border-radius: 8px;
      // width: 355px;
      width: 95%;
      height: 134px;
      margin-top: 10px;
      margin-left: 10px;
      text-align: left;
      .first {
        display: flex;
        justify-content: space-between;
        margin-left: 15px;
        padding-top: 15px;
        font-weight: 500;
        > :nth-child(1) {
          display: flex;
        }
       .tag1 {
          background: #ff9d00;
          border-radius: 100px 0px 0px 100px;
          width: 60px;
          text-align: center;
          font-size: 12px;
          color: white;
          line-height: 28px;
        }
        .tag2 {
          color: white;
          background: #B8B8B8;
          border-radius: 100px 0px 0px 100px;
          width: 60px;
          text-align: center;
          font-size: 12px;
          line-height: 28px;
        }
        .tag3 {
          color: white;
          background: #56BA3E;
          border-radius: 100px 0px 0px 100px;
          width: 60px;
          text-align: center;
          font-size: 12px;
          line-height: 28px;
        }
      }
      .two {
        height: 60px;
        overflow: scroll;
        padding-top: 20px;
        margin-left: 15px;
        font-size: 14px;
        font-weight: 400;
        color: #666666;
      }
      .three {
        padding-top: 15px;
        margin-left: 15px;
        font-size: 14px;
        font-weight: 400;
        color: #666666;
      }
    }
  }

}
</style>
