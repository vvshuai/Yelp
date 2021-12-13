package com.vvs.dianping.dal;

import com.vvs.dianping.model.ShopModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShopModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    int insert(ShopModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    int insertSelective(ShopModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    ShopModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    int updateByPrimaryKeySelective(ShopModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop
     *
     * @mbg.generated Sun Nov 28 00:28:47 CST 2021
     */
    int updateByPrimaryKey(ShopModel record);

    List<ShopModel> selectAll();

    Integer countAllShop();

    List<ShopModel> recommend(@Param("longitude") BigDecimal longitude,
                              @Param("latitude") BigDecimal latitude);

    List<ShopModel> search(@Param("longitude") BigDecimal longitude,
                           @Param("latitude") BigDecimal latitude,
                           @Param("keyword") String keyword);
}