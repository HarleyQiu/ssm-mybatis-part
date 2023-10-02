package org.example.mapper;

import org.example.pojo.Teacher;

/**
* @author 21055
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2023-10-01 15:37:35
* @Entity org.example.pojo.Teacher
*/
public interface TeacherMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

}
