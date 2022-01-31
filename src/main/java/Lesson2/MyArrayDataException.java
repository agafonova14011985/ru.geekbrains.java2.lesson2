package Lesson2;
//
public class MyArrayDataException extends MyException {
    MyArrayDataException(int x, int y) {
        super(String.format("не преобразовались данные в ячейке [%d, %d]\n", x, y));};
    }

