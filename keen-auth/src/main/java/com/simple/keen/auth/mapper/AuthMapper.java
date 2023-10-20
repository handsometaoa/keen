package com.simple.keen.auth.mapper;

import com.simple.keen.auth.model.param.LoginParam;
import com.simple.keen.system.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Select("select * from t_user where deleted != 1 " +
            "and username =#{username} and password =#{password} ")
    UserVO selectUserIdByUsernameAndPassword(LoginParam loginParam);

    @Select("select count(*) from t_user where deleted!=1 and username =#{username}")
    int countByUsername(@Param("username") String username);
}
