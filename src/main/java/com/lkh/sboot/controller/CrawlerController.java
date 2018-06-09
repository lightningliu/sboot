package com.lkh.sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class CrawlerController {
    @RequestMapping("/")
//    @ResponseBody
    public String Crawler() throws IOException {
        return "index";
/*//        User user=new User();
//        user.setId(UUID.randomUUID()+"");
//        user.setUserAddr("寿光会死肉偿冷凝管");
//        user.setUserEmail("aaaaaaaa");
//        user.setUserName("张三");
//        user.setUserPhone("15662111454");
//        user.setUserRealname("gttttttttt");
//        System.out.println(userMapper.insert(user));
        String url="http://www.baidu.com";
        OkHttpClient client=new OkHttpClient();
        //构建请求
        Request.Builder builder=new Request.Builder().url(url);
//        builder.addHeader("","");
        Request request=builder.build();
        Response response=client.newCall(request).execute();
        String result=response.body().string();
//        System.out.println(result);
        return result;*/
    }
}
