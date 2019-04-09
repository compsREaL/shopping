package com.real.shopping.dao;

import com.real.shopping.dataobject.UserDO;
import org.springframework.cache.annotation.Cacheable;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    @Cacheable(value = "user",key = "'id:'+#id",unless = "#result==null")
    UserDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 04 16:08:54 CST 2019
     */
    int updateByPrimaryKey(UserDO record);

    @Cacheable(value = "user",key = "'user:'+#telephone",unless = "#result==null")
    UserDO selectByTelephone(String telephone);
}