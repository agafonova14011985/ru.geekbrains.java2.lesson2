package Lesson5.multithreading.run;
//Задание
// 1, Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
// Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//2, Создайте два класса: беговая дорожка и стена, при прохождении через которые,
// участники должны выполнять соответствующие действия (бежать или прыгать),
// результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//3, Создайте два массива: с участниками и препятствиями,
// и заставьте всех участников пройти этот набор препятствий.
//*4, У препятствий есть длина (для дорожки) или высота (для стены),
//а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

import Lesson1.oop.Cat;
import Lesson1.oop.Gamer;
import Lesson1.oop.Human;
import Lesson1.oop.Robot;
import Lesson1.oop.Track;
import Lesson1.oop.Trap;
import Lesson1.oop.Wall;
//не многопоточность все выполняется последовательно
public class WorkInterface {
    public static void main(String[] args) throws InterruptedException{

        //Массив с игроками
        Lesson1.oop.Gamer[] gamers = {
                new Cat("Рыжий", 1, 2),
                new Robot("Walli", 10000, 2),
                new Human("Иван", 20, 4)
        };

        // Массив с испытаниями: 1 стена 2 м., 2
        Lesson1.oop.Trap[] traps = {
                new Lesson1.oop.Wall(2),
                new Track(10000),
                new Wall(3),};

        //цикл с вложенным циклом/
        for (Gamer g : gamers) {
            //берется каждый участник и вызывается метод : traps
            for (Trap t : traps) {
                //вызавается метод overcome(g) у премятствий
                if (!t.overcome(g)) break;
                //проверка если участника зовут --- то мы подождем в этом месте
                if (g.getName().equals("Walli")){
                    Thread.sleep(3000); //чтоб работало -sleep-нужно
                    // добавить исключения в мэйн throws InterruptedException
                    //выполнение четко последовательно не многопоточность!
                }

            }
        }System.out.printf("испытание завершилось");
    }

}



