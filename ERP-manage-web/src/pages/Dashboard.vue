<template>
  <v-container fluid grid-list-md>
    <v-layout row wrap>
      <v-flex xs10 md6>
        <v-card>
          <v-card-text class="px2">
            <div ref="sale" style="width: 100%;height:350px"></div>
          </v-card-text>
        </v-card>
      </v-flex>

      <v-flex xs10 md6>
        <v-card >
          <v-card-text class="px2">
            <div ref="pie" style="width: 100%;height:350px"></div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>

      <v-layout row wrap>

        <v-flex xs10 md6>
          <v-card>
          <div ref="lineChart" style="width: 100%; height: 350px; background-color: #ffffff; padding: 20px; border-radius: 20px;"></div>
          </v-card>
        </v-flex>

      <v-flex xs10 md6>
        <v-card>
          <div ref="myChart" style="width: 100%; height: 350px; background-color: #ffffff; padding: 20px; border-radius: 20px;"></div>
        </v-card>
      </v-flex>

      </v-layout>

  </v-container>
</template>

<script>
  // 引入 ECharts 主模块

  import * as echarts from 'echarts'

  require('echarts/lib/chart/bar');
  require('echarts/lib/chart/pie');

  export default {
    name: "dashboard",
    data(){
      return {
      }
    },
    mounted(){
      this.$nextTick(() => {
        var sale = echarts.init(this.$refs.sale);
        // 指定图表的配置项和数据
        var option = {
          title: {
            text: '销售统计'
          },
          tooltip: {},
          legend: {
            data:['销量']
          },
          xAxis: {
            data: ["TCL","iphone","华为","vivo","三星","小米"]
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20],
            itemStyle: {
              normal: {
                color: function (params) {
                  var colorList = ['#4FC3F7', '#00C853', '#F57F17','#C0FF3E','#4876FF','#7D26CD']
                  return colorList[params.dataIndex]
                }
              }
            }
          }],

        };
        // 使用刚指定的配置项和数据显示图表。
        sale.setOption(option);

        const pie = echarts.init(this.$refs.pie);
        pie.setOption({
          roseType: 'angle',
          title: {
            text: '访问来源'
          },
          series : [
            {
              name: '访问来源',
              type: 'pie',
              radius: '55%',
              data:[
                {value:235, name:'视频广告'},
                {value:274, name:'联盟广告'},
                {value:310, name:'邮件营销'},
                {value:335, name:'直接访问'},
                {value:400, name:'搜索引擎'}
              ],
              itemStyle: {
                emphasis: {
                  // 阴影的大小
                  shadowBlur: 200,
                  // 阴影水平方向上的偏移
                  shadowOffsetX: 0,
                  // 阴影垂直方向上的偏移
                  shadowOffsetY: 0,
                  // 阴影颜色
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                normal: {
                  color: function (params) {
                    var colorList = ['#4FC3F7', '#00C853', '#F57F17','#4876FF','#7D26CD']
                    return colorList[params.dataIndex]
                  }
                }
              }
            }
          ],

        });
        this.myChart = echarts.init(this.$refs.myChart)
        this.myChart.setOption({
          title: {
            text: '32469',
            subtext: '评价数',
            left: 'center',
            top: '43%',
            subtextStyle: {
              fontSize: 18
            }
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            icon: 'circle',
            top: '0',
            left: 'right'
          },
          series: [
            {
              name: '咨询数量',
              type: 'pie',
              radius: ['40%', '55%'],
              label: {
                show: true,
                padding: [0, -60],
                overflow: 'none',
                fontSize: '15',
                fontWeight: 'bold',
                formatter: '{d}%\n\n{c}'
              },
              labelLine: {
                show: true,
                length: 15,
                length2: 60
              },
              itemStyle: {
                normal: {
                  color: function (params) {
                    var colorList = ['#4FC3F7', '#00C853', '#F57F17']
                    return colorList[params.dataIndex]
                  }
                }
              },
              data: [
                { name: '好评', value: 1048 },
                { name: '一般', value: 735 },
                { name: '差评', value: 180 }
              ]
            }
          ]
        });
        this.lineChart = echarts.init(this.$refs.lineChart)
        this.lineChart.setOption({
          title: {
            text: '评价数据分析'
          },
          // 提示框
          tooltip: {
            trigger: 'axis'
          },
          // 图例
          legend: {
            icon: 'circle',
            left: 'center',
            top: 0,
            data: ['好评', '一般', '差评']
          },
          grid: {
            left: '3%',
            right: '3%',
            bottom: '3%',
            containLabel: true
          },
          // 工具栏
          toolbox: {
            feature: {
              saveAsImage: {
                type: 'png'
              },
              magicType: {
                type: ['line', 'bar', 'stack']
              }
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '好评',
              type: 'line',
              // smooth: true, // 平滑曲线显示
              data: [120, 132, 101, 134, 190, 230, 210, 201, 234, 290, 230, 210]
            },
            {
              name: '一般',
              type: 'line',
              // smooth: true,
              data: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110]
            },
            {
              name: '差评',
              type: 'line',
              stack: '总量',
              // smooth: true,
              data: [10, 22, 21, 14, 19, 13, 20, 11, 34, 29, 20, 10]
            }
          ]

        })
      })

    },


  }
</script>

<style scoped>

</style>
