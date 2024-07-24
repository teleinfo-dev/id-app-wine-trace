import { successResponseWrap, failResponseWrap } from "./response";

export default [
  {
    url: "/server/web/subsidy/getCompanySubsidy",
    method: "get",
    response: () => {
      return successResponseWrap({
        category: [
          "市区",
          "万州",
          "江北",
          "南岸",
          "北碚",
          "綦南",
          "长寿",
          "永川",
          "璧山",
          "江津",
        ],
        lineData: [
          18092, 20728, 24045, 28348, 32808, 36097, 39867, 44715, 48444, 50415,
        ],
        barData: [
          4600, 5000, 5500, 6500, 7500, 8500, 9900, 12500, 14000, 21500,
        ],
        rateData: [],
      });
    },
  },
  {
    url: "/server/web/member/getCompanyMember",
    method: "get",
    response: () => {
      return successResponseWrap([
        {
          username: "张三",
          memberId: "1",
        },
        {
          username: "李斯",
          memberId: "2",
        },
        {
          username: "王武",
          memberId: "3",
        },
        {
          username: "赵柳",
          memberId: "4",
        },
      ]);
    },
  },
  {
    url: "/server/web/product/appConfig",
    method: "post",
    response: () => {
      return successResponseWrap([]);
    },
  },
  {
    url: "/server/web/product/selectConfig",
    method: "get",
    response: () => {
      return successResponseWrap([
        {
          username: "张三",
          memberId: "1",
        },
        {
          username: "李斯",
          memberId: "2",
        },
      ]);
    },
  },
];
