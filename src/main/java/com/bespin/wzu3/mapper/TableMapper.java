package com.bespin.wzu3.mapper;

import com.bespin.wzu3.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TableMapper {

    TableEntity findOneById (int id);

    Integer randomInsert (TableEntity tableEntity);

    List<TableEntity> findListByEntity (TableEntity tableEntity);

    @Select("SELECT * FROM t_table")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name")
    })
    List<TableEntity> selectAll ();
}