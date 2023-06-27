package lzw;

public class Encoder {
    private Dictionary dictionary = new Dictionary();


    /**
     * encodes the given input via lzw compression.
     * @param input the given input String
     * @return the encoded String containing decimal integers divided by a space
     */
    public String encode(String input) {
        input = input.toLowerCase();
        for (char c: input.toCharArray()) {
            if (!dictionary.isInDictionary(String.valueOf(c))) {
                throw new IllegalStateException("the entered String contains non-ASCII characters");
            }
        }
        StringBuilder output = new StringBuilder();
        StringBuilder prefix = new StringBuilder();
        char current;

        for (int i = 0; i < input.length(); i++) {
            StringBuilder prefixPlusCurrent = new StringBuilder();
            current = input.charAt(i);
            prefixPlusCurrent.append(prefix).append(current);
            if (!(dictionary.isInDictionary(prefixPlusCurrent.toString()))) { // if prefix+current is not in the dictionary...
                if (dictionary.getEntries().size() > 65534) {
                    throw new IllegalStateException("lzw.Dictionary too big");
                }
              dictionary.add(prefixPlusCurrent.toString()); // ... it will be added
              output.append(dictionary.getEntries().indexOf(prefix.toString())).append(" "); // ... the dictionary entry resembled by
                                                                                        // prefix is appended to the output
              prefix.setLength(0); // (re)set prefix
              prefix.append(current);
            } else {
                prefix.setLength(0);
                prefix.append(prefixPlusCurrent); // if prefix+current is in the dictionary, the new prefix is prefix+current
            }

        }
        output.append(dictionary.getEntries().indexOf(prefix.toString()));
        return output.toString();
    }

    /**
     * converts the output of encode() to a binary string. Each decimal integer of the encode() output ist converted to
     * a 16bit binary number.
     * @param outputInDec the decimal output as created by encode()
     * @return a string consisting of 16bit wide conversion of the integers in the entered string
     */
    public String convertOutputStringToBinary(String outputInDec) {
        StringBuilder outputInBin = new StringBuilder();
        String[] decimalArray = outputInDec.split(" ");
        for (String s: decimalArray) {
            StringBuilder bin = new StringBuilder();
            bin.append(Integer.toBinaryString(Integer.parseInt(s)));
            while (bin.length() < 16) {
                bin.insert(0, "0");
            }
            outputInBin.append(bin);
        }
        return outputInBin.toString();
    }

    /**
     * 'main' method of lzw.Encoder class.
     * @param input the input String
     * @return the final encoded String consisting of 16bit binary numbers
     */
    public String action(String input) {
        String outputInDec = encode(input);
        return convertOutputStringToBinary(outputInDec);
    }
}
