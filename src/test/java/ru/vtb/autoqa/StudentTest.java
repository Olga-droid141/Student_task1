package ru.vtb.autoqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StudentTest {

    @Test
    public void addGradePositiveTest() {
        Student student = new Student("TestStudent#1");
        student.addGrade(2);
        student.getGrades().add(5);
        Assertions.assertEquals(Collections.singletonList(2), student.getGrades());
    }

    @Test
    public void addManyGradesPositiveTest() {
        Student student = new Student("TestStudent#2");
        student.addGrade(2);
        student.addGrade(3);
        student.addGrade(4);
        student.addGrade(5);
        Assertions.assertEquals(4, student.getGrades().size());
        List<Integer> lst = List.of(2, 3, 4, 5);
        Assertions.assertEquals(lst, student.getGrades());
    }

    @RepeatedTest(value = 4, name = "добавление корректной оценки")
    public void addEachGradePositiveTest(RepetitionInfo repetitionInfo) {
        Student stud = new Student("TestStudent#3");
        stud.addGrade(repetitionInfo.getCurrentRepetition() + 1);
        Assertions.assertEquals(stud.getGrades().get(0),
                repetitionInfo.getCurrentRepetition() + 1);
    }

    @Test
    public void compareStudentsPosTest() {
        Student student1 = new Student("TestStudent#4");
        Student student2 = student1;
        Assertions.assertTrue(student1.equals(student2));
    }

    @Test
    public void compareNullStudentTest() {
        Student student = new Student("TestStudent#5");
        Assertions.assertFalse(student.equals(null));
    }

    @Test
    public void compareDiffStudentTest() {
        Student student1 = new Student("TestStudent#6.1");
        Student student2 = new Student("TestStudent#6.2");
        Assertions.assertFalse(student2.equals(student1));
    }

    @Test
    public void compareDiffObjsTest() {
        Student student1 = new Student("TestStudent#6.3");
        Assertions.assertFalse(student1.equals(new Schoolboy("name")));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7})
    public void addGradeNegativeTest(int grades) {
        Student student = new Student("TestStudent#7");
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(grades));
    }

    @Test
    public void studentNameTest() {
        byte[] array = new byte[6];
        new Random().nextBytes(array);
        String name = new String(array, StandardCharsets.UTF_8);
        Student student = new Student(name);
        Assertions.assertEquals(name, student.getName());
    }

    @Test
    public void studentToStrTest() {
        String studentStr = "Student{" + "name=TestStudent#8, marks=[2, 3, 4, 5]}";
        Student student = new Student("TestStudent#8");
        student.addGrade(2);
        student.addGrade(3);
        student.addGrade(4);
        student.addGrade(5);
        Assertions.assertEquals(studentStr, student.toString());
    }

    @Test
    public void hashCodeNamesTest() {
        Student student1 = new Student("TestStudent#9");
        student1.addGrade(3);
        Student student2 = new Student("TestStudent#10");
        student2.addGrade(3);
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    public void hashCodeGradesTest() {
        Student student1 = new Student("TestStudent#11");
        student1.addGrade(2);
        Student student2 = new Student("TestStudent#11");
        student2.addGrade(3);
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    public void hashCodeDiffTest() {
        Student student1 = new Student("TestStudent#13");
        student1.addGrade(5);
        Student student2 = new Student("TestStudent#13");
        student1.addGrade(5);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
    }
}
