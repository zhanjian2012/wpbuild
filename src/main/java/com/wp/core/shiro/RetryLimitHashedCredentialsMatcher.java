package com.wp.core.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description： 密码匹配
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:29:33]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

//    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
//    }

    /*@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }*/
    
    private  Logger logger = LoggerFactory.getLogger(CredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
//        //所需加密的参数  即  用户输入的密码
//        String source = String.valueOf(utoken.getPassword());
//        //[盐] 一般为用户名 或 随机数
//        String salt = utoken.getUsername();
//        //加密次数
//        int hashIterations = 50;
//        SimpleHash sh = new SimpleHash("md5", source, salt, hashIterations);
//        String Strsh =sh.toHex();
//        //打印最终结果
//        logger.info("正确密码为："+Strsh);
//        //获得数据库中的密码
//        String dbPassword= (String) getCredentials(info);
//        logger.info("数据库密码为："+dbPassword);
//        //进行密码的比对
//        return this.equals(Strsh, dbPassword);
    	return true;
    }
}
