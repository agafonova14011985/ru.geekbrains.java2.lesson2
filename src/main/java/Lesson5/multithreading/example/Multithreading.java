package Lesson5.multithreading.example;

//многозадачность выполнение нескольких задачь параллельно
//у потоков внутри процесса память общая, в отлчии от процессов
public class Multithreading {

//9. отдельные синхронизирующие блоки
    private static final Object mon1 = new Object();
    private static final Object mon2 = new Object();


    //8.
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;


    public static void main(String[] args) {

        // thredCreation();
        //thredStop();

        //8.
        Thread t1 = new Thread(Multithreading::increment);//поток
        Thread t2 = new Thread(Multithreading::increment);//поток
        Thread t3 = new Thread(Multithreading::increment);//поток

        t1.start();//запускаем потоки
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        System.out.printf("A = %d, B = %d, C = %b\n", a, b, c);

    }

    //9.потоки выполняют  данный метод и до блока синхронайз выполнение параллельно конкурентно
    //достигнуи блока synchronized (mon1) , один поток его занемает
    //все остальные ожидают освобождения
    //но     private static void monitorSync2(){ может параллельно выполнять работу,
    // так как разные мониторы
    private static void monitorSync(){// не важно статика или нет
        System.out.println("Print monitor sync.....");
        //код
        //если нужно засинхронизировать лишь кусок кода то
        synchronized (mon1){
            //код который должен быть синхронизирован

        }
    }

    //9.
    private static void monitorSync2(){
        System.out.println("Print monitor sync.....");
        //код
        //если нужно засинхронизировать лишь кусок кода то
        synchronized (mon2){
            //код который должен быть синхронизирован

        }

    }

    //8. 1000 раз инкрементирует данные переменные
    //synchronized - одновременно может выполняться только в одном потоке,
    // остальные ждут пока первый закончит инкремент ,
    // последовательная работа не многопоточность
    private static synchronized void increment() {
        //монитором служит сам класс, но если много синхронизированных методов,
        // то монитор так же один- методы не могут быть выполненны одновременно
        // пока монитор не освободится
        for (int i = 0; i<1000; i++){
            a++;
            b++;
            c++;

        }
    }

    private synchronized void doSync(){
        // если не статический метод, то монитор у каждого объекта
    }

    private static void thredStop() {
        Thread main = Thread.currentThread();//6.

        //6. запускается поток с бесконечным циклом
        Thread t = new Thread(()->{
            //while (true){
              while (!Thread.currentThread().isInterrupted()){    //пока поток не прерван но до того как будет isInterrupted()
                try {
                    Thread.sleep(1000);
                    System.out.println("print 6." + main.getState());
                   // System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e){
                    e.printStackTrace();// try catch внутри while
                    Thread.currentThread().interrupt();//будет исключение и прерывание
                }
            }
        //}).start();
        });

        //t.setDaemon(true);//7. поток демон фоновая/ завершает работу

        t.start();//6.

        try {
            Thread.sleep(3000);
            System.out.println("main finish");
            //если вызвать метод t.interrupt(); то все норм флаг переключится ,
            // но если было Thread.sleep(3000); то InterruptedException: sleep interrupted
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //t.stop();//завершает работу майна, не рекомендуется опасно для потоков
        //t.suspend(); останавливает поток не рекомендованны
        //t.resume();....не рекомендованны
    }

    private static void thredCreation() {
        //отдельные потоки представляют собой объекты класса Thread
        //у Thread  есть поле - имя потоков
        //.currentThread() - получаем ссылку на текущий поток
        // в котором этот метод был вызван

        //1.myThread.run();//выполнится в потоке мэйн
        System.out.println("Hello from main. Thread is " + Thread.currentThread().getName());


        //2.myThread
        // что бы запустить отдельный поток нам нужен в любом случае объект класса Thread
        //далее в объекте переопределяем метод run(). с него начинается и заканчивается
        //что бы старт был в отдельном потоке используем метод .start();
        MyThread myThread = new MyThread();
        myThread.start();

        //3. myRunnableThread
        // что бы запустить  MyRunnable в отдельном потоке,
        //нужен объект .....
        MyRunnable myRunnable = new MyRunnable();
        Thread myRunnableThread = new Thread(myRunnable);
        myRunnableThread.start();

        //4.anonThread
        Thread anonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous. Thread is " + Thread.currentThread().getName());}
        });
        anonThread.start();

        //5.lambdaThread
        Thread lambdaThread = new Thread(()-> System.out.println
                        ("Hello from lambda. Thread is "
                                + Thread.currentThread().getName())); //сокращаеем написание метода
        lambdaThread.start();
        //или все тоже самое можно написать в одну строчку
        //Thread lambdaThread = new Thread(()-> System.out.println
        //    ("Hello from lambda. Thread is "
        //   + Thread.currentThread().getName())).start();
        new Thread(()->System.out.println
                ("Hello from lambda one line. Thread is "
                        + Thread.currentThread().getName())).start();

        //для реализации //5.
        new Thread(()-> printSome()).start();
        new Thread(Multithreading::printSome).start();
        new Thread(Multithreading::printSome).start();
        new Thread(Multithreading::printSome).start();
        new Thread(Multithreading::printSome).start();


        //нельзя один и тот же поток запустить дважды!!!
    }

    //2. что бы запустить код можно сделать прощще
    //создать class MyRunnable имплементировать интерфейс implements Runnable
        public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello from Runnable. Thread is " + Thread.currentThread().getName());

        }}


        //1.1 что бы запустить код в отдельном потоке можно унаследоваться из каласса Thread
        //Thread не абстрактный класс,
        public static class MyThread extends Thread {
            @Override
            //переопределяем метод run() с которогоначинается выполнение задачи в потоке
            // т. е. все приложение стартует с метода main а потоки стартуют с метода run()
            public void run() {
                System.out.println("Hello from MyThread. Thread is " + Thread.currentThread().getName());

            }
        }

        //5.
    private static void printSome() {
        System.out.println("Print Some" + Thread.currentThread().getName());
        //запустим метод из отдельного потока

    }
    }

