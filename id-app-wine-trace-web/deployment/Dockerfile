# 基础镜像
FROM nginx:1.21.1

# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
     && echo "Asia/Shanghai" > /etc/timezone

# 项目路径
COPY dist /usr/share/nginx/html
COPY default.conf /etc/nginx/conf.d/default.conf


