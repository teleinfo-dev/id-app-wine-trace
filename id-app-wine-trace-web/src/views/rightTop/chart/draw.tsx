import { defineComponent, ref, watch, shallowReactive } from "vue";

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
    let options = shallowReactive({
      color: null,
      tooltip: null,
      toolbox: null,
      calculable: null,
      legend: null,
      series: null,
    });

    watch(
      () => props.cdata,
      (val: any) => {
        console.log(val);
        options = {
          color: [
            "#37a2da",
            "#32c5e9",
            "#9fe6b8",
            "#ffdb5c",
            "#ff9f7f",
            "#fb7293",
            "#e7bcf3",
            "#8378ea",
          ],
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)",
          },
          toolbox: {
            show: true,
          },
          calculable: true,

          legend: {
            orient: "vertical",
            icon: "rect",
            right: 20, // 调节图例位置
            top: 10, // 调节图例位置
            itemGap: 20, // 调节图例间距
            itemWidth: 10, // 调节图例宽度
            itemHeight: 10, // 调节图例高度
            data: val.xData,
            formatter(name) {
              // 定制图例内容...
              return `${name}`;
            },
            textStyle: {
              // 调节图例文字样式
              color: "#fff",
              fontSize: 12,
            },
          },
          series: [
            {
              name: "通过率统计",
              type: "pie",
              radius: [40, 100], // 调节饼图大小
              roseType: "area",
              center: ["40%", "50%"], // 调节饼图位置
              itemStyle: {
                borderRadius: 2, // 调节饼图圆角
              },
              label: {
                show: false,
                color: "#fff",
                normal: {
                  show: true,
                  position: "center", // 标签的位置在饼图的中心
                  formatter: "北京算力", // 格式化标签内容为系列名称
                  fontSize: "12", // 文字的字体大小
                  fontWeight: "bold", // 文字的字体粗细
                  color: "#fff", // 文字的颜色
                },
              },
              emphasis: {
                label: {
                  show: false,
                },
              },
              data: val.seriesData,
            },
          ],
        };
        // 手动触发更新
        if (chartRef.value) {
          // 通过初始化参数打入数据
          chartRef.value.initChart(options);
        }
      },
      {
        immediate: true,
        deep: true,
      }
    );

    return () => {
      // 设置实际图表尺寸
      const height = "209px";
      const width = "400px";

      return (
        <div>
          <echart
            ref={chartRef}
            options={options}
            height={height}
            width={width}
          />
        </div>
      );
    };
  },
});
