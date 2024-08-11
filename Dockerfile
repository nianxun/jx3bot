FROM swr.cn-east-3.myhuaweicloud.com/kubesre/docker.io/bitnami/java:21-debian-12
LABEL authors="field"
WORKDIR /home/bot
EXPOSE 2333
VOLUME logs
VOLUME db
COPY jx3-bot-service/build/libs/jx3-bot.jar jx3-bot.jar
COPY db/bot.sqlite db/bot.sqlite
COPY jx3-bot-service/src/main/resources/config config
# 设定时区
ENV TZ=Asia/Shanghai
RUN apt update  \
    && apt install -y fonts-liberation fonts-dejavu fonts-freefont-ttf fonts-noto fonts-noto-cjk fonts-noto-color-emoji \
    && apt install -y firefox-esr  \
    && fc-cache -fv\
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime  \
    && echo $TZ > /etc/timezone  \
    && mkdir logs  \
    && chmod 777 config

CMD ["java","-jar","jx3-bot.jar","--spring.config.location=file:config/"]

# docker buildx build  --tag jx3bot:1.0 .