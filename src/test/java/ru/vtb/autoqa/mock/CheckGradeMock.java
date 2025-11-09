package ru.vtb.autoqa.mock;

import ru.vtb.autoqa.CheckGrade;

import java.util.List;

public class CheckGradeMock extends CheckGrade {

    @Override
    public boolean addGrade(int grade) {
        return (grade >= 2) & (grade <= 5);
    }

    @Override
    public int raiting(List<Integer> grades) {
        return grades.stream().mapToInt(x -> x).sum();
    }
}
