package pl.sda.projectY.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.projectY.entity.Student;

import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer>{

    List<Student> findAllByOrderByRegNumDesc();

    Student findByLogin(String login);

    List<Student> findAllByMainInstructor_UserIdOrderByRegNumDesc(Integer mainInstructor_userId);
}
