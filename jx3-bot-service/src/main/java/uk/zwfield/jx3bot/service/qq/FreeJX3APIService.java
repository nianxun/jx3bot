package uk.zwfield.jx3bot.service.qq;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import jakarta.annotation.Resource;
import jx3api.api.http.ApiService;
import jx3api.api.http.data.SchoolToxicData;
import jx3api.api.http.data.ServerCheckData;
import jx3api.api.util.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.zwfield.jx3bot.annotation.CheckGroupBind;
import uk.zwfield.jx3bot.entity.DataGroup;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Field
 * @date 2024-06-20 20:00
 **/
@Shiro
@Service
public class FreeJX3APIService {

    @Resource
    private LoadingCache<Long, DataGroup> dataGroupCache;
    @Resource
    private ApiService apiService;


    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "开服")
    public void checkServer(Bot bot, GroupMessageEvent event) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        ServerCheckData serverCheckData = apiService.serverCheck(dataGroup.getServerName());
        if (serverCheckData == null) {
            bot.sendGroupMsg(event.getGroupId(), "服务器不存在", false);
            return;
        }
        String msg = MessageFormat.format("服务器：{0}，开服状态：{1} ，时间：{2}",
                serverCheckData.getServer(),
                serverCheckData.getStatus() == 1 ? "已开服" : "维护中",
                TimeUtils.timeFormatting(serverCheckData.getTime()));
        bot.sendGroupMsg(event.getGroupId(), msg, false);
    }

    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "日常")
    public void activeCurrent(Bot bot, GroupMessageEvent event) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        String imgUrl = apiService.activeCurrentView(dataGroup.getServerName(), 0);
        if (!StringUtils.hasText(imgUrl)) {
            bot.sendGroupMsg(event.getGroupId(), "日常数据获取失败~", false);
            return;
        }
        String msg = MsgUtils.builder().img(imgUrl).build();
        bot.sendGroupMsg(event.getGroupId(), msg, false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "公告")
    public void newsAnnounce(Bot bot, GroupMessageEvent event) {
        String imgUrl = apiService.newsAnnounceView();
        String msg = MsgUtils.builder().img(imgUrl).build();
        bot.sendGroupMsg(event.getGroupId(), msg, false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "小药 (.*)")
    public void schoolToxicData(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(1);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入正确的心法名称", false);
            return;
        }
        SchoolToxicData schoolToxicData = apiService.schoolToxicData(name);
        if (schoolToxicData == null || schoolToxicData.getData() == null || schoolToxicData.getData().isEmpty()) {
            bot.sendGroupMsg(event.getGroupId(), "心法数据获取失败~", false);
            return;
        }
        List<SchoolToxicData.DataDTO> data = schoolToxicData.getData();
        MsgUtils text = MsgUtils.builder()
                .text("心法：" + name);
        data.forEach(e -> {
            text.text("\n" + e.getClassX() + ": " + e.getToxic());
        });

        bot.sendGroupMsg(event.getGroupId(), text.build(), false);
    }
}
