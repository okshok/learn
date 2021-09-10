package ok.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import ok.common.type.CaffeineCacheType;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configurable
@EnableCaching
public class CaffeineCacheConfig {

	  @Bean
	  @Deprecated
	  public CacheManager cacheManager() {
	    SimpleCacheManager cacheManager = new SimpleCacheManager();
	    List<CaffeineCache> caches = Arrays.stream(CaffeineCacheType.values())
	        .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder()
	            .expireAfterWrite(cache.getExpiredAfterWrite(), TimeUnit.SECONDS)
	            .maximumSize(cache.getMaximumSize())
	            .build()
	            )
	        )
	        .collect(Collectors.toList());
	    cacheManager.setCaches(caches);
	    return cacheManager;
	  }
	
}
