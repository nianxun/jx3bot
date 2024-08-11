package uk.zwfield.jx3bot.service.qq;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.common.utils.ShiroUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import freemarker.template.TemplateException;
import jakarta.annotation.Resource;
import jx3api.api.config.ApiProperties;
import jx3api.api.http.ApiService;
import jx3api.api.http.data.LuckAdventureData;
import jx3api.api.http.data.RoleAttributeData;
import jx3api.api.http.data.ServerSandData;
import jx3api.api.http.data.TradeDemonData;
import jx3api.api.http.data.TradeRecordData;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;
import uk.zwfield.jx3bot.config.JX3BotConfig;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.exception.BotException;
import uk.zwfield.jx3bot.utils.HTML2PNGUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
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
            List<LuckAdventureData> luckAdventureDataList = apiService.luckAdventure(serverName, name, apiProperties.getTicket()).getData();
            if (!CollectionUtils.isEmpty(luckAdventureDataList)) {
                // 合成图片
                text.img("base64://" + HTML2PNGUtil.luckAdventure(serverName, name, luckAdventureDataList));
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException | IOException | TemplateException | ParserConfigurationException | SAXException |
                 URISyntaxException e) {
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
            RoleAttributeData roleAttributeData = apiService.roleAttribute(serverName, name, apiProperties.getTicket()).getData();
            if (roleAttributeData != null) {
                // 合成图片
                text.img("base64://" + HTML2PNGUtil.roleAttribute(roleAttributeData));
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException | IOException | TemplateException | URISyntaxException e) {
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
        try {
            MsgUtils text = MsgUtils.builder();
            TradeRecordData tradeRecordData = apiService.tradeRecord(name).getData();
            if (tradeRecordData != null) {
                // 合成图片
                text.img("base64://" + HTML2PNGUtil.tradeRecord(tradeRecordData));
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (IOException | TemplateException | URISyntaxException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^沙盘(|\\s\\S+)$")
    public void serverSand(Bot bot, GroupMessageEvent event, Matcher matcher) {
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            MsgUtils text = MsgUtils.builder();
            ServerSandData sandData = apiService.serverSand(serverName).getData();
            if (sandData != null) {
                // 合成图片
                text.img("base64://" + HTML2PNGUtil.serverSand(sandData));
            } else {
                text.text("查询失败");
            }
            bot.sendGroupMsg(event.getGroupId(), text.build(), false);
        } catch (BotException | IOException | TemplateException | URISyntaxException e) {
            bot.sendGroupMsg(event.getGroupId(), e.getMessage(), false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^金价(|\\s\\S+)$")
    public void tradeDemon(Bot bot, GroupMessageEvent event, Matcher matcher) {
        try {
            String serverName = getMatcherServerName(matcher, event.getGroupId());
            List<TradeDemonData> demonDataList = apiService.tradeDemon(serverName, 5).getData();
            if (!CollectionUtils.isEmpty(demonDataList)) {
                List<String> list = demonDataList.stream().map(e ->
                        "万宝楼：" + e.getWanbaolou() + "\n" +
                                "贴吧：" + e.getTieba() + "\n" +
                                "5173：" + e.getFive173() + "\n" +
                                "7881：" + e.getSeven881() + "\n" +
                                "dd373：" + e.getDd373() + "\n" +
                                "uu898：" + e.getUu898() + "\n" +
                                "日期：" + e.getDate()).toList();
                List<Map<String, Object>> forwardMsg = ShiroUtils.generateForwardMsg(bot.getSelfId(), "金价", list);
                bot.sendGroupForwardMsg(event.getGroupId(), forwardMsg);
            }
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
        if (!JX3BotConfig.LUCK_MAP.containsKey(name)) {
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
