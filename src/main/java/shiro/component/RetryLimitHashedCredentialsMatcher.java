package shiro.component;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunjx on 2018/3/1.
 * 扩展重试密码次数功能，如果需要修改重试次数记录时间，就修改缓存时间<br/>
 * 如果需要修改重试次数，就修改spring-shiro.xml中的maxRetry
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    private CacheManager cacheManager;

    private Cache<String,AtomicInteger> passwordRetryCache;

    private Integer maxRetry;


    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager,String passwordRetryCacheName,  Integer maxRetry) {
        this.cacheManager = cacheManager;
        this.passwordRetryCache = cacheManager.getCache(passwordRetryCacheName);
        this.maxRetry = maxRetry;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //获取用户名
        String userName = (String) token.getPrincipal();

        //获取用户重试密码次数
        AtomicInteger retryCount = passwordRetryCache.get(userName);

        if (retryCount == null){
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userName, retryCount);
        }

        //如果重试次数大于上限，抛出异常
        if(retryCount.incrementAndGet() > maxRetry){
            throw new ExcessiveAttemptsException();
        }

        //密码验证
        boolean matchs = super.doCredentialsMatch(token, info);

        //如果验证通过，清除缓存
        if(matchs){
            passwordRetryCache.remove(userName);
        }

        return matchs;
    }
}
