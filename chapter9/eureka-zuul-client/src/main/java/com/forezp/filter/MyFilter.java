package com.forezp.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by fangzhipeng on 2017/6/26.
 */
@Component
public class MyFilter extends ZuulFilter {//zuul实现过滤器只需要继承 ZuulFilter ，井实现 Zuu!Filter 中的抽象方法

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     pre：可以在请求被路由之前调用
     route：在路由请求时候被调用
     post：在route和error过滤器之后被调用
     error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;//值越小，越早执行该过滤器
    }

    @Override
    public boolean shouldFilter() {//表示该过滤器是否过滤逻辑，如果为 true ，则执行 run（）方法
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}