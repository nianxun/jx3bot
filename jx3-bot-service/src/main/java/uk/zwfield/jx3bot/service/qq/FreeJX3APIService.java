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
import jx3api.api.http.data.ActiveCurrentData;
import jx3api.api.http.data.SchoolToxicData;
import jx3api.api.http.data.ServerCheckData;
import jx3api.api.http.data.WebNewsAnnounceData;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    private LoadingCache<String, String> newsAnnounceCache;
    @Resource
    private ApiService apiService;


    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^开服?$")
    public void checkServer(Bot bot, GroupMessageEvent event) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        ServerCheckData serverCheckData = apiService.serverCheck(dataGroup.getServerName()).getData();
        if (serverCheckData == null) {
            bot.sendGroupMsg(event.getGroupId(), "服务器不存在", false);
            return;
        }
        String msg = MessageFormat.format("服务器：{0} \n开服状态：{1} \n时间：{2}",
                serverCheckData.getServer(),
                serverCheckData.getStatus() == 1 ? "已开服" : "维护中",
                serverCheckData.getTime());
        bot.sendGroupMsg(event.getGroupId(), msg, false);
    }

    @CheckGroupBind
    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^日常?$")
    public void activeCurrent(Bot bot, GroupMessageEvent event) {
        DataGroup dataGroup = dataGroupCache.get(event.getGroupId());
        ActiveCurrentData currentData = apiService.activeCalendar(dataGroup.getServerName(), 0).getData();
        if (currentData == null) {
            bot.sendGroupMsg(event.getGroupId(), "日常数据获取失败~", false);
            return;
        }
        StringBuilder msg = new StringBuilder();
        msg.append(" 日常[").append(dataGroup.getServerName()).append("]")
                .append(" 当前时间：").append(currentData.getDate())
                .append(" 星期").append(currentData.getWeek()).append("\n")
                .append("【秘境大战】").append(currentData.getWar()).append("\n")
                .append("【战场任务】").append(currentData.getBattle()).append("\n")
                .append("【阵营任务】").append(currentData.getOrecar()).append("\n")
                .append("【门派事件】").append(currentData.getSchool()).append("\n")
                .append("【驰援任务】").append(currentData.getRescue()).append("\n");
        if (StringUtils.hasText(currentData.getDraw())) {
            msg.append("【美人画像】").append(currentData.getDraw()).append("\n");
        }
        if(!CollectionUtils.isEmpty(currentData.getLeader())) {
            msg.append("【世界BOSS】").append(currentData.getLeader().get(0)).append("\n")
                    .append("【世界BOSS·旧】").append(currentData.getLeader().get(1)).append("\n");
        }
        if (!CollectionUtils.isEmpty(currentData.getTeam())) {
            msg.append("【武林通鉴·公共任务】").append(currentData.getTeam().get(0)).append("\n")
                    .append("【武林通鉴·秘境任务】").append(currentData.getTeam().get(1)).append("\n")
                    .append("【武林通鉴·团队秘境】").append(currentData.getTeam().get(2));
        }
        bot.sendGroupMsg(event.getGroupId(), msg.toString(), false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^公告?$")
    public void newsAnnounce(Bot bot, GroupMessageEvent event) {
        List<WebNewsAnnounceData> data = apiService.newsAnnounce(1).getData();
        if(CollectionUtils.isEmpty(data)) {
            bot.sendGroupMsg(event.getGroupId(), "公告数据获取失败~", false);
            return;
        }
        MsgUtils msg = MsgUtils.builder().img("base64://" + newsAnnounceCache.get(data.getFirst().getUrl()));
        bot.sendGroupMsg(event.getGroupId(), msg.build(), false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "^小药\\s(\\S+)?$")
    public void schoolToxicData(Bot bot, GroupMessageEvent event, Matcher matcher) {
        String name = matcher.group(1);
        if (!StringUtils.hasText(name)) {
            bot.sendGroupMsg(event.getGroupId(), "请输入正确的心法名称", false);
            return;
        }
        List<SchoolToxicData> schoolToxicDataList = apiService.schoolToxicData(name).getData();
        if (schoolToxicDataList == null || schoolToxicDataList.isEmpty()) {
            bot.sendGroupMsg(event.getGroupId(), "心法数据获取失败~", false);
            return;
        }
        MsgUtils text = MsgUtils.builder()
                .text("心法：" + name);
        schoolToxicDataList.forEach(e -> text.text("\n" + e.getClassType() + ": " + e.getToxic()));
        bot.sendGroupMsg(event.getGroupId(), text.build(), false);
    }
}
