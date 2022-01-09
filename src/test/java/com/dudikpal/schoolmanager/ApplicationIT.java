package com.dudikpal.schoolmanager;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.dtos.StudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import com.dudikpal.schoolmanager.entities.Student;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql(statements = "delete from students")
public class ApplicationIT {

    static final String URL = "/api/students";

    @Autowired
    TestRestTemplate template;


    @BeforeEach
    void setUp() {
        createStudent("first1", "last1", 22);
        createStudent("first2", "last2", 23);
        createStudent("first3", "last3", 24);
        createStudent("first4", "last4", 22);
        createStudent("first5", "last5", 23);
    }


    @Test
    void create_and_read_student_test() {
        int originalSize = getStudents().size();
        createStudent("first6", "last6", 44);
        originalSize++;
        int newSize = getStudents().size();

        assertEquals(originalSize, newSize);
    }


    @Test
    void update_student_test() {
        long id = createStudent("first6", "last6", 25).getId();
        String filteredURL = URL + "/" + id;
        StudentDTO student = template.getForObject(filteredURL, StudentDTO.class);

        String originalLastName = student.getLastName();

        UpdateStudentCommand command = new UpdateStudentCommand();
        command.setLastName("newLastName");
        template.put(filteredURL, command);

        StudentDTO updatedStudent = template.getForObject(filteredURL, StudentDTO.class);

        assertNotEquals(originalLastName, updatedStudent.getLastName());
        assertEquals("newLastName", updatedStudent.getLastName());
    }


    @Test
    void delete_student_test() {
        List<StudentDTO> students = getStudents();

        int idForDelete = students.get(0).getId();
        String filteredURL = URL + "/" + idForDelete;
        int sizeBeforeDelete = students.size();

        template.delete(filteredURL);

        int sizeAfterDelete = getStudents().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }


    @Test
    void filtering_student_by_name_test() {
        String filterName = "namePart=st3";
        List<StudentDTO> students = template.exchange(URL + "?" + filterName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertEquals(24, students.get(0).getAge());
    }


    @Test
    void filtering_student_by_age_test() {
        String filterAge = "age=22";
        List<StudentDTO> students = template.exchange(URL + "?" + filterAge,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertEquals(2, students.size());
    }


    @Test
    void filtering_student_by_name_and_age_test() {
        String filterName = "namePart=st";
        String filterAge = "age=24";
        String urlWithFilters = URL + "?" + filterName + "&" + filterAge;
        List<StudentDTO> students = template.exchange(urlWithFilters,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertEquals(24, students.get(0).getAge());
    }


    private Student createStudent(String firstName, String lastName, int age) {
        return template.postForObject(URL,
                new CreateStudentCommand(
                        firstName, lastName, age
                ), Student.class);
    }


    private List<StudentDTO> getStudents() {
        return template.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();
    }
}
