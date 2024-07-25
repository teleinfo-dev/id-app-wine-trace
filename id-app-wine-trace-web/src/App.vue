<template>
  <div class="app">
    <router-view />
    <div class="water-mark"></div>
  </div>
</template>

<style lang="scss" scoped>
.app {
  width: 100vw;
  height: 100vh;
  background-color: #00040e;
  overflow: hidden;
}

.water-mark {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: 385px 185px;
  display: block;
  z-index: 99999;
  position: fixed;
  background-image: url(@/assets/bg/watermark.png);
  background-repeat: repeat;
  pointer-events: none;
}
</style>
