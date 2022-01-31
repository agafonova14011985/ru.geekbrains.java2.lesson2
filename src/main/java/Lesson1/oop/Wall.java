package Lesson1.oop;

//класс - стена

public class Wall implements Trap {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Gamer gamer) {
        if (gamer.jump(this.height)) {
            System.out.printf("%s %s Молодец! он перепрыгнул стену, высотой  %d м.\n",
                    gamer.getClass().getSimpleName(), gamer.getName(), height);
            return true;
        }
        System.out.printf("%s  %s Увы...не перепрыгнул стену высотой %d м.\n",
                gamer.getClass().getSimpleName(), gamer.getName(), height);
        return false;
    }
}
