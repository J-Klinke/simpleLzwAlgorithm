public class Decoder {
    Dictionary dictionary = new Dictionary();


    /**
     * converts the encoded String into an array of 16bit binary number Strings
     * @param input the encoded String
     * @return the converted String array
     */
    public String[] splitInputString(String input) {
        String[] splitInputString = new String[input.length()/16];
        for (int i = 0; i < splitInputString.length; i++) {
            splitInputString[i] = input.substring(16*i,16+16*i);
        }
        return splitInputString;
    }


    /**
     * decodes the entered String with a lzw algorithm.
     * @param inputStringAsArray the string array provided by splitInputString()
     * to be decoded in the format of 16bit binary integers
     * @return the decoded string
     */
    public String decode(String[] inputStringAsArray) {
        StringBuilder output = new StringBuilder();

        int altCode = -1; // altcode isn't initialised until the end of the first iteration
        for (String string: inputStringAsArray) {
            int code = Integer.parseInt(string,2); // code is the decimal equivalent of the binary number in the input array
            StringBuilder prefix = new StringBuilder();
            if (altCode != -1) {
                prefix.append(dictionary.entries.get(altCode)); // prefix is the decoded char/string of altcode
            }

            if (dictionary.entries.size() >= code+1){ // while the size of entries is bigger than or equal to code, the
                                                    // symbol represented by code is in the dictionary
                char sign = dictionary.entries.get(code).charAt(0); // in this case sign is the first char of the String
                                                                    // which is represented by code
                String newEntry = prefix.toString()+sign;
                if (!(dictionary.isInDictionary(newEntry))) {
                    dictionary.add(newEntry);
                }
                output.append(dictionary.entries.get(code));
            } else if (altCode != -1){
                char sign = dictionary.entries.get(altCode).charAt(0); // in this case sign is the first char of the String
                                                                       // which is represented by altcode
                StringBuilder newEntry= new StringBuilder();
                newEntry.append(prefix).append(sign);
                dictionary.add(newEntry.toString());
                output.append(dictionary.entries.get(dictionary.entries.indexOf(newEntry.toString())));
            }

            altCode = code;
        }


        return output.toString();
    }

    /**
     * the 'main' method of Decoder class
     * @param input the encoded String
     * @return the decoded String
     */
    public String action(String input) {
        return decode(splitInputString(input));
    }

}
