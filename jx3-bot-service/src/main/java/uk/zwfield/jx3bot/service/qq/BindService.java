package uk.zwfield.jx3bot.service.qq;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.zwfield.jx3bot.annotation.CheckGroupBind;
import uk.zwfield.jx3bot.config.JXServerConfig;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.service.DataGroupService;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;


/**
 * @author Field
 * @date 2024-06-20 12:58
 **/
@Shiro
@Slf4j
@Service
public class BindService {

    @Resource
    private DataGroupService dataGroupService;
    @Resource
    private LoadingCache<Long, DataGroup> dataGroupCache;


    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "绑定 (.*)")
    public void bind(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String server = matcher.group(1);
        Long groupId = event.getGroupId();
        log.debug("绑定服务器：{}", server);
        AtomicReference<String> msg = new AtomicReference<>("服务器不存在！");
        JXServerConfig.SERVER_MAP.forEach((k, v) -> {
            if (v.contains(server)) {
                DataGroup dataGroup = dataGroupCache.get(groupId);
                if (dataGroup == null) {
                    dataGroup = new DataGroup();
                    dataGroup.setGroupNum(groupId);
                }
                dataGroup.setServerName(k);
                dataGroup.setBotNum(bot.getSelfId());
                dataGroupCache.put(groupId, dataGroup);
                dataGroupService.saveOrUpdate(dataGroup);
                msg.set(MessageFormat.format("绑定服务器：{0}，成功！", k));
            }
        });
        bot.sendGroupMsg(groupId, msg.get(), false);
    }

    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "关闭所有推送")
    public void unbind(Bot bot, GroupMessageEvent event) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        if (dataGroup != null) {
            dataGroup.setBotSwitch("[]");
            dataGroupService.save(dataGroup);
        }
        bot.sendGroupMsg(event.getGroupId(), "取消绑定成功！推送功能已停用！", false);
    }

}
