import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    // делаем текст цветным
    public  String ANSI_RESET = "\u001B[0m";
    public  String ANSI_BLACK = "\u001B[30m";
    public  String ANSI_RED = "\u001B[31m";
    public  String ANSI_GREEN = "\u001B[32m";
    public  String ANSI_YELLOW = "\u001B[33m";
    public  String ANSI_BLUE = "\u001B[34m";
    public  String ANSI_PURPLE = "\u001B[35m";
    public  String ANSI_CYAN = "\u001B[36m";
    public  String ANSI_WHITE = "\u001B[37m";

    Resume[] storage = new Resume[10000];
    int size = 0;
    void clear() {
        Arrays.fill(storage,null);
        System.out.println(ANSI_RED + "#######[Массив очищен!]#######" + ANSI_RESET);
    }

    void save(Resume r) {
        if(r != null && size<storage.length){
            storage[size]=r;
            size++;
            System.out.println(ANSI_GREEN + "#######[Сохранено]#######" + ANSI_RESET);
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                System.out.println(ANSI_BLUE + storage[i]+ ANSI_RESET);
            }
        }
        return null;
    }

    void delete(String uuid) {
        // создаем счетчик для того чтобы вывести сколько совпадений найдено по запросу на удаление
        // если совпадение найдено то переспрашиваем реально ли человек хочет удалить учейки
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid)  {
                count++;
                //size --;
                //System.out.println(storage[i].uuid);
            }
            /*
            if (storage[i].uuid == uuid) {
                System.out.println("Успешно удалено =)");
                storage[i] = null;
            }else{
                System.out.println("Извините, указанное значение ячейки массива не найдено!");
            }*/

        }
       if(count > 1){
            System.out.println("Найдено " + ANSI_PURPLE + count + ANSI_RESET+" совпадений.\nУдалить все или по одиночке?\nВведите A(все) / S(по одиночке)" +
                    " / N(отмена):");
            Scanner choosenWord = new Scanner(System.in);
            String choose = choosenWord.nextLine();
            deleteChoosen(uuid,choose);
        }else if(count == 1){
            System.out.println("Найдено совпадение.\nУдалить?\nВведите Y(Да) / N(отмена):");
            Scanner choosenWord = new Scanner(System.in);
            String choose = choosenWord.nextLine();
            deleteChoosen(uuid,choose);
        }else if (count == 0){
            System.out.println("Совпадений не найдено =(");
        }
    }
    void deleteChoosen(String someUuid, String select){
        if (select.equals("A")){
            for(int i = 0; i < size; i++){
                if (storage[i].uuid == someUuid) {
                    //storage[i] = "";
                    storage[i] = new Resume();
                }
            }
            System.out.println(ANSI_RED  +"\n###############\n" +
                    "###[Удалено]###\n" +
                    "###############" + ANSI_RESET);
           // System.out.println("Все элементы удалены.");
           // Arrays.sort(storage);
        }else if (select.equals("S") || select.equals("Y")){
            for(int i = 0; i < size; i++){
                if (storage[i].uuid == someUuid) {
                    //storage[i] = null;
                    storage[i] = new Resume();
                    break;
                }
            }
            //Arrays.sort(storage);
            System.out.println(ANSI_RED  +"\n###############\n" +
                    "###[Удалено]###\n" +
                    "###############" + ANSI_RESET);
        }else if (select.equals("S")){}
        //System.out.println(select);
        return;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
// здесь мы фильтруем хранилище и вытаскиваем только заполненные ячейки массива
        Resume[] alls = storage;
       // String[] strAlls = storage;
        //alls = Arrays.stream(alls).filter(s -> (s != null)).toArray(Resume[]::new);
        alls = Arrays.stream(alls).filter(Objects::nonNull).toArray(Resume[]::new);
        return alls;
    }

// для собственной проверки выводим общее количество ячеек
    int getSumElem() {
        return storage.length;
    }

    int size() {
        int count = 0;
        Resume[] alls = storage;
        alls = Arrays.stream(alls).filter(s -> (s != null)).toArray(Resume[]::new);
        for (int i = 0; i < alls.length; i++){
            count++;
        }
        return count;
    }
/*------------------------------------------------------*/
    void arraV(){
        //String[] array = {"abc", "def", null, "g", null}; // Your array
        Resume[] array = storage; // Your array
        //String[] refinedArray = new String[array.length]; // A temporary placeholder array
        Resume[] refinedArray = new Resume[array.length]; // A temporary placeholder array
        int count = -1;
        for(Resume s : array) {
            if(s != null) { // Skips over null values. Add "|| "".equals(s)" if you want to exclude empty strings
                refinedArray[++count] = s; // Increments count and sets a value in the refined array
            }
        }

// Returns an array with the same data but refits it to a new length
        array = Arrays.copyOf(refinedArray, count + 1);
        System.out.println(Arrays.toString(storage));

        /*Resume[] origArray = storage;
        int count = -1;
        for (int i = 0; i < storage.length; i++){
            if(storage[i] != null && storage.equals("null")) {
                System.out.println(storage[i]);
            }
        }*/
//        storage = Arrays.copyOf(origArray,count + 1);
//        System.out.println(Arrays.toString(storage));

        /*
        //Resume[] origArray = storage;
        Resume[] origArray = Arrays.stream(storage).filter(s -> (s != null)).toArray(Resume[]::new);
        Resume[] origArray2 = Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);

        System.out.println(Arrays.toString(origArray));

        for(int i = 0; i < origArray.length; i++){
            if(origArray[i].uuid != null){
                //new Resume().uuid = origArray[i].uuid;
                //origArray[i].uuid = ARRAY_STORAGE.storage;
                System.out.println(origArray[i]);
            }else{}
        }
        System.out.println(Arrays.toString(origArray2));
        */
        /*
        origArray = Arrays.stream(origArray).filter(s -> (s != null)).toArray(Resume[]::new);
        //Resume[] arra = Arrays.stream(origArray).filter(Objects::nonNull).toArray(Resume[]::new);

        System.out.println(Arrays.toString(origArray));
        for(int i = 0; i < origArray.length; i++){
            if(origArray[i].uuid != null){
                new Resume().uuid = origArray[i].uuid;
                //origArray[i].uuid = ARRAY_STORAGE.storage;
                System.out.println(origArray[i]);
            }else{}
        }
        System.out.println(Arrays.toString(storage));
        origArray = Arrays.sort(origArray);
        System.out.println(Arrays.toString(origArray));
        */
        return;
    }

}
