package uk.zwfield.jx3bot.service.qq;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import jakarta.annotation.Resource;
import jx3api.api.config.ApiProperties;
import jx3api.api.http.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.zwfield.jx3bot.config.JX3BotConfig;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.exception.BotException;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @author Field
 * @date 2024-06-29 17:07
 **/
@Shiro
@Service
public class VIP1JX3APIService {

    @Resource
    private LoadingCache<Long, DataGroup> dataGroupCache;
    @Resource
    private ApiService apiService;
    @Resource
    private ApiProperties apiProperties;

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^查询(|\\s\\S+)\\s(\\S+)?$")
    public void luckAdventure(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(2);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入角色名！", false);
            return;
        }
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            MsgUtils text = MsgUtils.builder();
            String url = apiService.luckAdventureView(serverName, name, apiProperties.getTicket());
            if (StringUtils.hasText(url)) {
                text.img(url);
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }


    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^属性(|\\s\\S+)\\s+(\\S+)?$")
    public void roleAttribute(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(2);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入角色名！", false);
            return;
        }
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            MsgUtils text = MsgUtils.builder();
            String url = apiService.roleAttributeView(serverName, name, apiProperties.getTicket());
            if (StringUtils.hasText(url)) {
                text.img(url);
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^物价\\s+(\\S+)?$")
    public void tradeRecord(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(1);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入物品名称！", false);
            return;
        }
        MsgUtils text = MsgUtils.builder();
        String url = apiService.tradeRecordView(name);
        if (StringUtils.hasText(url)) {
            text.img(url);
        } else {
            text.text("查询失败");
        }
        bot.sendGroupMsg(event.getGroupId(), text.build(), false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^沙盘(|\\s\\S+)$")
    public void serverSand(Bot bot, GroupMessageEvent event, Matcher matcher) {
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            MsgUtils text = MsgUtils.builder();
            String url = apiService.serverSandView(serverName);
            if (StringUtils.hasText(url)) {
                text.img(url);
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^金价(|\\s\\S+)$")
    public void tradeDemon(Bot bot, GroupMessageEvent event, Matcher matcher) {
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            MsgUtils text = MsgUtils.builder();
            String url = apiService.tradeDemonView(serverName);
            if (StringUtils.hasText(url)) {
                text.img(url);
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^攻略\\s+(\\S+)?$")
    public void luckStrategy(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(1);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入奇遇名称！", false);
            return;
        }
        if(!JX3BotConfig.LUCK_MAP.containsKey(name)){
            bot.sendGroupMsg(event.getGroupId(), "无法找到此攻略！", false);
            return;
        }
        MsgUtils text = MsgUtils.builder();
        text.text("奇遇：" + name);
        text.text("\n攻略：" + "https://jx3box.com/adventure/" + JX3BotConfig.LUCK_MAP.get(name));
        bot.sendGroupMsg(event.getGroupId(), text.build(), false);
    }


    /**
     * 获取服务器名称
     */
    private String getMatcherServerName(Matcher matcher, Long groupId) throws BotException {
        String serverName = matcher.group(1).trim();
        if (!StringUtils.hasText(serverName)) {
            DataGroup dataGroup = dataGroupCache.get(groupId);
            if (dataGroup == null || !StringUtils.hasText(dataGroup.getServerName())) {
                throw new BotException("请先绑定服务器或指定服务器！");
            }
            serverName = dataGroup.getServerName();
        }
        boolean isExist = false;
        for (Map.Entry<String, List<String>> entry : JX3BotConfig.SERVER_MAP.entrySet()) {
            String k = entry.getKey();
            List<String> v = entry.getValue();
            if (v.contains(serverName)) {
                serverName = k;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new BotException("服务器不存在！");
        }
        return serverName;
    }


}
