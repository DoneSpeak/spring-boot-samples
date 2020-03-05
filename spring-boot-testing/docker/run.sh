!bash/shell

cd ..
mvn package
cd docker
cp ../target/spring-boot-testing-0.0.1-SNAPSHOT.jar spring-boot-testing.jar
# 创建镜像
docker build -t spring-boot-samples/spring-boot-testing:0.0.1-SNAPSHOT .
# 启动mysql docker
docker run -p 3306:3306 --name mysql \
  -v /var/server/docker/mysql/log:/var/log/mysql \
  -v /var/server/docker/mysql/data:/var/lib/mysql \
  -v /var/server/docker/mysql/conf:/etc/mysql \
  -d mysql:5.7

# 启动应用docker
docker run -p 8080:8080 --name spring-boot-testing \
  --link mysql:db \
  -v /var/server/docker/logs:/var/logs \
  -d spring-boot-samples/spring-boot-testing:0.0.1-SNAPSHOT