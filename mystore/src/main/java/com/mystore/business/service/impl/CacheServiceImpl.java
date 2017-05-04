package com.mystore.business.service.impl;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mystore.business.service.CacheService;

@Service("cacheService")
public class CacheServiceImpl implements CacheService{
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Override
	public Serializable get(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, Serializable value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void setWithExpire(String key, Serializable value, long expire) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}

	@Override
	public void expire(String key, long expire) {
		// TODO Auto-generated method stub
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		redisTemplate.delete(key);
	}

	@Override
	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.hasKey(key);
	}

}
