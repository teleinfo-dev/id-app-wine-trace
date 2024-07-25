<template>
  <div class="header-box">
    <div class="header-title jc-center ai-center d-flex"></div>
    <div class="header-right jc-end ai-center d-flex">
      <div class="header-input">
        <input
          type="text"
          class="search-input"
          v-model="handle"
          placeholder="请输入酒瓶标识码"
        />
        <img
          src="@/assets/bg/search.png"
          alt=""
          class="images"
          @click="searchInput()"
        />
      </div>
      <span class="header-time">
        {{ timeInfo.dateWeek }}
      </span>
      <div class="header-month">
        <div>{{ `${timeInfo.dateMonth}月` }}</div>
        <span class="day">{{ timeInfo.dateDay }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted, onUnmounted, defineEmits } from "vue";
import { formatTime } from "@/utils/index";

const handle = ref("");

const emit = defineEmits(["updateValue"]);
// * 时间内容
const timeInfo = reactive({
  setInterval: null,
  dateDay: "",
  dateYear: "",
  dateMonth: "",
  dateWeek: "",
});

// todo 处理时间监听
const handleTime = () => {
  timeInfo.setInterval = setInterval(() => {
    const date = new Date();
    timeInfo.dateMonth = formatTime(date, "M");
    timeInfo.dateDay = formatTime(date, "dd");
    timeInfo.dateWeek = formatTime(date, "HH: mm: ss");
  }, 1000);
};
const searchInput = () => {
  emit("updateValue", handle.value);
};

onMounted(() => {
  handleTime();
});

onUnmounted(() => {
  clearInterval(timeInfo.setInterval);
});

defineExpose({
  handle,
});
</script>

<style scoped lang="scss">
.header-box {
  display: flex;
  justify-content: right;
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
    min-width: 480px;
    font-family: Alimama ShuHeiTi;
    font-weight: bold;
    .header-input {
      width: 302px;
      height: 46px;
      background: rgba(26, 84, 192, 0.5);
      border-radius: 8px;
      border: 1px solid #0fc4ff;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      padding-right: 20px;
      .search-input {
        width: 250px;
        height: 46px;
        font-size: 20px;
        border: none;
        padding-left: 10px;
        background: none;
        color: #fff;
      }
      .search-input::placeholder {
        color: #4fb7ff;
        font-size: 16px;
      }
      .images {
        width: 29px;
        height: 29px;
        cursor: pointer;
      }
    }
    .header-time {
      width: 118px;
      font-size: 24px;
      color: #ffffff;
      margin-left: 15px;
    }
    .header-month {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      width: 38px;
      height: 38px;
      border-radius: 50%;
      border: 1px solid #fff;
      margin-left: 13px;
      font-size: 12px;
      color: #ffffff;
      line-height: normal;
      .day {
        font-size: 14px;
        color: #78ceff;
      }
    }
  }
}
</style>
