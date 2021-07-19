package com.erp.item.mapper;

import com.erp.item.pojo.SpecParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SpecParamMapper extends Mapper<SpecParam> {
    /**
     * 删除规格
     * @param id 规格表id
     * @return
     */
    @Delete("DELETE FROM tb_spec_param where id=(#{id})")
    void deleteParams(@Param("id")  Long id);
}
