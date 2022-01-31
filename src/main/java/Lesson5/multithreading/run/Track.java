package Lesson5.multithreading.run;

//класс - Трэк - беговая дорожка

import Lesson1.oop.Gamer;
import Lesson1.oop.Trap;

public class Track implements Trap {
    private final int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(Gamer gamer) {
        if (gamer.run(this.length)) {
            System.out.printf(" %s  %s Молодец! он пробежал %s км. \n",
                    gamer.getClass().getSimpleName(),
                    gamer.getName(), length);
            return true;
        }
        System.out.printf(" %s %s Увы... не смог пробежать  %d км.\n",
                gamer.getClass().getSimpleName(), gamer.getName(), length);
        return false;
    }
}
