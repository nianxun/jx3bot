package uk.zwfield.jx3bot.service.qq;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotContainer;
import jakarta.annotation.Resource;
import jx3api.api.ws.IWsDataPushService;
import jx3api.api.ws.data.BaseWsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.service.DataGroupService;

import java.util.List;

/**
 * @author Field
 * @date 2024-06-21 14:24
 **/
@Slf4j
@Service
public class WsDataPushServiceImpl implements IWsDataPushService {

    @Resource
    private DataGroupService dataGroupService;
    @Resource
    private BotContainer botContainer;

    @Override
    public void pushDataByWs(BaseWsData baseWsData) {
        if (baseWsData == null) {
            return;
        }
        List<DataGroup> dataGroupList = dataGroupService.getListByAction(baseWsData.getAction());
        if (!dataGroupList.isEmpty()) {
            dataGroupList.stream()
                    .filter(e -> e.getGroupNum() != null)
                    .filter(e -> !StringUtils.hasText(baseWsData.getServer())
                            || "-".equals(baseWsData.getServer())
                            || e.getServerName().equals(baseWsData.getServer()))
                    .forEach(dataGroup -> {
                        Bot bot = botContainer.robots.get(dataGroup.getBotNum());
                        if (bot != null) {
                            bot.sendGroupMsg(dataGroup.getGroupNum(), baseWsData.getMsg(), false);
                        }
                    });
        }
    }
}
