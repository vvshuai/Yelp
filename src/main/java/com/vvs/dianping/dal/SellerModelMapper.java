package com.vvs.dianping.dal;

import com.vvs.dianping.model.SellerModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    int insert(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    int insertSelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    SellerModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    int updateByPrimaryKeySelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Wed Nov 17 23:30:11 CST 2021
     */
    int updateByPrimaryKey(SellerModel record);

    List<SellerModel> selectAll();

    Integer countAllSeller();
}