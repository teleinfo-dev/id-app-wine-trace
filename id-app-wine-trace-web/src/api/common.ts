import $http from "@/utils/http";

// 获取bid信息
export function apiBidList(params: Record<string, any>) {
  return $http.get("/api/v1/handle/query", { params });
}
