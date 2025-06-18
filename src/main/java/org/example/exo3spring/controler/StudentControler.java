package org.example.exo3spring.controler;

import org.example.exo3spring.entity.Student;
import org.example.exo3spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class StudentControler {

    private final StudentService studentService;



    public StudentControler(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String homePage(){
        return "home";
    }

    @GetMapping("/list") // listing of all
    public String goTolist(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "list";
    }

    @GetMapping("/detail/{id}") // student by ID
    public String goDetailPage(@PathVariable("id") UUID id, Model model){
Student student = studentService.getStudentByID(id);
        model.addAttribute("student",student);
        return "detail";
    }
    @GetMapping("/searching")
    public String searchStudent(Model model){
        model.addAttribute("student", new Student());
        return "form/search";
    }

    @GetMapping("/searchname")
    public String goSearchName(@RequestParam(value = "name",required = false) String name, Model model){
        Student student = studentService.getStudentByName(name);

        if (student != null){
            model.addAttribute("student",student);
            return "detail";
        }else {

            return "redirect:/list";
        }
    }
//    @GetMapping("/form")
//    public String addContactFormOne(){
//        return "form/addStudent";
//    }

    @GetMapping("/form")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());
        return "form/addStudent";
    }

//    @GetMapping("/add")
//    public String recupFormGet(@RequestParam("name") String name,
//                               @RequestParam("surname") String surname,
//                               @RequestParam("age")int age,
//                               @RequestParam("email")String email){
//        Student student = Student.builder().id(UUID.randomUUID()).name(name).surname(surname).age(age).email(email).build();
////studentService.save(student);
//        return "redirect:/";
//    }

@PostMapping("/add")
public String recupFormPost(@RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("age") int age,
                            @RequestParam("email") String email) {
    Student student = Student.builder()
            .id(UUID.randomUUID())
            .name(name)
            .surname(surname)
            .age(age)
            .email(email)
            .build();
    studentService.addStudent(student);
    return "redirect:/";
}







}
