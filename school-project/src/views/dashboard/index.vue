<template>
  <div class="app">
    <div class="all">
      <div class="charts" >
        <div>
          <div class="title">用户收支统计</div>
          <div style="margin-left: 10%">
            <el-select v-model="value" placeholder="请选择用户" @change="changeHandler">
              <el-option
                  v-for="item in options"
                  :key="item"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </div>
          <div class="top">
            <div id="myChart1" style="width: 800px; height: 500px"></div>
            <div id="myChart2" style="width: 800px; height: 500px"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {post,get,Del_arry,Del} from '../../store/axios'
// import 'echarts-gl';
export default {
  data() {
    return {
      value:"test",
      options:[],
      limitQuery:{
        page:1,
        pagesize:100
      },
      data1:[],
      data2:[],
      data3:[],
      data4:[],
    };
  },
  mounted() {
    this.query();
  },
  methods: {
    changeHandler(){
      get("/expend/countDay?userName="+this.value).then((res)=>{
        this.data1 = res.data.data.xData
        this.data2 = res.data.data.yData
        get("/income/countDay?userName="+this.value).then((result)=>{
          this.data3 = result.data.data.xData
          this.data4 = result.data.data.yData
          this.drawLine1();
          this.drawLine2();
        })
      })
    },
    query(){
      post('/income/findAll',this.limitQuery).then((res)=>{
        var result = []
        res.data.data.forEach((item)=>{
          result.push(item.userName)
        })
        this.options = Array.from(new Set(result))
        get("/expend/countDay?userName="+this.value).then((res)=>{
          this.data1 = res.data.data.xData
          this.data2 = res.data.data.yData
          get("/income/countDay?userName="+this.value).then((result)=>{
            this.data3 = result.data.data.xData
            this.data4 = result.data.data.yData
            this.drawLine1();
            this.drawLine2();
          })
        })
      })
    },
    drawLine1() {
      var chartDom = document.getElementById("myChart1");
      var myChart = echarts.init(chartDom);
      var option;
      option = {
        xAxis: {
          type: 'category',
          data: this.data1
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.data2,
            type: 'line',
            smooth: true
          }
        ]
      };
      option && myChart.setOption(option);
    },
    drawLine2() {
      var chartDom = document.getElementById("myChart2");
      var myChart = echarts.init(chartDom);
      var option;
      option = {
        xAxis: {
          type: 'category',
          data: this.data3
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.data4,
            type: 'line',
            smooth: true
          }
        ]
      };
      option && myChart.setOption(option);
    },
  },
};
</script>
<style lang="scss" scoped>
.app {
  padding: 20px;
  .all {
    width: 100%;
    height: 90vh;
    overflow: scroll;
    border-radius: 20px;
    // background-color: blue;
    box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.3), 0px 0px 50px #ddd inset;
    .tops:hover {
      cursor: pointer;
    }
    .tops {
      padding-top: 15px;
      display: flex;
      justify-content: space-between;
      > div {
        margin: 10px;
        display: flex;
        flex: 1;
        height: 150px;
        border-radius: 15px;
        justify-content: space-around;
        color: rgb(90, 86, 86);
        .tops_top {
          font-size: 23px;
          padding: 15px;
          margin-top: 20px;
        }
        .tops_bottom {
          margin-left: -100px;
          margin-top: 80px;
          font-size: 20px;
        }
      }
      >:nth-child(1){
       background-image: url('../../assets/404_images/101.png');
       background-repeat: no-repeat;
       background-position: center;
       background-size:100% 100%;
      }
      >:nth-child(2){
       background-image: url('../../assets/404_images/102.png');
       background-repeat: no-repeat;
       background-position: center;
       background-size:100% 100%;
      }
      >:nth-child(3){
       background-image: url('../../assets/404_images/103.png');
       background-repeat: no-repeat;
       background-position: center;
       background-size:100% 100%;
      }
      >:nth-child(4){
       background-image: url('../../assets/404_images/104.png');
       background-repeat: no-repeat;
       background-position: center;
       background-size:100% 100%;
      }
    }
    // .charts {
    //   display: flex;
    // }
  }
}
.top {
  display: flex;
  justify-content: center;
  align-content: center;

  margin-top: 60px;
}
.bottom {
  display: flex;
  justify-content: space-around;
  align-content: center;
  /* margin-top: 10px; */
}
.title {
  padding-top: 20px;
  display: flex;
  font-size: 29px;
  color: red;
  justify-content: center;
}
</style>