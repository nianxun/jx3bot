package uk.zwfield.jx3bot.aspectj;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.exception.BotException;

/**
 * @author Field
 * @date 2024-06-21 11:15
 **/
@Aspect
@Component
public class CheckGroupBindAspectj {

    @Resource
    private LoadingCache<Long, DataGroup> dataGroupCache;


    // 定义一个切点，匹配所有带有 @Loggable 注解的方法
    @Pointcut("@annotation(uk.zwfield.jx3bot.annotation.CheckGroupBind)")
    public void point() {}


    /**
     * 处理请求前执行
     */
    @Before(value = "point()")
    public void boBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Bot bot = null;
        Long groupId = null;
        for (Object arg : args) {
            if(arg instanceof Bot){
                bot = (Bot) arg;
            } else if(arg instanceof GroupMessageEvent){
                groupId = ((GroupMessageEvent) arg).getGroupId();
            }
        }
        if(bot != null && groupId != null){
            DataGroup dataGroup = dataGroupCache.get(groupId);
            if (dataGroup == null || StringUtils.isBlank(dataGroup.getServerName())) {
                bot.sendGroupMsg(groupId, "机器人未绑定服务器，请先绑定服务器！", false);
                throw new BotException("机器人未绑定服务器，请先绑定服务器！");
            }
        }
    }
}
