package me.enzoly.redis.packet;

import me.enzoly.redis.listener.RedisListener;
import java.lang.reflect.Method;

/**
 * Represents a handler for a specific Redis packet.
 */

public class RedisPacketHandler {

    private final Method method;
    private final  RedisListener listener;

    /**
     * Constructs a new RedisPacketHandler with the specified method and listener.
     *
     * @param method   The method that handles the Redis packet.
     * @param listener The listener associated with the packet handler.
     */
    public RedisPacketHandler(Method method, RedisListener listener) {
        this.method = method;
        this.listener = listener;
    }

    public Method getMethod() {
        return method;
    }

    public RedisListener getListener() {
        return listener;
    }
}
