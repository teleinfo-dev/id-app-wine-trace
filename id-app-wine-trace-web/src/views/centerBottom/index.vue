<template>
  <div class="center_bottom-box chart-box">
    <div class="header-box">
      <div>
        <i class="iconfont icon-chart_header_icon"></i>
      </div>
      <span class="title">补贴发放情况</span>
    </div>
    <div class="d-flex mt-1 jc-center">
      <dv-scroll-board class="dv-scr-board" :config="config" />
    </div>
  </div>
</template>

<script>
import { defineComponent, reactive } from "vue";

export default defineComponent({
  setup() {
    const config = reactive({
      header: ["组件", "分支", "覆盖率"],
      data: [
        ["组件1", "dev-1", "<span  class='colorGrass'>↑75%</span>"],
        ["组件2", "dev-2", "<span  class='colorRed'>↓33%</span>"],
        ["组件3", "dev-3", "<span  class='colorGrass'>↑100%</span>"],
        ["组件4", "rea-1", "<span  class='colorGrass'>↑94%</span>"],
        ["组件5", "rea-2", "<span  class='colorGrass'>↑95%</span>"],
        ["组件6", "fix-2", "<span  class='colorGrass'>↑63%</span>"],
        ["组件7", "fix-4", "<span  class='colorGrass'>↑84%</span>"],
        ["组件8", "fix-7", "<span  class='colorRed'>↓46%</span>"],
        ["组件9", "dev-2", "<span  class='colorRed'>↓13%</span>"],
        ["组件10", "dev-9", "<span  class='colorGrass'>↑76%</span>"],
      ],
      rowNum: 7, // 表格行数
      headerHeight: 35,
      headerBGC: "#0f132566", // 表头
      oddRowBGC: "#00337F66", // 奇数行
      evenRowBGC: "#00194F66", // 偶数行
      index: true,
      columnWidth: [50],
      align: ["center"],
    });
    return { config };
  },
});
</script>

<style lang="scss" scoped>
$box-height: 360px;
$box-width: 100%;
.center_bottom-box {
  padding: 16px;
  padding-top: 20px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  .text {
    color: #c3cbde;
  }

  .dv-scr-board {
    width: 100%;
    height: 300px;
  }
}
</style>
