package uk.zwfield.jx3bot.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import jakarta.annotation.Resource;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.zwfield.jx3bot.entity.DataGroup;
import uk.zwfield.jx3bot.service.DataGroupService;

import java.util.concurrent.TimeUnit;

/**
 * @author Field
 * @date 2024-06-20 17:13
 **/
@Configuration
public class BotCache {

    @Resource
    private DataGroupService dataGroupService;

    @Bean
    public LoadingCache<Long, DataGroup> dataGroupCache() {
        return Caffeine.newBuilder()
                //默认6小时
                .expireAfterAccess(6, TimeUnit.HOURS)
                .expireAfterWrite(6, TimeUnit.HOURS)
                .build(new CacheLoader<>() {
                    @Override
                    public @Nullable DataGroup load(Long key) {
                        return dataGroupService.getOneByGroupId(key);
                    }
                });
    }
}
