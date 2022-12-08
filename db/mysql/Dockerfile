FROM mysql:8.0.24
ARG CONFIG_FILE=mysql.cnf
ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ${CONFIG_FILE} /etc/mysql/conf.d/mysql.cnf