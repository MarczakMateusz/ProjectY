package pl.sda.projectY.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.projectY.dto.LessonDto;
import pl.sda.projectY.entity.Lesson;
import pl.sda.projectY.repository.InstructorRepository;
import pl.sda.projectY.repository.LessonRepository;
import pl.sda.projectY.repository.StudentRepository;

/**
 * author:
 * Mateusz
 * Marczak
 **/
@Service
public class LessonService {


    private LessonRepository lessonRepository;
    private InstructorRepository instructorRepository;
    private StudentRepository studentRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, InstructorRepository instructorRepository) {
        this.lessonRepository = lessonRepository;
        this.instructorRepository = instructorRepository;
    }

    public void add(LessonDto newLesson) {
        lessonRepository.save(getLesson(newLesson));

    }

    private Lesson getLesson(LessonDto newLesson) {
        Lesson lesson = new Lesson();
        lesson.setDate(newLesson.getDate());
        lesson.setStartHour(newLesson.getStartHour());
        lesson.setFinishHour(newLesson.getFinishHour());
        lesson.setInstructor(instructorRepository.getOne(newLesson.getInstructor()));
        lesson.setStudent(studentRepository.getOne(newLesson.getStudent()));
        return lesson;
    }


    public void deleteById(int lessonId) {
        lessonRepository.delete(lessonId);
    }
}
