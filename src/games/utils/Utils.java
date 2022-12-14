package games.utils;

import java.util.List;

public class Utils {
    public static <E> String listToString(List<E> list) {
        StringBuilder b = new StringBuilder();

        for(int i = 0; i < list.size(); i++) {
            b.append("\n\n");
            b.append(list.get(i).toString());
        }

        return b.toString();
    }
}
