package team.redrock.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.redrock.shorturl.Utility.Ipsettings;
import team.redrock.shorturl.Utility.Response;
import team.redrock.shorturl.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public Response userRegister(@RequestParam("username")String username,
                                 @RequestParam("password")String password){

        return userService.userRegister(username,password);
    }

    @PostMapping(value = "/login")
    public Response userLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletRequest request){

        return userService.userLogin(username,password,request);
    }
    @GetMapping(value = "/exit")
    public Response userLogoff(HttpServletRequest request, HttpServletResponse response){
        HttpSession session =request.getSession(false);
        if(session!=null){
            session.invalidate();
        }


        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new Response("0","退出登录成功");
    }

    @RequestMapping(value = "/getIp", method = RequestMethod.POST)
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return Ipsettings.getRemoteHost(request);
    }


}
