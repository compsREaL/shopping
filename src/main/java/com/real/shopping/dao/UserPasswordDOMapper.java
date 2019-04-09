package com.real.shopping.dao;

import com.real.shopping.dataobject.UserPasswordDO;
import org.springframework.cache.annotation.Cacheable;

public interface UserPasswordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int insert(UserPasswordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int insertSelective(UserPasswordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    UserPasswordDO selectByPrimaryKey(Integer id);

    @Cacheable(value = "password",key = "'password:'+#userId",unless = "#result==null")
    UserPasswordDO selectByUserId(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int updateByPrimaryKeySelective(UserPasswordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int updateByPrimaryKey(UserPasswordDO record);
}