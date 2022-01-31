package Lesson3.Collections.Set.Map;

import java.util.*;

public class CollectionSetMap {
    public static void main(String[] args) {
        //setExampl();

        //часто используемые структуры данных
        //набор пар ключ- тип значение
        //есть ключи типа стринг и значение инт
        //хранятся значения с уникальными ключами

        Map<String, Integer> map = new HashMap<>(); //много в исходниках информации

        //добавление элементов ключ и значение
        map.put("A", 1); //не будет записан
        map.put("B", 2);
        map.put("C", 3);
        //map.put("A", 4); //перезаписывает значение  у одинакового ключа
        System.out.println(map);

        //добавляет ключ и значение усли такого ключа не было или если ключ был но значение было налл
        map.putIfAbsent("A", 4);//запишется ("A", 1);

        //можно доставать значение по ключу
        System.out.println(map.get("B"));//распечатается 2
        //если попросить не сущ то выйдет нулл

        //ищет значение по ключу, если находит то выводит.,
        // если нет то возвращает нам наше поданное значение
        System.out.println(map.getOrDefault("A", 99)); //вернет 1
        System.out.println(map.getOrDefault("Ц", 99)); //вернет 99

        //изминения /расписать как мы измним все элементы
        map.replaceAll((k, v) -> v += 5);
        System.out.println(map);//увеличивается на 5 все элементы

        //можно дастать ключи
        Set<String> keys = map.keySet();
        System.out.println(keys); //[A, B, C]

        //получить коллекцию значений
        Collection<Integer> values = map.values();
        System.out.println(values);//[6, 7, 8]

        //позволяют пару ключ- значение, вычислить значение
        /*map.compute();
        map.computeIfAbsent();
        map.computeIfPresent();*/

        //позволяет по ключи, значения, и функции,
        // позволяет слияние значений по данному ключи по опред логике
        //map.merge();

        //все пары ключ значений пройти и распечатать
        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + "" + entry.getValue());//один результ

           // map.forEach((k, v) -> System.out.println(k + "" + v)); //один результ
        }
    }
    private static void setExampl(){
        //нельзя добавлять элементы по индексу, менять по индексу у них нет индексов
        //можно добавлять удалять, только уникальный, в определенном порядке
        //сортировать нельзя, нельзя циклом проитись
        //но можно

        //
        Set<String> set = new HashSet<>();


        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("A");    //нет дубликотов
        set.add(null);   //лучше не использовать
        System.out.println(set);

        //число элементов
        set.size();
        System.out.println(set.size());

        set.forEach(System.out::println);

        //работа с коробкой в которую добавленны коробки , распечатает все
        //что бы распечались бы только уникальные коробки нужно переопределить метод икволс
        //требует контракт
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1,1));
        boxes.add(new Box(2,2));
        boxes.add(new Box(1,1));



        //класс расширяющий HashSet, запоминает порядок добавления
        //забор ресурсов
        //Set<Box> boxes = new LinkedHashSet<>();

        //структура данных - дерево- красно-черное-бинарное
        //отсортированный сет//но если реализовать интерфейс implements Comparable в классе коробки
        // или компаратор для своих элементов
        //Set<Box> boxes = new TreeSet<>();


        System.out.println(boxes);

    }

    //класс коробка
    public static class Box {
        private int widht;
        private  int height;

        //конструктор коробка
        public Box(int widht, int height){
            this.widht = widht;
            this.height = height;
        }

        public int getWidht() {
            return widht;
        }

        public void setWidht(int widht) {
            this.widht = widht;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override //
        //сравниваем коробки по их параметрам, но этого мало нужно еще и hashCode()
        //если объекты одинаковы то код одинаков
        //hashCode() переопределять обязательно
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Box box = (Box) o;
            return widht == box.widht && height == box.height;
        }

       @ Override //сравнивает по hash
        public int hashCode() {
            return Objects.hash(widht, height);
        }

        @Override
        public String toString() {
            return "Box{" +
                    "widht=" + widht +
                    ", height=" + height +
                    '}';
        }

    }

}
