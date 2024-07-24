<template>
  <div
    class="center_bottom-box chart-box"
    :style="{ width: data.values.length > 5 ? '1800px' : '900px' }"
  >
    <div class="header-box">
      <div class="header-title">
        <img src="@/assets/bg/icon_left.png" alt="" class="images" />
        <span class="title">{{ CodeEnum[currentIndex] }}</span>
        <img src="@/assets/bg/icon_right.png" alt="" class="images" />
      </div>
      <div class="bid">{{ `标识码：${data?.handle}` }}</div>
    </div>
    <div
      class="content_area"
      :class="data.values.length <= 5 ? 'single_columns' : 'more_columns'"
    >
      <div
        class="header-content spaceBetween"
        v-for="(item, index) in data.values"
        :key="index"
        :style="{ width: data.values.length <= 5 ? '100%' : '880px' }"
      >
        <span>{{ item.type }}</span>
        <span>{{
          item.value.replace(/['"]|[\[\]{}()]/g, "") || "暂无数据"
        }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { defineComponent, reactive, onMounted, defineProps } from "vue";

const props = defineProps({
  data: {
    type: Object,
  },
  currentIndex: {
    type: Number,
    default: -1,
  },
});

const CodeEnum = {
  14: "供应商",
  13: "原料批次",
  12: "制曲",
  11: "酿造",
  10: "酒体设计",
  9: "包装过程管控",
  8: "瓶酒",
  7: "箱酒",
  6: "生产入库",
  5: "销售出库",
  4: "经销商收货",
  3: "经销商发货",
  2: "门店签收",
  1: "消费者扫码",
};

onMounted(() => {
  console.log(props.data.values.length, "props");
});
</script>

<style lang="scss" scoped>
$box-height: 304px;
.center_bottom-box {
  height: $box-height;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  .text {
    color: #c3cbde;
  }
  .twoW .dv-scr-board {
    width: 100%;
    height: 300px;
  }
}
.header-box {
  width: 100%;
  height: 60px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  background: rgba(34, 91, 155, 0.9);

  color: #83dbff;
  .header-title {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .images {
    width: 21px;
    height: 18px;
  }
  .title {
    font-family: PingFang SC;
    font-weight: 600;
    font-size: 20px;
    margin: 0 16px;
  }
  .bid {
    font-weight: 300;
    font-size: 14px;
    color: #15b2f3;
  }
}
.content_area {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.more_columns {
  div:nth-child(4n + 3),
  div:nth-child(4n + 4) {
    background: rgba(34, 91, 155, 0.45);
  }
}

.single_columns {
  div:nth-child(2n) {
    background: rgba(34, 91, 155, 0.45);
  }
}
.header-content {
  width: 100%;
  height: 50px;
  word-break: break-all;
  font-family: PingFang SC;
  font-weight: 300;
  font-size: 16px;

  // .text_left {
  //   display: flex;
  //   flex: 1;
  //   height: 100%;
  //   position: relative;
  & :nth-child(1) {
    color: #25bfff;
    margin-left: 58px;
  }
  & :nth-child(2) {
    width: calc(100% - 250px);
    // position: absolute;
    margin-right: 58px;
    text-align: right;
  }
  // }

  .text_right {
    width: 880px;
    height: 100%;
    position: relative;
    & :nth-child(1) {
      margin-left: 58px;
    }
    & :nth-child(2) {
      position: absolute;
      margin-left: 604px;
    }
  }
}

.spaceBetween {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
</style>
