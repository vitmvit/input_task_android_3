package com.klever.task3.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Line {

    private Point firstPoint;
    private Point secondPoint;

    @Override
    public String toString() {
        return firstPoint + ", " + secondPoint + ' ';
    }
}
