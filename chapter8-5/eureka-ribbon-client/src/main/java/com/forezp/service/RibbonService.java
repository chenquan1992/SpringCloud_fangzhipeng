package com.forezp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fangzhipeng on 2017/6/13.
 */
@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")//hi（）方法启用 Hystrix 熔断器的功能，hiError为熔断器调用的方法的名称，详见下一个方法
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name="+name,String.class);
    }

    /**
     * 其中 fallbackMethod 为处理回退（ fallback ）逻
     辑的方法。在本例中， 直接返回了 字符串。在熔断器打开的状态下，会执行 fallback 逻辑。
     fallback 的逻辑最好是返回些静态的字符串，不需要处理复杂的逻辑，也不需要远程调度其
     他服务，这样方便执行快速失败，释放线程资源 如果一定要在 fallback 逻辑中远程调度其他
     服务，最好在远程调度其他服务时，也加上熔断器。
     */
    public String hiError(String name) {//熔断器的逻辑处理，相当于请求超时的一些逻辑处理
        return "hi,"+name+",sorry,error!";
    }
}
