package com.erp.item.mapper;

import com.erp.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

        /**
         * 新增商品分类和品牌中间表数据
         * @param cid 商品分类id
         * @param bid 品牌id
         * @return 影响行数
         */
        @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
        int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

        /**
     * 删除商品分类和品牌中间表数据
     * @param bid 品牌id
     * @return 影响行数
     */
    @Delete("DELETE FROM tb_category_brand where brand_id=(#{bid})")
    int deleteCategoryBrand( @Param("bid") Long bid);
    @Delete("DELETE FROM tb_brand where id=(#{bid})")
    int deletebid(@Param("bid") Long bid);
    /**
     * 根据分类查询品牌
     * @param cid
     * @return
     */
    @Select("SELECT b.* FROM tb_brand b LEFT JOIN tb_category_brand cb ON b.id = cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> queryByCategoryId(Long cid);
}

