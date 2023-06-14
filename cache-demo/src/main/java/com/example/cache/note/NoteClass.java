package com.example.cache.note;

/**
 * 这个类用来写笔记
 *
 * @program: cache-test
 * @packagename: com.example.cache.note
 * @author: lwj
 * @date: 2021-11-17 11:30
 **/
public interface NoteClass {
    /**
     * JSR107规范 太复杂  使用spring的缓存抽象
     * Spring缓存抽象
     * 定义了Cache和CacheManager两个接口来统一不同的缓存技术并支持JSR107的注解来简化我们的开发
     * CacheManager简单描述就是用来存放Cache，Cache用于存放具体的key-value值。
     * 1，Cache接口提供了缓存组件的规范，包含缓存的各种操作
     * 2，Cache接口下Spring提供了各种XXCache的实现，eg:RedisCache, EhCacheCache, ConcurrentMapCache等
     * 3，每次调用需要缓存功能的方法时，Spring会先检查指定参数的目标方法是否被调用过，如果调用过，直接返回缓存的结果，
     * 如果第一次调用，则调用方法并在缓存结果后返回给用户，下次调用直接从缓存中获取。
     *
     *
     * 原理：
     * 1，自动配置类 CacheAutoConfiguration
     * 2，缓存配置类
     * 0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     * 1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     * 2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     * 3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     * 4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     * 5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     * 6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     * 7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     * 8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     * 9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     *
     * 3，哪个配置类生效 SimpleCacheConfiguration
     * 4，给容器注册了一个CacheManager： ConcurrentMapCacheManager
     * 5，CacheManager可以创建Cache（ConcurrentMapCache）缓存组件：它的作用是将将缓存放在ConcurrentMap中
     *
     * 运行流程：
     * 1，目标方法执行前，先通过CacheNames获取缓存组件（Cache）即CacheManager先
     * 获取相应的缓存组件，如果没有这个Cache组件则会创建。
     * 2，从Cache中通过Key获取缓存数据（key通过keyGenerator生成，如果有一个参数，key就是参数名，
     * 如果没有参数或多个参数keyGenerator有自己的逻辑生成key）。
     * 3，如果没有key就会调用目标方法。
     * 4，将目标方法的返回值 put到Cache中，key的生成策略如上面所述。
     */
}
