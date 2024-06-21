package uk.zwfield.jx3bot.service.qq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uk.zwfield.jx3bot.annotation.CheckGroupBind;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.service.DataGroupService;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * @author Field
 * @date 2024-06-21 17:54
 **/
@Shiro
@Service
public class FreePushService {

    /**
     * 可用的推送，白名单模式，硬编码
     */
    private static final Map<String, Integer> ACTION_MAP = Map.of(
            "开服监控", 2001,
            "新闻资讯", 2002,
            "游戏更新", 2003,
            "八卦速报", 2004
    );

    @Resource
    private LoadingCache<Long, DataGroup> dataGroupCache;
    @Resource
    private DataGroupService dataGroupService;

    @SneakyThrows
    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "(开启|关闭) (.*)")
    public void pushChange(Bot bot, GroupMessageEvent event, Matcher matcher) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        Set<Integer> actionList = new ObjectMapper().readValue(dataGroup.getBotSwitch(), new TypeReference<>() {
        });
        String action = matcher.group(2);
        if(!ACTION_MAP.containsKey(action)) {
            return;
        }
        Integer actionCode = ACTION_MAP.get(action);
        if("开启".equals(matcher.group(1))) {
            actionList.add(actionCode);
        } else {
            actionList.remove(actionCode);
        }
        dataGroup.setBotSwitch(new ObjectMapper().writeValueAsString(actionList));
        dataGroupCache.put(event.getGroupId(), dataGroup);
        dataGroupService.updateById(dataGroup);
        bot.sendGroupMsg(event.getGroupId(), "操作成功", false);
    }
}
