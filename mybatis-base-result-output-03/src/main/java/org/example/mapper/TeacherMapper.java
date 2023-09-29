package org.example.mapper;

import org.example.pojo.Teacher;

public interface TeacherMapper {

    int insertTeacher(Teacher teacher);

    Teacher queryById(String tId);
}
