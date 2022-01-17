package Lesson2;

public class Work {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
    //исключения пробросятся и вообще не будут обрабатываться, не стоит делать
    // throws Exeption

            //
            String[][] array1 = {
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"}
            };
    String[][] array2 = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };
    String[][] array3 = {
            {"0", "0", "0", "0"},
            {"0", "x", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

        try {
        System.out.println("Первый массив 4*4, все ячейки заполнены 1");
        System.out.println("Сумма всех элементов массива = " + Converter.strConverter(array1) + ".\n");
    } catch (MyException e) {
        e.getMessage();
    }

        try {
        System.out.println("Второй массив не 4*4, все ячейки заполнены 0");
        System.out.println("Сумма всех элементов массива = "
                + Converter.strConverter(array2) + ".\n");
    } catch (MyException e) {
        System.out.println(e.getMessage());
    }

        try {
        System.out.println("Третий массив 4*4. элементы 0 и x");
        System.out.println("Сумма всех элементов массива = "
                + Converter.strConverter(array3) + ".\n");
    } catch (MyException e) {
        System.out.println(e.getMessage());
    }



    }}



