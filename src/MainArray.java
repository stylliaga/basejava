import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Interactive test for ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println("\u001B[36m ##### [Количество заполненных ячеек в массиве: " + ARRAY_STORAGE.size() + " ] #####\u001B[0m");
                    break;
                case "save":
                    r = new Resume();
                    r.uuid = uuid;
                    //
                    // System.out.println("Print r.uuid = "+r.uuid);
                    //System.out.println("Print ARRAY_STORAGE.getAll() = " + ARRAY_STORAGE.save(r););
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    ARRAY_STORAGE.get(uuid);
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println(ARRAY_STORAGE.ANSI_YELLOW + "----------- [Заполненных ячеек: " + all.length + " ] -----------------"
                         + ARRAY_STORAGE.ANSI_RESET);
        if (all.length == 0) {
            System.out.println(ARRAY_STORAGE.ANSI_BLUE + "Empty"
                             + ARRAY_STORAGE.ANSI_RESET);
        } else {
            for (Resume r : all) {
                if(ARRAY_STORAGE.getAll() == null) {System.out.print("");}
                else{
                    System.out.println(ARRAY_STORAGE.ANSI_BLUE + r + ARRAY_STORAGE.ANSI_RESET);
                }
            }
        }
        System.out.println(ARRAY_STORAGE.ANSI_YELLOW + "-----------[Ячеек в массиве: " + ARRAY_STORAGE.getSumElem()
                          + ARRAY_STORAGE.ANSI_RESET + " ]-----------------"+"\u001B[0m");
    }

}