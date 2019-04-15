import java.util.Arrays;
import java.util.Scanner;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    // делаем текст цветным
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    Resume[] storage = new Resume[10000];
    int size = 0;
    void clear() {
        Arrays.fill(storage,null);
        System.out.println(ANSI_RED + "[Массив очищен!]" + ANSI_RESET);
    }

    void save(Resume r) {
        if(r != null && size<storage.length){
            storage[size]=r;
            size++;
            System.out.println(ANSI_GREEN + "[Сохранено]" + ANSI_RESET);
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
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
            System.out.println("Найдено "+ count +" совпадений.\nУдалить все или по одиночке?\nВведите A(все) / S(по одиночке)" +
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
                    storage[i] = null;
                    System.out.println(ANSI_RED + "\n##############\n" +
                            "##[Удалено]###\n" +
                            "##############" + ANSI_RESET);
                    storage[i] = new Resume();

                    //size--;
                }
            }
           // System.out.println("Все элементы удалены.");
        }else if (select.equals("S") || select.equals("Y")){
            for(int i = 0; i < size; i++){
                if (storage[i].uuid == someUuid) {
                    storage[i] = null;
                    System.out.println(ANSI_RED  +"\n##############\n" +
                            "##[Удалено]###\n" +
                            "##############" + ANSI_RESET);
                    storage[i] = new Resume();

                    //size--;
                    break;
                }
            }
            System.out.println("Элемент успешно удален!");
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
        alls = Arrays.stream(alls).filter(s -> (s != null)).toArray(Resume[]::new);
        return alls;
    }

// для собственной проверки выводим общее количество ячеек
    int getSumElem() {
        return storage.length;
    }

    int size() {
        return 0;
    }


}
