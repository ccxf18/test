<template>
    <div class="list">
      <tar-bar :title="title" />
      <div class="body">
        <div>
           <span style="color:#6ea9f1">|</span> 收入统计(天)
        </div>
        <div style="margin-top:-20px">
          <div id="main" style="width:100%;height:300px"></div>
        </div>
        <div style=" display:flex;justify-content: space-between;">
          <div>
            <div>
             <span style="color:#6ea9f1">|</span> 收入统计月)
            </div>
            <div >
              <div id="main1" style="width:200px;height:300px"></div>
            </div>
          </div>
          <div>
            <div>
              <span style="color:#6ea9f1">|</span> 支出统计(月)
            </div>
            <div >
              <div id="main3" style="width:200px;height:300px"></div>
            </div>
          </div>
        </div> 
        <div style="margin-top:-30px">
           <span style="color:#6ea9f1">|</span> 支出统计(天)
        </div>
        <div style="margin-top:-30px">
          <div id="main2" style="width:100%;height:300px"></div>
        </div>
      </div>
    </div>
  </template>
    <script>
  import tarBar from "../../components/tarBar/index1.vue";
  import { dateFormat } from "../../JS/utils";
  import * as echarts from 'echarts';
  export default {
    data() {
      return {
        xdata:[],
        ydata:[],
        xdata1:[],
        ydata1:[],
        money:0,
        ids:[],
        results:[],
        result:[],
        name:'',
        show:false,
        immediate:false,
        finished:false,
        status:null,
        active: 0,
        list: [],
        list1: [],
        pageIndex:1,
        pageSize:5,
        img1: require("../../assets/images/house1.png"),
        img2: require("../../assets/images/house2.png"),
        img3: require("../../assets/images/house3.png"),
        loading: false,
        title: "统计分析",
      };
    },
    components: { tarBar },
    created() {
      this.havProperty()
      this.havProperty1()
      this.havProperty2()
      this.havProperty3()
    },
    methods: {  
      // 收入统计1
      initChart1(){
          // 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('main'));
          var option;
          // 绘制图表（折线图）
          option = {
            xAxis: {
              type: 'category',
              data: this.xdata,
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: this.ydata,
                type: 'line',
                smooth: true
              }
            ]
          };
          option && myChart.setOption(option);
      }, 
         // 支出统计1
         initChart3(){
          // 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('main2'));
          var option;
          // 绘制图表（折线图）
          option = {
            xAxis: {
              type: 'category',
              data: this.xdata1,
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: this.ydata1,
                type: 'line',
                // smooth: true
              }
            ]
          };
          option && myChart.setOption(option);
      }, 
      //收入统计2
      initChart2(){
        var chartDom = document.getElementById('main1');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '15%',
            left: 'center'
          },
          series: [
            {
              name: '收入统计',
              type: 'pie',
              radius: ['30%', '40%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.list
            }
          ]
        };

        option && myChart.setOption(option);
    
      }, 
      // 支出统计
      initChart4(){
        var chartDom = document.getElementById('main3');
        var myChart = echarts.init(chartDom);
        var option;
        option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '15%',
            left: 'center'
          },
          series: [
            {
              name: '支出统计',
              type: 'pie',
              radius: '40%',
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.list1
            }
          ]
        };

        option && myChart.setOption(option);
      },
      goPay(){
        this.show = true
        let a = 0
        this.results.forEach((item)=>{
           a+=Number(item.money) 
        })
        this.money = a
        this.ids = this.results.map((item)=>{
          return item.id
        })
      },
      //收入统计（天）
      havProperty(){
        let params = {
          userName:sessionStorage.getItem('username')
        }
        this.$http.get("/income/countDay",{params}).then((res) => {
          if (res.code == 0) {
            this.xdata = res.data.xData
            this.ydata = res.data.yData
            this.initChart1()
          }
        });
      },
      //收入统计（月）
      havProperty1(){
        let params = {
          userName:sessionStorage.getItem('username')
        }
        this.$http.get("/income/countMonth",{params}).then((res) => {
          if (res.code == 0) {
            this.list = res.data
            // this.ydata = res.data.yData
            this.initChart2()
          }
        });
      },
        //支出统计（月）
        havProperty3(){
        let params = {
          userName:sessionStorage.getItem('username')
        }
        this.$http.get("/expend/countMonth",{params}).then((res) => {
          if (res.code == 0) {
            this.list1 = res.data
            this.initChart4()
          }
        });
      },
       //支出统计（天）
       havProperty2(){
        let params = {
          userName:sessionStorage.getItem('username')
        }
        this.$http.get("/expend/countDay",{params}).then((res) => {
          if (res.code == 0) {
            this.xdata1 = res.data.xData
            this.ydata1 = res.data.yData
            this.initChart3()
          }
        });
      },
    },  
  };

  </script>
  
    <style scoped lang="less">
  .list {
    width: 100%;
    .body {
      text-align: left;
      margin-top: 45px;
      height: 90vh;
      overflow: scroll;
      padding: 10px 15px;
    }
  }
  </style>
  