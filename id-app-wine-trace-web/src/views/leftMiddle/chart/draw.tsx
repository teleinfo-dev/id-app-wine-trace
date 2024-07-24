import { defineComponent, watch, ref, nextTick } from "vue";
import * as echarts from "echarts";
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
    // 定义 ref
    const chartRef = ref();
    // 配置项
    let options = {};

    // 监听
    watch(
      () => props.cdata,
      (val: any) => {
        options = {
          tooltip: {
            show: true,
            trigger: "item",
            axisPointer: {
              type: "shadow",
              label: {
                show: true,
                backgroundColor: "#7B7DDC",
              },
            },
          },
          legend: {
            show: true,
            top: 10,
          },
          grid: {
            x: "14%",
            width: "94%",
            top: "24%",
            left: "16%",
            right: "14%",
            bottom: "10%",
          },
          xAxis: {
            data: val?.category ?? [],
            axisLine: {
              lineStyle: {
                color: "#B4B4B4",
              },
            },
            axisTick: {
              show: false,
            },
          },
          yAxis: [
            {
              splitLine: {
                show: true,
                lineStyle: {
                  type: "dashed", // 网格线样式，这里设置为虚线
                  color: "rgba(100,100,100,0.9)", // 网格线颜色，这里设置为半透明黑色
                },
              },
              axisLine: {
                show: false, // y轴线
                // lineStyle: {
                //   color: "#B4B4B4"
                // }
              },

              axisLabel: {
                formatter: "{value} ",
              },
              axisTick: {
                show: false, // 隐藏 y 轴刻度线
              },
            },
            {
              splitLine: { show: false },
              axisLine: {
                show: false, // y轴线
                // lineStyle: {
                //   color: "#B4B4B4"
                // }
              },
              axisLabel: {
                formatter: "{value} ",
              },
              axisTick: {
                show: false, // 隐藏 y 轴刻度线
              },
            },
          ],
          series: [
            {
              name: "贯通率",
              type: "line",
              smooth: true,
              showAllSymbol: true,
              symbol: "emptyCircle",
              symbolSize: 8,
              yAxisIndex: 1,
              itemStyle: {
                normal: {
                  color: "#F02FC2",
                },
              },
              data: val?.rateData ?? [],
            },
            {
              name: "已贯通",
              type: "bar",
              barWidth: 10,
              itemStyle: {
                normal: {
                  barBorderRadius: 2,
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: "#956FD4" },
                    { offset: 1, color: "#3EACE5" },
                  ]),
                },
              },
              data: val?.barData ?? [],
            },
            {
              name: "计划贯通",
              type: "bar",
              barGap: "-100%",
              barWidth: 10,
              itemStyle: {
                normal: {
                  barBorderRadius: 2,
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: "rgba(156,107,211,0.8)" },
                    { offset: 0.2, color: "rgba(156,107,211,0.5)" },
                    { offset: 1, color: "rgba(156,107,211,0.2)" },
                  ]),
                },
              },
              z: -12,
              data: val?.lineData ?? [],
            },
          ],
        };
        // 手动触发更新
        nextTick(() => {
          // 此时 DOM 已经更新
          if (chartRef.value) {
            console.log(`chartref==${chartRef.value}`);
            console.log("data==", val);
            // 通过初始化参数打入数据
            chartRef.value.initChart(options);
          }
        });
      },
      {
        immediate: true,
        deep: true,
      }
    );

    return () => {
      const height = "220px";
      const width = "400px";

      return (
        <div>
          <echart ref={chartRef} height={height} width={width} />
        </div>
      );
    };
  },
});
