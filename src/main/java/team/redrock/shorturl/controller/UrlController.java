package team.redrock.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.redrock.shorturl.Utility.RandomValidateCodeUtil;
import team.redrock.shorturl.Utility.Response;

import team.redrock.shorturl.service.UrlService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UrlController {
    @Autowired
    private UrlService urlService;

    @ResponseBody
    @PostMapping(value = "/save")
    public Response saveUrl(@RequestParam("longurl") String longurl ,
                            @RequestParam("shorturl") String shorturl,
                            @RequestParam("expire") int expire){
        System.out.println(longurl);
        return urlService.saveUrl(longurl,shorturl ,expire);

    }

    @RequestMapping("/ZS/{url}")
    public String restoreUrl(@PathVariable(value = "url") String url){

      String longurl = urlService.RestoreUrl(url);
//      if(longurl.indexOf("http//")!=0){
//          if(longurl.indexOf("https//")!=0)
//          return "redirect:http//"+longurl;
//          else{
//              return "redirect:https//"+longurl;
//          }
//      }
        System.out.println("longurl为"+longurl);
        return "redirect:"+longurl;
    }

    @RequestMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        randomValidateCode.getRandcode(request, response);//输出验证码图片方法
    }

    @PostMapping(value = "/checkVerify",headers = "Accept=application/json")
    @ResponseBody
        public Response  checkVerify(@RequestParam("inputStr") String vertext ,HttpSession session){
//从session中获取随机数
//        String inputStr = requestMap.get("inputStr").toString();
        System.out.println("yam:"+vertext);
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        System.out.println(random);
        if (random == null) {
            return new Response("-1","验证异常");
        }
        if (random.equals(vertext)) {
            return new Response("0","验证成功");
        } else {
            return new Response("-1","验证码不正确");
        }
    }

}
