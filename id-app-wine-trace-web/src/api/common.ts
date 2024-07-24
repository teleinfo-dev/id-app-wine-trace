import $http from "@/utils/http";

// 获取补贴数据
export function apiSubsidyData(params: Record<string, any>) {
  return $http.get("/server/web/subsidy/getCompanySubsidy", { params });
}

//  获取企业下成员列表
export function apiMemberList(params: Record<string, any>) {
  return $http.get("/server/web/member/getCompanyMember", { params });
}

//  获取企业下某个应用下有权限的成员列表
export function apiMemListByProduct(data: Record<string, any>) {
  return $http.post("/server/web/product/app/selectConfig", data);
}

// 给应用授权可查看的成员
export function apiAuthMember(data: Record<string, any>) {
  return $http.post("/server/web/product/app/saveConfig", data);
}

// 获取bid信息
export function apiBidList(params: Record<string, any>) {
  return $http.get("/api/v1/handle/query", { params });
}