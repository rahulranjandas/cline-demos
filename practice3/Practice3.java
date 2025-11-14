import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Practice3 {
    public Set<String> findDuplicates(ArrayList<String> list) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String item : list) {
            if (!seen.add(item)) {
                duplicates.add(item);
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        // Example usage
        Practice3 practice3 = new Practice3();
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");
        list.add("orange");
        list.add("banana");
        Set<String> duplicates = practice3.findDuplicates(list);
        System.out.println("Duplicate elements: " + duplicates);
    }
}
