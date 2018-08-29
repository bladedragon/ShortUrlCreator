package team.redrock.shorturl.service;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.redrock.shorturl.DAO.UrlMapper;
import team.redrock.shorturl.DAO.UserMapper;
import team.redrock.shorturl.Utility.NormalUtils;
import team.redrock.shorturl.Utility.Response;
import team.redrock.shorturl.entity.User;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImp implements UserService{

  @Autowired
    UserMapper userMapper;
    @Override
//    @Transactional
    public Response userRegister(String username,String password){
        if(NormalUtils.IsNull(username)){
            Response response = new Response("-1","用户名不能为空!");
            return response;
        }
        if(NormalUtils.IsNull(password)){
            Response response = new Response("-1","密码不能为空！");
            return response;
        }
        if(userMapper.SelectByUsername(username)!=null)
        {
            return new Response("-1","用户名已存在！");
        }
        User user = new User();

        user.setUsername(username);
//        String encodedpassword = Encoding.getMD5(password);
        user.setPassword(password);
        user.setIsPrime(0);
        userMapper.insert(user);
        return new Response("0", JSON.toJSONString(user));

    }

    @Override
    public Response userLogin(String username, String password, HttpServletRequest request) {

        if (NormalUtils.IsNull(username)) {
            return new Response("-1", "用户名不能为空");
        }
        if (NormalUtils.IsNull((password))) {
            return new Response("-1", "密码不能为空");
        }
        User user = userMapper.SelectByUsername(username);
        if (user == null) {
            return new Response("-1", "用户名不存在");
        } else {
            if (!user.getPassword().equals(password)) {
                return new Response("-1", "密码错误");
            } else {
//                if (request.getSession().getAttribute("user").equals(username)) {
//                    System.out.println("得到sessionl");
//                    return new Response("-1", "用户已登录");
//                } else {
                request.getSession().setAttribute("user", user.getUsername());
                String user1 = (String) request.getSession().getAttribute("user");
                System.out.println("session为" + user1);

//                System.out.println("当前用户登录"+request.getSession().getAttribute("user_session"));
                return new Response("0", JSON.toJSONString(user));

            }

        }




    }
}
