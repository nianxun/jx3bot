FROM do.nark.eu.org/mdsol/java21-jdk:latest
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
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime  \
    && echo $TZ > /etc/timezone  \
    && mkdir logs  \
    && chmod 777 config

CMD ["java","-jar","jx3-bot.jar","--spring.config.location=file:config/"]

# docker buildx build  --tag jx3bot:1.0 .