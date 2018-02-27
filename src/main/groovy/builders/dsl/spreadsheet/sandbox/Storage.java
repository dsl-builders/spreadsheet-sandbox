package builders.dsl.spreadsheet.sandbox;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Storage {

    private static final Cache<String, byte[]> BINARY_CACHE = CacheBuilder
            .newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    public static InputStream read(String key) {
        byte[] bytes = BINARY_CACHE.getIfPresent(key);
        if (bytes == null) {
            bytes = new byte[0];
        }
        return new ByteArrayInputStream(bytes);
    }

    public static String store(byte[] bytes) {
        String uuid = UUID.randomUUID().toString();
        BINARY_CACHE.put(uuid, bytes);
        return uuid;
    }

}
