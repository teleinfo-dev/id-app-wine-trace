import { defineComponent, watch, shallowReactive } from "vue";

// 声明类型
const PropsType = {
  cdata: {
    type: Object,
    require: true,
  },
} as const;

// 定义主体
export default defineComponent({
  props: PropsType,
  setup(props) {
    // 配置项
    let options = shallowReactive({
      tooltip: null,
      yAxis: null,
      xAxis: null,
      legend: null,
      series: null,
      grid: null,
    });

    watch(
      () => props.cdata,
      (val: any) => {
        options = {
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          legend: {},
          grid: {
            left: "3%",
            right: "1%",
            bottom: "0%",
            top: "10%",
            containLabel: true,
          },
          xAxis: {
            type: "value",
            axisLine: {
              show: false, // 隐藏X轴线
            },
            axisLabel: {
              show: false, // 隐藏X轴标签
            },
            axisTick: {
              show: false, // 隐藏X轴刻度
            },
            show: false, // 隐藏X轴
          },
          yAxis: {
            type: "category",
            data: val.xdata,
            axisLine: {
              show: false, // 隐藏Y轴线
            },
            axisLabel: {
              show: false, // 隐藏Y轴标签
            },
            axisTick: {
              show: false, // 隐藏Y轴刻度
            },
            show: false, // 隐藏Y轴
          },
          series: [
            {
              name: "2011",
              type: "bar",
              data: val.ydata,
              showBackground: true,
              backgroundStyle: {
                color: "rgba(256, 256, 256, 0.1)",
              },
              barWidth: 14,
              // barCategoryGap: 0.9,
              // barGap: 0.5,
              itemStyle: {
                normal: {
                  // 设置渐变色
                  color: {
                    type: "linearGradient",
                    x: 0,
                    y: 0,
                    x2: 1,
                    y2: 0,
                    colorStops: [
                      {
                        offset: 0,
                        color: "#00315D", // 开始颜色
                      },
                      {
                        offset: 1,
                        color: "#0085FF", // 结束颜色
                      },
                    ],
                    global: false, // 渐变色是否应用到全局
                  },
                },
              },
              label: {
                normal: {
                  show: true,
                  position: "left",
                  align: "left",
                  padding: [-40, 0, 0, 8],
                  margin: [-30, 0, 0, 0],
                  color: "#fff", // 标签文本颜色
                  fontSize: 16,
                  fontWeight: "bold",
                  rich: {
                    // 使用 Base64 编码的图片
                    // icon: {
                    //   icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA...',
                    //   width: 20,
                    //   height: 20,
                    //   padding: [0, 2],
                    // },
                    yValue: {
                      fontSize: 14,
                      fontWeight: "bold",
                      color: "#333",
                    },
                  },
                  formatter: "{b}",
                },
              },
            },
          ],
        };
      },
      {
        immediate: true,
        deep: true,
      }
    );

    return () => {
      const height = "240px";
      const width = "400px";

      return (
        <div>
          <echart options={options} height={height} width={width} />
        </div>
      );
    };
  },
});
