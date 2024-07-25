<template>
  <div class="header-box">
    <div class="header-title jc-center ai-center d-flex">
      <span class="title-text">{{ title }}</span>
    </div>
    <div class="header-right jc-center ai-center d-flex">
      <span>
        {{ timeInfo.dateYear }} {{ timeInfo.dateWeek }}
        {{ timeInfo.dateDay }}
      </span>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, defineProps, reactive, onMounted, onUnmounted } from "vue";
import { formatTime } from "@/utils/index";
import { WEEK } from "@/constant/index";

// 传入title
defineProps<{ title: string }>();
// * 时间内容
const timeInfo = reactive({
  setInterval: null,
  dateDay: "",
  dateYear: "",
  dateWeek: "",
});
// 处理时间监听
const handleTime = () => {
  timeInfo.setInterval = setInterval(() => {
    const date = new Date();
    timeInfo.dateDay = formatTime(date, "HH: mm: ss");
    timeInfo.dateYear = formatTime(date, "yyyy-MM-dd");
    timeInfo.dateWeek = WEEK[date.getDay()];
  }, 1000);
};

onMounted(() => {
  handleTime();
});

onUnmounted(() => {
  clearInterval(timeInfo.setInterval);
});
</script>

<style scoped lang="scss">
.header-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: transparent;
  .header-title {
    flex: 1;
    height: 100%;
    font-size: 36px;
    font-weight: 600;
    .title-text {
      margin-left: 240px;
    }
  }
  .header-right {
    height: 100%;
    min-width: 200px;
    font-size: 12px;
  }
}
</style>
