package com.javadaily.message.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * <p>
 * <code>DistributedLockController</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/5/13 15:18
 */
@RestController
@RequestMapping("lock")
@Log4j2
public class DistributedLockController {
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Autowired
    private ZookeeperLockRegistry zookeeperLockRegistry;

    @GetMapping("/redis")
    public void test1() {
        Lock lock = redisLockRegistry.obtain("redis");
        try{
            //尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                log.info("lock is ready");
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            log.error("obtain lock error",e);
        } finally {
            lock.unlock();
        }
    }


    @GetMapping("/zookeeper")
    public void test2() {
        Lock lock = zookeeperLockRegistry.obtain("zookeeper");
        try{
            //尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                log.info("lock is ready");
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            log.error("obtain lock error",e);
        } finally {
            lock.unlock();
        }
    }


}
