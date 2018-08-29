package team.redrock.shorturl.DAO;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.redrock.shorturl.entity.Url;

@Mapper
@Repository
public  interface UrlMapper {

    @Select("select * from url where shorturl = #{shortpath} ")
    Url SelectByShortpath(String shortpath);

    @Select("select * from url where hashid = #{Hashid} ")
    Url SelectByHashid(String hashid);

    @Select("select * from url where longpath = #{longpath} ")
    Url SelectByLong(String longpath);

    @Delete("Delete from url where shorturl = #{shorturl} ")
    int DeleteUrl(String shorturl);

//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("Insert into url(longpath,hashid,shortpath,id) values(#{longpath},#{hashid},#{shortpath},#{id}) ")
    void insert(Url url);

}
