package lee.bowon.holiday.config

import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomCacheConfig {

    companion object {
        const val HOLIDAY_LIST_CACHE = "holiday_list"
        const val LAST_UPDATE_CACHE = "last_update"
        const val LAST_UPDATE_DATE_LIST_CACHE = "last_update_list"
    }

    @Bean
    fun cacheManager(): CacheManager {
        val manager = SimpleCacheManager()
        manager.setCaches(
            listOf(
                ConcurrentMapCache(HOLIDAY_LIST_CACHE),
                ConcurrentMapCache(LAST_UPDATE_CACHE),
                ConcurrentMapCache(LAST_UPDATE_DATE_LIST_CACHE)
            )
        )
        return manager
    }
}