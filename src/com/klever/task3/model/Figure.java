package com.klever.task3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Figure {

    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;
    private Point fourPoint;
    private Point offset;

    public Line getFirstLine() {
        return new Line(firstPoint, secondPoint);
    }

    public Line getSecondLine() {
        return new Line(secondPoint, thirdPoint);
    }

    public Line getThirdLine() {
        return new Line(thirdPoint, fourPoint);
    }

    public Line getFourLine() {
        return new Line(fourPoint, firstPoint);
    }

    public List<Line> toLines() {
        return List.of(getFirstLine(), getSecondLine(), getThirdLine(), getFourLine());
    }
}
