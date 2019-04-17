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
        System.out.println(ANSI_RED + "########### [Массив очищен!] ###########" + ANSI_RESET);
    }

    void save(Resume r) {
        if(r.uuid != null && size<storage.length){
            storage[size]=r;
            size++;
            System.out.println(ANSI_GREEN + "########### [Сохранено] ###########" + ANSI_RESET);
        }else{
            System.out.println(ANSI_RED + "########### [Не корректный ввод данных!] ###########" + ANSI_RESET);

        }
    }

    Resume get(String uuid) {
        boolean isElem = true;
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                try {
                    if (storage[i].uuid == uuid) {
                        System.out.println(ANSI_BLUE + storage[i].uuid + ANSI_RESET);
                    }
                } catch (Exception e) {}
                count++;
            }else{
                isElem = false;
            }
        }
        if (isElem == false && count == 0){
            System.out.println(ANSI_RED + "########### [Искомый элемент не найден] ###########" + ANSI_RESET);
        }

        return null;
    }

    void delete(String uuid) {
        // создаем счетчик для того чтобы вывести сколько совпадений найдено по запросу на удаление
        // если совпадение найдено то переспрашиваем реально ли человек хочет удалить учейки
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid == uuid)  {
                    count++;
                }
            }else{}
        }
       if(count > 1){
            System.out.println("Найдено " + ANSI_PURPLE + count + ANSI_RESET+" совпадений." +
                    "\nУдалить все или по одиночке?"
                    + ANSI_RED + "\nВведите A(все)" + ANSI_RESET + " / "
                    + ANSI_YELLOW + "S(по одиночке)" + ANSI_RESET + " / "
                    + ANSI_GREEN + "N(отмена)" + ANSI_RESET + ":");
            Scanner choosenWord = new Scanner(System.in);
            String choose = choosenWord.nextLine();
            deleteChoosen(uuid,choose);
        }else if(count == 1){
            System.out.println("Найдено совпадение.\nУдалить?"
                    + ANSI_RED + "\nВведите Y(Да)" + ANSI_RESET + " / "
                    + ANSI_GREEN + "N(отмена)" + ANSI_RESET + ":");
            Scanner choosenWord = new Scanner(System.in);
            String choose = choosenWord.nextLine();
            deleteChoosen(uuid,choose);
        }else if (count == 0){
            System.out.println("Совпадений не найдено =(");
        }
    }
    void deleteChoosen(String someUuid, String select){
        if (select.equals("A")){
            for (int i = 0; i < storage.length; i++){
                if (storage[i] != null) {
                    if (storage[i].uuid == someUuid) {
                        storage[i] = null;
                       // size--;
                    }
                }else{}
            }
            System.out.println(ANSI_RED  +
                  "\n#################################\n" +
                    "########### [Удалено] ###########\n" +
                    "#################################" + ANSI_RESET);
           // System.out.println("Все элементы удалены.");
           // Arrays.sort(storage);
        }else if (select.equals("S") || select.equals("Y")){
            for (int i = 0; i < storage.length; i++) {

                if (storage[i] != null) {
                    if (storage[i].uuid == someUuid) {
                        storage[i] = null;
                        // size--;
                    break;
                    }
                }else{}
            }

            //Arrays.sort(storage);
            System.out.println(ANSI_RED  +
                  "\n#################################\n" +
                    "########### [Удалено] ###########\n" +
                    "#################################" + ANSI_RESET);
        }else if (select.equals("S")){}
        //System.out.println(select);
        return;
    }
    void delMeth(int i, String someUuid) {
            if (storage[i] != null) {
                if (storage[i].uuid == someUuid) {
                    storage[i] = null;
                    size--;
                }
            } else {
            }
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
        int count = 0;
        Resume[] alls = storage;
        alls = Arrays.stream(alls).filter(s -> (s != null)).toArray(Resume[]::new);
        for (int i = 0; i < alls.length; i++){
            count++;
        }
        return count;
    }
/*------------------------------------------------------*/

}
