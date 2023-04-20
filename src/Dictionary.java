import java.util.ArrayList;

public class Dictionary {
    ArrayList<String> entries = new ArrayList<>();

    public Dictionary() {
        initializeDictionaryFull();
    }

    /**
     * adds an entry in lowercase to the Dictionary
     * @param entry the String to be added
     */
    public void add(String entry) {
        entries.add(entry.toLowerCase());
    }

    /**
     * checks if the given String is in the Dictionary already.
     * @param entry the given String
     * @return true/false if the String is/isn't in the Dictionary
     */
    public boolean isInDictionary(String entry) {
        for (String e: entries) {
            if (e.equals(entry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * initializes the dictionary with all printable ASCII characters (32-126)
     */
    public void initializeDictionaryFull(){
        for (int i = 32; i < 127; i++) {
            add(String.valueOf((char)i));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < entries.size(); i++) {
            stringBuilder.append(entries.indexOf(entries.get(i))).append(": ").append(entries.get(i)).append(", ");
            if (i%15 == 0  && i > 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
