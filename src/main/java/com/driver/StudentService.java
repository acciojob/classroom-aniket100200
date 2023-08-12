package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository=new StudentRepository();

    public void addStudent(Student student)
    {
        Map<String,Student>studentMap= studentRepository.getStudentMap();
        studentMap.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        Map<String,Teacher>teacherMap= studentRepository.getTeacherMap();

        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student,String teacher)
    {
        Map<String,Student>studentMap= studentRepository.getStudentMap();
        Map<String,Teacher>teacherMap= studentRepository.getTeacherMap();
        if(!studentMap.containsKey(student) || !teacherMap.containsKey(teacher))return;
        Map<String, List<Student>>teacherStudentMap= studentRepository.getTeacherStudent();
        List<Student>list=teacherStudentMap.getOrDefault(teacher,new ArrayList<>());
        for(Student s:list)if(s.getName().equalsIgnoreCase(student))return;
        list.add(studentMap.get(student));
        teacherStudentMap.put(teacher,list);
        Teacher teacher1= teacherMap.get(teacher);
       teacher1.setNumberOfStudents(teacher1.getNumberOfStudents()+1);
    }

    public Student getStudentByName(String name){
        Map<String,Student>map= studentRepository.getStudentMap();
        return map.get(name);
    }

    public Teacher getTeacherByName(String name)
    {
        Map<String,Teacher>teacherMap= studentRepository.getTeacherMap();
        return teacherMap.get(name);
    }
    public List<String> getStudentsByTeacherName( String teacher)
    {
        Map<String, List<Student>>teacherStudentMap= studentRepository.getTeacherStudent();
        if(!teacherStudentMap.containsKey(teacher))return new ArrayList<>();
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
        Map<String, List<Student>>teacherStudentMap= studentRepository.getTeacherStudent();

        List<String>ans=new ArrayList<>();
       for (String key:teacherStudentMap.keySet()){
           for(Student student:teacherStudentMap.getOrDefault(key,new ArrayList<>())){
               ans.add(student.getName());
           }
       }
        return ans;
    }

    public void deleteTeacherByName(String name)
    {
        Map<String,Teacher>teacherMap= studentRepository.getTeacherMap();
        if(!teacherMap.containsKey(name))return;
        teacherMap.remove(name);
        Map<String,List<Student>>map= studentRepository.getTeacherStudent();
        List<Student>list=map.get(name);
        map.remove(name);
        Map<String,Student>map1= studentRepository.getStudentMap();
        for(Student student:list)
        {
            if(map1.containsKey(student.getName()))map1.remove(student.getName());
        }

    }

    public void deleteAllTeachers()
    {
        Map<String,Teacher>teacherMap= studentRepository.getTeacherMap();
        for(String teacher:teacherMap.keySet())teacherMap.remove(teacher);

        Map<String ,Student>studentMap= studentRepository.getStudentMap();
        for(String st:studentMap.keySet())studentMap.remove(st);
    }
}
