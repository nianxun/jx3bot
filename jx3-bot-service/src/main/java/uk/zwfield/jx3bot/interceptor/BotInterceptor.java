package uk.zwfield.jx3bot.interceptor;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotMessageEventInterceptor;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.MessageEvent;
import org.springframework.stereotype.Component;

/**
 * @author Field
 * @date 2024-06-20 12:40
 **/
@Component
public class BotInterceptor implements BotMessageEventInterceptor {

    @Override
    public boolean preHandle(Bot bot, MessageEvent event) {
        // 只接受群消息
        return event instanceof GroupMessageEvent;
    }

    @Override
    public void afterCompletion(Bot bot, MessageEvent event) {
        // 当 preHandle 返回值为 true 时则会执行 plugin-list，执行完毕后进入到 afterCompletion
    }

}
