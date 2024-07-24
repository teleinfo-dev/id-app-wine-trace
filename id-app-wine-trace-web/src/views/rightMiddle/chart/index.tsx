import { defineComponent, reactive } from "vue";
import Draw from "./draw";

export default defineComponent({
  components: {
    Draw,
  },
  setup() {
    const cdata = reactive({
      xdata: ["Brazil", "Indonesia", "USA", "India"],
      ydata: [18203, 23489, 29034, 104970],
    });
    return () => {
      return (
        <div>
          <draw cdata={cdata} />
        </div>
      );
    };
  },
});
