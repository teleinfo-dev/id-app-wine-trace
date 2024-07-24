import { defineComponent, ref, onMounted } from "vue";
import { apiSubsidyData } from "@/api/common";
import Draw from "./draw";

export default defineComponent({
  components: {
    Draw,
  },
  setup() {
    // 定义一个interface
    interface IChartData {
      category: string[];
      lineData: number[];
      barData: number[];
      rateData: string[];
    }
    const cdata = ref<IChartData>();
    const setData = async () => {
      // todo 接口返回数据
      // 此接口为mock数据，实际使用时请替换为真实接口，其他图表可参照此接口的写法
      apiSubsidyData({})
        .then((data: any) => {
          // eslint-disable-next-line no-plusplus
          for (let i = 0; i < data?.barData.length - 1; i++) {
            const rate = data.barData[i] / data.lineData[i];
            data.rateData.push(rate.toFixed(2));
          }
          cdata.value = data;
        })
        .catch();

      console.log(90900000, cdata.value);
    };

    // 生命周期
    onMounted(() => {
      setData();
    });

    return () => {
      return (
        <div>
          <Draw cdata={cdata.value} />
        </div>
      );
    };
  },
});
