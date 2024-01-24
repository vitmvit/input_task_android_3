# Задача 3. Проверка и оптимизация резов стекла
Имеется станок ЧПУ для раскроя листов стекла. На него подаются прямоугольные листы стекла, и режущий инструмент станка (далее – резец), осуществляет процесс резки. Резец станка имеет 2 режима перемещения: “холостой ход”, когда он перемещается в поднятом состоянии; и “рабочий ход”, когда он перемещается и непосредственно режет стекло. Программирование работы станка осуществляется заданием списка отрезков, по которым происходит перемещение резца. Список отрезков включает в себя непосредственно входной набор отрезков и некоторые стороны фигур из входного набора фигур.
#### Требуется реализовать алгоритм с функциями:
- преобразования сторон фигур в отрезки с координатами в СКЛ;
- поиска и исключения из списка отрезков, которые накладываются на другие отрезки;
- оптимизации списка отрезков для уменьшения расстояния “холостого хода” резца.
#### Алгоритм должен принимать в качестве входных параметров:
- Список фигур – приведен в разделе “Входные данные для задачи 3”. Фигура описывается 4-мя точками на плоскости с координатами (X, Y), образующими четырёхугольник. Координаты точек фигуры даны в системе координат фигуры (СКФ). Положение фигуры на листе задаётся координатами начала системы координат фигуры (СКФ) в системе координат листа (СКЛ). Начало системы координат листа (СКЛ) совпадает с нижним левым углом листа.
- Список отрезков на плоскости листа приведен в разделе “Входные данные для задачи 3”. Отрезки заданы двумя точками, точка определяется координатами X, Y (СКЛ).
#### Примечание: все координаты целочисленные, все отрезки являются прямолинейными.
#### Условия:
Список отрезков передается на станок резки стекла. Для успешного и экономного выполнения операции резки стекла нужно соблюсти следующие условия:
- стороны фигур должны быть преобразованы в отрезки и добавлены в общий список отрезков;
- станок не должен дважды резать по одной линии. То есть исключить из списка отрезки, которые полностью накладываются на другие отрезки;
- необходимо сократить расстояние “холостого хода” резца. То есть оставшиеся отрезки должны быть расположены в списке таким образом, чтобы начало следующего отрезка являлось ближайшей точкой к концу текущего отрезка. Для этого можно менять положение отрезков в списке и менять местами начальную и конечную точки подходящего отрезка.
#### Пояснение
Резец начинает движение из начала координат СКЛ (точка (0,0) совпадает с левым нижним углом листа) и двигается по прямой к начальной точке первого отрезка из списка в поднятом состоянии (“холостой ход”). В этой точке резец опускается и, двигаясь к конечной точке отрезка, прорезает стекло. Далее резец поднимается и перемещается по прямой к начальной точке следующего отрезка и т. д.
#### Результат работы алгоритма:
- Список отрезков, удовлетворяющий условиям и заданный координатами X1, Y1, X2, Y2 в СКЛ.
#### Оформление результата выполнения задачи:
- Листинг алгоритма в одном TXT-файле.
- TXT-файл с результатом работы алгоритма: только список отрезков, удовлетворяющий условиям. Каждая строка в файле – отдельный отрезок, заданный координатами X1, Y1, X2, Y2 в СКЛ.
### Входные данные для задачи 3
#### Список отрезков:
#### Каждая строка отдельный отрезок, заданный координатами X1, Y1, X2, Y2 в СКЛ:
```text
15, 0, 15, 3210
0, 15, 6000, 15
1500, 0, 1500, 3210
15, 1015, 1500, 1015
15, 2015, 1500, 2015
15, 3015, 1500, 3015
2550, 0, 2550, 3210
1500, 1415, 2550, 1415
1500, 2815, 2550, 2815
3991, 0, 3991, 3210
2550, 515, 3991, 515
2550, 1015, 3991, 1015
2550, 1515, 3991, 1515
2550, 2015, 3991, 2015
2550, 2765, 3991, 2765
3250, 2015, 3250, 2765
4789, 0, 4789, 3210
3991, 1515, 4789, 1515
3991, 3015, 4789, 3015
5843, 0, 5843, 3210
4789, 1123, 5843, 1123
5316, 15, 5316, 1123
```
#### Список фигур. Каждая фигура описана 4-мя точками (X, Y) в СКФ
###### Фигура 1:
```text
4 точки фигуры:
0, 0
1470, 0
1200, 1000
0, 1000
Положение фигуры в СКЛ:
15, 15
```

###### Фигура 2:
```text
4 точки фигуры:
0, 0
1470, 0
1200, 1000
0, 1000
Положение фигуры в СКЛ:
15, 1015
```

###### Фигура 3:
```text
4 точки фигуры:
15, 0
1485, 0
1485, 1000
285, 1000
Положение фигуры в СКЛ:
15, 2015
```

###### Фигура 4:
```text
4 точки фигуры:
0, 0
798, 0
798, 1485
0, 1000
Положение фигуры в СКЛ:
3991, 15
```

###### Фигура 5:
```text
4 точки фигуры:
0, 0
798, 0
798, 1200
0, 1485
Положение фигуры в СКЛ:
3991, 1515
```

###### Фигура 6:
```text
4 точки фигуры:
15, 0
685, 0
600, 735
150, 735
Положение фигуры в СКЛ:
2550, 2015
```

##### Пример к задаче 3.
##### Входные данные:
###### Список отрезков:
```text
500, 0, 500, 3210
0, 15, 6000, 15
2000, 0, 2000, 3210
500, 1515, 2000, 1515
```

###### Фигура:
```text
4 точки фигуры:
0, 0
1500, 0
1500, 1000
0, 1485
Положение фигуры в СКЛ:
500, 15
```

##### Схематическое изображение чертежа листа, отрезков и фигуры для данного примера:
Размер листа: ширина = 6000 мм, высота = 3210 мм.

```text
0, 15, 6000, 15
2000, 0, 2000, 3210
500, 3210, 500, 0
500, 1500, 2000, 1015
2000, 1515, 500, 1515
```
