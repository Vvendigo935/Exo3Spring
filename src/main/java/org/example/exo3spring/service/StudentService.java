package org.example.exo3spring.service;

import org.example.exo3spring.entity.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {

    private final Map<UUID, Student> students;

public StudentService() {
    students = new HashMap<>();
//     if we want some students already in the list
    Student student1 = Student.builder().id(UUID.randomUUID()).name("Snow").surname("Jon").age(20).email("youknow@nothing.com").build();
    Student student2 = Student.builder().id(UUID.randomUUID()).name("Sarkozy").surname("Nicolas").age(70).email("magouille@UMP.fr").build();
    Student student3 = Student.builder().id(UUID.randomUUID()).name("Kvaratchkelia").surname("Khvicha").age(25).email("georgien@fou.ge").build();
    Student student4 = Student.builder().id(UUID.randomUUID()).name("Al-Khelaifi").surname("Nasser").age(45).email("lathune@qatar.qa").build();
    Student student5 = Student.builder().id(UUID.randomUUID()).name("John").surname("Doe").age(15).email("truc@bidule.fr").build();

//    students.put(student.getId(), student);

    this.students.put(student1.getId(), student1);
    this.students.put(student2.getId(), student2);
    this.students.put(student3.getId(), student3);
    this.students.put(student4.getId(), student4);
    this.students.put(student5.getId(), student5);



}
public StudentService addStudent(Student student) {
    this.students.put(student.getId(), student);
    return this;
}

//public addStudent(Student student){
//    students = new HashMap<>();
//    students.put(student.getId(), student);
//}

// list all, see if we can show only name and surname in the HTML
    public List<Student> getAllStudents(){
        return this.students.values().stream().toList();
    }
// get a student with his ID
    public Student getStudentByID(UUID id){
        return this.students.get(id);
    }
// get a student with the name
public Student getStudentByName(String name){
    return this.students.values().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
}






}
