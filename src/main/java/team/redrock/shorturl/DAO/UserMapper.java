package team.redrock.shorturl.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import team.redrock.shorturl.entity.Url;
import team.redrock.shorturl.entity.User;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username} ")
    User SelectByUsername(String username);

//    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    @Insert("Insert into user(userId,username,password,isPrime) values(#{userId},#{username},#{password},#{isPrime}) ")
    void insert(User user);

}
