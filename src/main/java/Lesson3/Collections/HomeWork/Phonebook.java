package Lesson3.Collections.HomeWork;

import java.util.*;


public class Phonebook {

    private HashMap<String, List<String>> list;

    public Phonebook(){
        this.list = new HashMap<>();
    }

    public void add(String surname, String number){

        if(list.containsKey(surname)){
            List<String> numbers = list.get(surname);

            //если используется несколько номеров одним человеком
            if (!numbers.contains(number))
              {numbers.add(number);
                  System.out.println(String.format(" %s номер: %s", surname,number ));
              }
            //определение кто использует данный номер
        } else {
            list.put(surname, new ArrayList<>(Arrays.asList(number)));
            System.out.println(String.format(" %s номер: %s", surname,number ));
            }
    }

    public List<String> get(String surname){
        if(list.containsKey(surname)){
            return list.get(surname);
        }
        //else
        {return new ArrayList<>();
            }
        }

}
