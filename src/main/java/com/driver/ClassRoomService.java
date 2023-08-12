package com.driver;

import com.driver.Student;
import com.driver.Teacher;
import com.driver.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassRoomService
{
    @Autowired
    private ClassRoomRepository classRoomRepository=new ClassRoomRepository();

    public void addStudent(Student student)
    {
        Map<String,Student>studentMap=classRoomRepository.getStudentMap();
        studentMap.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        Map<String,Teacher>teacherMap=classRoomRepository.getTeacherMap();

        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student,String teacher)
    {
        Map<String,Student>studentMap=classRoomRepository.getStudentMap();
        Map<String,Teacher>teacherMap=classRoomRepository.getTeacherMap();
        if(!studentMap.containsKey(student) || !teacherMap.containsKey(teacher))return;
        Map<String, List<Student>>teacherStudentMap=classRoomRepository.getTeacherStudent();
        List<Student>list=teacherStudentMap.getOrDefault(teacher,new ArrayList<>());
        list.add(studentMap.get(student));
        teacherStudentMap.put(teacher,list);
    }

    public Student getStudentByName(String name){
        Map<String,Student>map=classRoomRepository.getStudentMap();
        return map.get(name);
    }

    public Teacher getTeacherByName(String name){
        Map<String,Teacher>teacherMap=classRoomRepository.getTeacherMap();
        return teacherMap.get(name);
    }
    public List<String> getStudentsByTeacherName( String teacher)
    {
        Map<String, List<Student>>teacherStudentMap=classRoomRepository.getTeacherStudent();
       List<Student>list= teacherStudentMap.getOrDefault(teacher,new ArrayList<>());
       List<String>ans=new ArrayList<>();
       for(Student student:list)
       {
           ans.add(student.getName());
       }
       return ans;
    }
    public List<String> getAllStudents()
    {
        Map<String, List<Student>>teacherStudentMap=classRoomRepository.getTeacherStudent();

        List<String>ans=new ArrayList<>();
        for(List<Student>list:teacherStudentMap.values()){
            for(Student student:list)ans.add(student.getName());
        }
        return ans;
    }

    public void deleteTeacherByName(String name){
        Map<String,Teacher>teacherMap=classRoomRepository.getTeacherMap();
        if(!teacherMap.containsKey(name))return;
        teacherMap.remove(name);
        Map<String,List<Student>>map=classRoomRepository.getTeacherStudent();
        map.remove(name);
    }

    public void deleteAllTeachers()
    {
        classRoomRepository.setTeacherMap(new HashMap<>());
        classRoomRepository.setTeacherStudent(new HashMap<>());
    }
}
