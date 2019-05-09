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
        Arrays.fill(storage,(size-size), size, null);
    }

    void save(Resume r) {
        if (r.uuid != null && size < storage.length) {
            storage[size] = r;
            size++;
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
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid) || storage[i].uuid.equals(null)) {
                counter++;
                storage[i] = null;
            }else if(counter > 0){
                storage[i - counter] = storage[i];
                storage[i] = null;
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
