package com.lkh.sboot.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyAccessFilter extends AccessControlFilter {
    private static final Logger log= LoggerFactory.getLogger(MyAccessFilter.class);

    private String unauthorizedUrl = "/unauthorized.html";
    private String loginUrl = "/login.html";

    /**
     *
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * (感觉这里应该是对白名单（不需要登录的接口）放行的)
     * 如果isAccessAllowed返回true则onAccessDenied方法不会继续执行
     * 这里可以用来判断一些不被通过的链接（个人备注）
     * * 表示是否允许访问 ，如果允许访问返回true，否则false；
     * @param servletRequest
     * @param servletResponse
     * @param object 表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     * */
    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object object) throws Exception{
//        Subject subject = getSubject(servletRequest,servletResponse);
//        String url = getPathWithinApplication(servletRequest);
//        log.info("当前用户正在访问的 url => " + url);
//        log.info("subject.isPermitted(url);"+subject.isPermitted(url));
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * onAccessDenied是否执行取决于isAccessAllowed的值，如果返回true则onAccessDenied不会执行；如果返回false，执行onAccessDenied
     * 如果onAccessDenied也返回false，则直接返回，不会进入请求的方法（只有isAccessAllowed和onAccessDenied的情况下）
     * */
    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        System.out.println("onAccessDenied==================================");
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
            saveRequest(request);
            WebUtils.issueRedirect(request, response, loginUrl);
        } else {
            HttpServletRequest req = (HttpServletRequest)request;
            String url = req.getServletPath();
            System.out.println(url);
            url = url.substring(1, url.length());
            System.out.println("hou="+url);
            boolean isPermitted = SecurityUtils.getSubject().isPermitted(url);
            if(!isPermitted){
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            }else{
                return true;
            }
        }

            return false;
    }
}
