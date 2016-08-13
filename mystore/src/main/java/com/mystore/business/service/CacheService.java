package com.mystore.business.service;

import java.io.Serializable;

public interface CacheService {
	
	public Serializable get(String key);

	public void set(String key, Serializable value);

	public void setWithExpire(String key, Serializable value, long expire);

	public void expire(String key, long expire);

	public void remove(String keys);

	public boolean exists(String key);

}
