package lee.bowon.holiday.config

import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HolidayCacheConfig {

    @Bean
    fun cacheManager() : CacheManager {
       val manager = SimpleCacheManager()
        manager.setCaches(listOf(
            ConcurrentMapCache("holidays")
        ))
        return manager
    }
}