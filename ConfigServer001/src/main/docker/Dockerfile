# 基于哪个镜像
FROM java:8
# 拷贝文件到容器，也可以直接写成ADD xxxxx.jar /app.jar
COPY ConfigServer001-*.jar /app.jar
# 声明需要暴露的端口
EXPOSE 7001
#配置环境变量，接受外部传递参数
#启动端口
ENV PORT=""
#注册中心地址
ENV EUREKA=""
# 配置容器启动后执行的命令
ENTRYPOINT ["sh","-c","java $PORT $EUREKA -jar /app.jar"]