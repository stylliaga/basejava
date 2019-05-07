import java.util.Arrays;
import java.util.Scanner;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    // делаем текст цветным
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_BLUE = "\u001B[34m";

    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        int c = 0;
        int s = 1;
        for(int i = 0; i < size; i++) {
            Arrays.fill(storage,c, s++, null);
        }
    }

    void save(Resume r) {
        if (r.uuid != null && size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    String get(String uuid) {
       // boolean isElem = true;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                    if (storage[i].uuid.equals(uuid)) {
                        return storage[i].uuid;
                    }
            }
        }
        return null;
    }

    void delete(String uuid) {
        // создаем счетчик для того чтобы вывести сколько совпадений найдено по запросу на удаление
        // если совпадение найдено то переспрашиваем реально ли человек хочет удалить учейки
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid == uuid) {
                    storage[i] = null;
                }
            }
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
        for (int i = 0; i < alls.length; i++) {
            count++;
        }
        return count;
    }
    /*------------------------------------------------------*/

}
