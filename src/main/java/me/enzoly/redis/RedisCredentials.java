package me.enzoly.redis;

import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Getter
public class RedisCredentials {

    private final String host;
    private final int port;
    private final String password;
    private final JedisPool jedis;
    private final String channel;

    /**
     * Constructs a new instance of RedisCredentials with the specified host, port, password, and channel.
     *
     * @param host     The Redis server host.
     * @param port     The Redis server port.
     * @param password The password for authenticating with Redis.
     * @param channel  The channel for communication.
     */
    public RedisCredentials(String host, int port, String password, String channel) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.channel = channel;

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(12);

            if (password != null && !password.isEmpty()) {
                jedis = new JedisPool(config, host, port, 10000, password);
            }
            else {
                jedis = new JedisPool(config, host, port, 10000);
            }

            try (Jedis jedis = this.jedis.getResource()) {
                jedis.ping();
            }
        }
        catch (JedisConnectionException e) {
            throw new JedisConnectionException("Failed to connect to Redis server");
        }
    }

    /**
     * Constructs a new instance of RedisCredentials with the specified host, port, and channel.
     * No password authentication will be used.
     *
     * @param host    The Redis server host.
     * @param port    The Redis server port.
     * @param channel The channel for communication.
     */
    public RedisCredentials(String host, int port, String channel) {
        this(host, port, null, channel);
    }
}
