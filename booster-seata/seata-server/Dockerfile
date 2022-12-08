FROM moxm/java:1.8-full as builder
WORKDIR /build
ARG JAR_FILE=target/seata-server.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract && rm app.jar

FROM moxm/java:1.8-full
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -verbose:gc -Dio.netty.leakDetectionLevel=advanced"
WORKDIR booster-seata

COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/application/ ./

EXPOSE 8091

CMD sleep 30; java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
