package team.redrock.shorturl.service;

import team.redrock.shorturl.Utility.Response;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

        Response userRegister(String username, String password);
        Response userLogin(String username , String password, HttpServletRequest request);

}
