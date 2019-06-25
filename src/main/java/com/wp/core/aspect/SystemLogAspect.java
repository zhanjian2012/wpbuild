package com.wp.core.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.wp.common.IPUtils;
import com.wp.core.annoation.SystemLog;
import com.wp.modules.sys.entity.Log;
import com.wp.modules.sys.service.LogService;

/**
 * 
 * @Description： 系统日志切面
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:26:37]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.wp.core.annoation.SystemLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysemLog(result, point, time);

		return result;
	}

    private void saveSysemLog(Object result, ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		Log log = new Log();
		SystemLog systemLog = method.getAnnotation(SystemLog.class);
		if(systemLog != null){
			//注解上的描述
			log.setExecDesc(systemLog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		log.setExecMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args);
			log.setArgs(params);
		}catch (Exception e){

		}

		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//设置IP地址
		log.setIp(IPUtils.getIpAddr(request));

		//用户名
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		log.setUsername(username);

        log.setReqMethod(method + " " + request.getProtocol());
        String url = request.getRequestURL().append(StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()).toString();// url
        log.setReqUri(url);
		log.setExecTime(time);
		log.setReturnVal(new Gson().toJson(result));
		log.setCreateTime(new Date());
		//保存系统日志
		logService.save(log);
	}

}
