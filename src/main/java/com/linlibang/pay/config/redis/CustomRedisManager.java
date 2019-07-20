package com.linlibang.pay.config.redis;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * 描述:
 * 日期: 2018/7/12--15:14
 *
 * @author yanpeicai
 */
@Repository
public class CustomRedisManager extends RedisCacheManager {

	/* 24 HOURS*/
	private static final long DEFAULT_EXPIRE_TIME = 60 * 60 * 24L;
	private static final String USER_CACHE = "User";

	public CustomRedisManager(RedisTemplate redisTemplate) {
		super(redisTemplate);
		setDefaultExpiration(DEFAULT_EXPIRE_TIME);

		HashMap<String, Long> expires = new HashMap<>();
		expires.put(USER_CACHE, DEFAULT_EXPIRE_TIME);// 1 min

		setExpires(expires);

	}


	public void putUser(Object key, Object value) {
		getCache(USER_CACHE).put(key, value);
	}

	// <T> T get(Object var1, Class<T> var2);
	public String getUser(String key) {
		return getCache(USER_CACHE).get(key, String.class);
	}

	public <T> T getUser(String key, Class<T> type) {
		return getCache(USER_CACHE).get(key, type);
	}

}
