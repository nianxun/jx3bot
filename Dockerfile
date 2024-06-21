FROM mdsol/java21-jdk:latest
LABEL authors="field"
WORKDIR /home/bot
EXPOSE 2333
VOLUME logs
COPY jx3-bot.jar jx3-bot.jar
COPY config config
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime  \
    && echo $TZ > /etc/timezone  \
    && mkdir logs  \
    && chmod 777 config

CMD ["java","-jar","jx3-bot.jar","--spring.config.location=file:config/"]

#docker buildx build . --tag jx3bot:latest