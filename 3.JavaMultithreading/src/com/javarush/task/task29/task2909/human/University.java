package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    String name;
    int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double grade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() >= grade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        if (students.size() == 0) {
            return null;
        }
        Student maxGradeStudent = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() > maxGradeStudent.getAverageGrade()) {
                maxGradeStudent = student;
            }
        }
        return maxGradeStudent;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public Student getStudentWithMinAverageGrade() {
        if (students.size() == 0) {
            return null;
        }
        Student minGradeStudent = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() < minGradeStudent.getAverageGrade()) {
                minGradeStudent = student;
            }
        }
        return minGradeStudent;
    }
}