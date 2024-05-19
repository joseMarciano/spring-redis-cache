package com.spring.cache.example.marcianodev.config;

import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Bean
    RedisConnectionFactory lettuceConnectionFactory(@Value("${redis.host}") final String host) {
        final var redisUri = RedisURI.create(host);
        redisUri.setVerifyPeer(false);

        final var redisConfig = new RedisStandaloneConfiguration(redisUri.getHost(), redisUri.getPort());
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean
    public Map<String, RedisCacheConfiguration> customRedisKeyCacheConfig() {
        final var map = new HashMap<String, RedisCacheConfiguration>();
        map.put("product", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10_000))
                .disableCachingNullValues());

        return map;
    }

    @Bean
    public RedisCacheConfiguration defaultRedisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(3))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager redisCacheManager(final RedisConnectionFactory redisConnectionFactory,
                                               final RedisCacheConfiguration defaultRedisCacheConfiguration,
                                               final Map<String, RedisCacheConfiguration> customRedisKeyCacheConfig) {
        final var builder = RedisCacheManager.builder(redisConnectionFactory);
        builder.cacheDefaults(defaultRedisCacheConfiguration);
        customRedisKeyCacheConfig.forEach(builder::withCacheConfiguration);
        return builder.build();
    }

    @Bean
    public CacheResolver cacheResolver(final RedisCacheManager redisCacheManager) {
        return new SimpleCacheResolver(redisCacheManager);
    }
}
