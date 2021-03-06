package pl.sda.projectY.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.projectY.dto.StudentDto;
import pl.sda.projectY.dto.StudentShortDto;
import pl.sda.projectY.entity.Student;
import pl.sda.projectY.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * author:
 * Mateusz
 * Marczak
 **/

@Service
@Transactional(readOnly = true)
public class StudentFinder {

    private final StudentRepository studentRepository;
    private final InstructorFinder instructorFinder;

    @Autowired
    public StudentFinder(StudentRepository studentRepository, InstructorFinder instructorFinder) {
        this.studentRepository = studentRepository;
        this.instructorFinder = instructorFinder;
    }

    public List<StudentDto> findAll() {
        List<Student>studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        studentList.forEach(student -> studentDtoList.add(getStudentDto(student)));
        return studentDtoList;
    }

    public List<StudentShortDto>findAllShort(){
        List<Student>studentList = studentRepository.findAllByOrderByRegNumDesc();
        List<StudentShortDto> studentDtoList = new ArrayList<>();
        studentList.forEach(student -> studentDtoList.add(getStudentShortDto(student)));
        return studentDtoList;
    }
    StudentShortDto getStudentShortDto(Student student) {
        StudentShortDto studentShortDto = new StudentShortDto();
        studentShortDto.setUserId(student.getUserId());
        studentShortDto.setFullName(student.getName()+" "+student.getSurname());
        studentShortDto.setRegNum(student.getRegNum());
        studentShortDto.setMainInstructor(instructorFinder.findById(student.getMainInstructor().getUserId()));
        return studentShortDto;
    }

    public StudentDto findById(int id) {
        Student student =  studentRepository.findOne(id);
        return getStudentDto(student);

    }


    private StudentDto getStudentDto(Student student) {
        StudentDto newStudent = new StudentDto();

        newStudent.setUserId(student.getUserId());
        newStudent.setLogin(student.getLogin());
        //newStudent.setPassword(student.getPassword());
        newStudent.setName(student.getName());
        newStudent.setSurname(student.getSurname());
        newStudent.setTelephone(student.getTelephone());

        newStudent.setCity(student.getCity());
        newStudent.setStreet(student.getStreet());
        newStudent.setPostCode(student.getPostCode());

        newStudent.setIdNumber(student.getIdNumber());
        newStudent.setPesel(student.getPesel());
        newStudent.setPkNum(student.getPkNum());
        newStudent.setRegNum(student.getRegNum());

        if(student.getMainInstructor()!=null) {
            newStudent.setMainInstructor(student.getMainInstructor().getUserId());
        }
        newStudent.setStartDate(student.getStartDate());

       /* List<Lesson> lessonList = new ArrayList<>(student.getLessonList());
        List<Payment> paymentList = new ArrayList<>(student.getPaymentList());

        newStudent.setLessonList(lessonList);
        newStudent.setPaymentList(paymentList);*/
        return newStudent;
    }

    public StudentDto findByLogin(String login) {
        Student student = studentRepository.findByLogin(login);
        return getStudentDto(student);
    }


    public StudentDto getUserDetails() {
        Optional<UserDetails> userDetails =
                Optional.ofNullable(SecurityContextHolder.getContext())
                        .map(SecurityContext::getAuthentication)
                        .map(Authentication::getPrincipal)
                        .map(ud -> (UserDetails) ud);

        String login = userDetails.get().getUsername();
        return findByLogin(login);
    }

    public List<StudentDto> findAllByMainInstructor_userIdOrderByName(int instructor) {
        List<StudentDto> studentDto = new ArrayList<>();
        studentRepository.findAllByMainInstructor_UserIdOrderByRegNumDesc(instructor).forEach(student -> studentDto.add(getStudentDto(student)));
        return studentDto;
    }
}
