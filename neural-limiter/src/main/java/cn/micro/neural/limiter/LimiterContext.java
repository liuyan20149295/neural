package cn.micro.neural.limiter;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The Neural Context.
 *
 * @author lry
 */
@Data
@ToString
public class LimiterContext implements Serializable {

    private static final InheritableThreadLocal<LimiterContext> THREAD_LOCAL = new InheritableThreadLocal<>();

    private String id = UUID.randomUUID().toString();
    private Map<String, Object> parameters = new HashMap<>();

    public void put(String key, Object value) {
        parameters.put(key, value);
    }

    public static void initialize() {
        THREAD_LOCAL.set(new LimiterContext());
    }

    public static void set(LimiterContext limiterContext) {
        THREAD_LOCAL.set(limiterContext);
    }

    public static LimiterContext get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
