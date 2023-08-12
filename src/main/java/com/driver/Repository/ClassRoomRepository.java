package com.driver.Repository;

import com.driver.Models.Student;
import com.driver.Models.Teacher;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClassRoomRepository
{
    private Map<String, Student> studentMap=new HashMap<>();
    private Map<String, Teacher>teacherMap=new HashMap<>();
    private Map<String, List<Student>> teacherStudent=new HashMap<>();

    public Map<String, Student> getStudentMap()
    {
        return studentMap;
    }

    public Map<String, Teacher> getTeacherMap()
    {
        return teacherMap;
    }

    public Map<String, List<Student>> getTeacherStudent() {
        return teacherStudent;
    }

    public void setStudentMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }

    public void setTeacherStudent(Map<String, List<Student>> teacherStudent) {
        this.teacherStudent = teacherStudent;
    }
}
