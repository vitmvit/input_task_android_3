package com.klever.task3.service;

import com.klever.task3.model.Figure;
import com.klever.task3.model.Line;
import com.klever.task3.model.Point;

import java.awt.geom.Line2D;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class FigureService {

    public Figure offset(Figure figure) {

        int offsetX = Math.abs(figure.getFirstPoint().getX() - figure.getOffset().getX());
        int offsetY = Math.abs(figure.getFirstPoint().getY() - figure.getOffset().getY());

        Point firstPoint = figure.getFirstPoint();
        firstPoint.move(Math.abs((firstPoint.getX() + offsetX)), Math.abs(firstPoint.getY() + offsetY));

        Point secondPoint = figure.getSecondPoint();
        secondPoint.move(Math.abs((secondPoint.getX() + offsetX)), Math.abs(secondPoint.getY() + offsetY));

        Point thirdPoint = figure.getThirdPoint();
        thirdPoint.move(Math.abs((thirdPoint.getX() + offsetX)), Math.abs(thirdPoint.getY() + offsetY));

        Point fourPoint = figure.getFourPoint();
        fourPoint.move(Math.abs((fourPoint.getX() + offsetX)), Math.abs(fourPoint.getY() + offsetY));

        return figure;
    }

    public List<Line> compactLines(List<Line> list) {
        for (int i = 0; i < list.size(); i++) {
            Line line1 = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                Line line2 = list.get(j);
                if (Line2D.linesIntersect(line1.getFirstPoint().getX(), line1.getFirstPoint().getY(), line1.getSecondPoint().getX(), line1.getSecondPoint().getY(), line2.getFirstPoint().getX(), line2.getFirstPoint().getY(), line2.getSecondPoint().getX(), line2.getSecondPoint().getY())) {
                    if ((line1.getFirstPoint().getX() == line2.getFirstPoint().getX() && line1.getSecondPoint().getX() == line2.getSecondPoint().getX()
                            && line2.getFirstPoint().getX() == line1.getSecondPoint().getX() && line2.getSecondPoint().getX() == line1.getFirstPoint().getX()
                            && (line1.getFirstPoint().getY() != line2.getFirstPoint().getY() && line1.getSecondPoint().getY() != line2.getSecondPoint().getY()))) {
                        Point min = new Point(line1.getFirstPoint().getX(), Math.min(line1.getFirstPoint().getY(), Math.min(line2.getFirstPoint().getY(), Math.min(line1.getSecondPoint().getY(), line2.getSecondPoint().getY()))));
                        Point max = new Point(line1.getFirstPoint().getX(), Math.max(line1.getFirstPoint().getY(), Math.max(line2.getFirstPoint().getY(), Math.max(line1.getSecondPoint().getY(), line2.getSecondPoint().getY()))));
                        Line result = new Line(min, max);
                        list.add(result);
                        list.remove(line1);
                        list.remove(line2);

                    }
                    if ((line1.getFirstPoint().getY() == line2.getFirstPoint().getY() && line1.getSecondPoint().getY() == line2.getSecondPoint().getY()
                            && line2.getFirstPoint().getY() == line1.getSecondPoint().getY() && line2.getSecondPoint().getY() == line1.getFirstPoint().getY()
                            && (line1.getFirstPoint().getX() != line2.getFirstPoint().getX() && line1.getSecondPoint().getX() != line2.getSecondPoint().getX()))) {
                        Point min = new Point(Math.min(line1.getFirstPoint().getX(), Math.min(line2.getFirstPoint().getX(), Math.min(line1.getSecondPoint().getX(), line2.getSecondPoint().getX()))), line1.getFirstPoint().getY());
                        Point max = new Point(Math.max(line1.getFirstPoint().getX(), Math.max(line2.getFirstPoint().getX(), Math.max(line1.getSecondPoint().getX(), line2.getSecondPoint().getX()))), line1.getFirstPoint().getY());
                        Line result = new Line(min, max);
                        list.add(result);
                        list.remove(line1);
                        list.remove(line2);
                    }
                }
            }
        }
        list = list.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        return list;
    }

    public List<Line> sortLines(List<Line> line) {
        Point currentPoint = new Point(0, 0);
        Line minLine;
        int minLineIndex = 0;
        double minDistance;
        Point p;

        for (int i = 0; i < line.size(); ++i) {
            minLine = line.get(i);
            minLineIndex = i;
            minDistance = sqrt(pow(minLine.getFirstPoint().getX() - currentPoint.getX(), 2) + pow(minLine.getFirstPoint().getY() - currentPoint.getY(), 2));

            for (int j = i; j < line.size(); ++j) {
                p = line.get(j).getFirstPoint();
                if (sqrt(pow(p.getX() - currentPoint.getX(), 2) + pow(p.getY() - currentPoint.getY(), 2)) < minDistance) {
                    minDistance = sqrt(pow(p.getX() - currentPoint.getX(), 2) + pow(p.getY() - currentPoint.getY(), 2));
                    minLine = line.get(j);
                    minLineIndex = j;
                }

                p = line.get(j).getSecondPoint();
                if (sqrt(pow(p.getX() - currentPoint.getX(), 2) + pow(p.getY() - currentPoint.getY(), 2)) < minDistance) {
                    minDistance = sqrt(pow(p.getX() - currentPoint.getX(), 2) + pow(p.getY() - currentPoint.getY(), 2));
                    minLine = new Line(line.get(j).getSecondPoint(), line.get(j).getFirstPoint());
                    minLineIndex = j;
                }
            }

            line.set(minLineIndex, line.get(i));
            line.set(i, minLine);
            currentPoint = minLine.getSecondPoint();
        }
        return line;
    }
}
