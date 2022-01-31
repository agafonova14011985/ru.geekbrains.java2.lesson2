package Lesson5.multithreading.home_work2.oop;

//класс - Трэк - беговая дорожка

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
