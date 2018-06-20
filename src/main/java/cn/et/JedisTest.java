package cn.et;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
 * redis单连接测试类
 */
public class JedisTest {

	/**
	 * 测试redis连接
	 */
	@Test
	public void testConnRedis() {
		//连接的主机
		String ip = "localhost";
		//连接的端口
		int port = 6379;
		//jedis可以操作所有redis命令
		Jedis jedis = new Jedis(ip,port);
		
		//搜索所有的key
		Set<String> keys = jedis.keys("a*");
		for(String key:keys) {
			System.out.println(key);
		}
		
		//设置key的过期(10秒后过期)
		jedis.expire("a", 10);
		
		//关闭redis，会消耗资源
		jedis.close();
	}
	
	
	static String ip = "localhost";
	static int port = 6379;
	static JedisPool jp = new JedisPool(ip,port);
	/**
	 * 连接池使用
	 */
	@Test
	public void testConnPoolRedis() {
		//从连接池中获取连接
		Jedis jedis = jp.getResource();
		
		String s = jedis.set("hello", "zyx");
		
		//放回连接池
		jp.returnResource(jedis);
		
		System.out.println(s);
	}
	
	
}
