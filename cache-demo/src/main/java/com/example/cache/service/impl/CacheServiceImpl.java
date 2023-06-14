package com.example.cache.service.impl;

import com.example.cache.entity.Employee;
import com.example.cache.mapper.EmpMapper;
import com.example.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author lwj
 * @version 1.00
 * @time 2021/7/2 0002  下午 3:41
 * <p>
 * 主要讲解 @Cacheable  @Cacheput @CacheEvict 这三个注解的使用
 */

@Slf4j
@Service
//@CacheConfig() 在类上提供缓存相关的共同配置 如果类和方法上都配置了相同属性 以方法为准
@CacheConfig(cacheNames = "testCacheConfig")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * @AliasFor("cacheNames") String[] value() default {};
     * value作用等同于cacheNames, 配置缓存组件的名字，缓存数据保存到缓存组件中
     * value属性是个数组,可以配置多个缓存组件
     * @AliasFor("value") String[] cacheNames() default {};
     * cacheNames作用等同于上面， 配置缓存组件的名字，缓存数据保存到缓存组件中
     * <p>
     * String key() default "";
     * key 定义缓存数据的key 缓存数据以key，value的形式保存到缓存中，key默认是参数名
     * key可以通过SqEl表达式来指定 #id为参数id的值，#a0，#p0，#root.args[0] 代表第一个参数
     * 如果不配置key key的生成规则
     * 单参数：cacheNames::arg
     * 无参数: cacheNames::SimpleKey [], 后面使用 SimpleKey []来补齐
     * 多参数: cacheNames::SimpleKey [arg1, arg2...]
     * 非基础对象：cacheNames::obj.toString()
     *
     *
     * EL表达式的使用
     * #root.args   传递给缓存方法的参数，形式为数组
     * #root.caches  该方法执行是所对应的缓存，形式为数组
     * #root.target 目标对象
     * #root.targetClass 目标对象的类
     * #root.method  缓存方法
     * #root.methodName 缓存方法的名字，是#root.method.name的简写形式
     * #result 方法调用的返回值（不能用在@Cacheable注解上）
     * #Argument 任意的方法参数名（如#argName）或参数索引（如#a0或p0）
     *
     * <p>
     * <p>
     * String keyGenerator() default "";
     * key生成器，可以自己指定key的生成策略
     * <p>
     * <p>
     * String cacheManager() default "";
     * 如果有多个缓存管理器 配置缓存管理器的名字
     * 缓存管理器 cacheManager 是spring中管理缓存的组件  缓存管理器管理一个或任意多个缓存组件，
     * 缓存组件 cache 缓存组件具体负责数据存储，和Map一样，缓存中通过不同的key保存不同的数据
     * <p>
     * String cacheResolver() default "";
     * 缓存解析器 解析缓存用的  和 cacheManager 配置一个就行
     * <p>
     * String condition() default "";
     * 缓存的条件 符合条件才缓存 eg:condition="#id>0"表示 id 大于0才缓存
     * <p>
     * String unless() default "";
     * 不缓存的条件 符合条件不缓存 eg:condition="#id<=0"表示 id 小于等于0不缓存
     * <p>
     * boolean sync() default false;
     * 是否同步换成 默认为false
     */


    /**
     * @param id
     * @return
     */
    //如果springboot里面加入了redis依赖 自动配置将缓存放入redis中
    //@Cacheable(value = "cache:demo", key = "#id")
    @Cacheable(value = "cache:demo")
    @Override
    public Employee getEmpById(String id) {
        log.info("走了查询方法,查询id:{}", id);
        return empMapper.selectById(id);
    }


    @Cacheable(value = "cache:demo")
    @Override
    public Employee getEmp() {
        log.info("走了查询方法,无参数");
        return empMapper.selectById("1");
    }


    /**
     * 有时候我们可能组合多个Cache注解使用；比如用户新增成功后，
     * 我们要添加id–>user；dId—>user；email—>user的缓存；此时就需要@Caching组合多个注解标签了。
     *
     * @param employee
     * @return
     */
    @Caching(put = {
            @CachePut(value = "employee", key = "#employee.id"),
            @CachePut(value = "employee", key = "#employee.dId"),
            @CachePut(value = "employee", key = "#employee.email")
    })
    public Employee saveEmp(Employee employee) {
        empMapper.insert(employee);
        return employee;
    }
}
