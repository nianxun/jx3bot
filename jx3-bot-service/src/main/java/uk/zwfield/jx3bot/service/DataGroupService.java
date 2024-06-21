package uk.zwfield.jx3bot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.mapper.DataGroupMapper;

import java.util.List;

/**
 * @author Field
 * @date 2024-06-20 16:56
 **/
@Service
public class DataGroupService extends ServiceImpl<DataGroupMapper, DataGroup> {

    public DataGroup getOneByGroupId(Long groupId) {
        return getOne(new QueryWrapper<DataGroup>().eq("group_num", groupId));
    }

    public List<DataGroup> getListByAction(Integer action) {
        return baseMapper.selectList(new QueryWrapper<DataGroup>().like("bot_switch", "," + action + ","));
    }
}
