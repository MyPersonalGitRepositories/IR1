import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Dictionary {

    private File[] textFiles;
    private MyArrayList dict;
    private long wordsInCollection;
    private long wordsInDictionary;
    private long sizeCollection;
    private long sizeDictionary;

    Dictionary(int fileAmount) throws IOException {

        wordsInCollection = 0;
        wordsInDictionary = 0;
        sizeCollection = 0;
        sizeDictionary = 0;

        textFiles = new File[fileAmount];
        for (int i = 0; i < textFiles.length; i++) {
            textFiles[i] = new File("files/"+i+".txt");
        }
        dict = new MyArrayList();

        buildCollection();
        createDictionary();
    }

    private void buildCollection() throws IOException {

        for (File textFile : textFiles) {
            sizeCollection += textFile.length();
            BufferedReader br = new BufferedReader(new FileReader(textFile.getAbsolutePath()));
            String s = "";
            while ((s = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, "\t£½°¾–‘’.,:;()[]{}<>_- —=+“”'`\"/|!?$^&*@#%0123456789\uFEFF");
                while (st.hasMoreTokens()) {
                    String word = st.nextToken().toLowerCase();
                    wordsInCollection++;
                    if (!(Arrays.asList(dict.data).contains(word))) {
                        dict.add(word);
                    }
                }
            }
            br.close();
        }
        Arrays.sort(dict.data,new MyArrayList.MyComparator());
    }

    private void createDictionary() throws IOException {

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("dictionary.txt")));

        for (int i = 0; i < dict.data.length - 1; i++) {
            if ((dict.data[i] != null)){
                printWriter.write(dict.data[i]);
                printWriter.write("\n");
                wordsInDictionary++;
            }
        }
        File output = new File("dictionary.txt");
        sizeDictionary = output.length();

        printWriter.write("\n \n");
        printWriter.write("COLLECTION SIZE: " + sizeCollection + " bytes \n");
        printWriter.write("DICTIONARY SIZE: " + sizeDictionary + " bytes \n");
        printWriter.write("WORDS IN COLLECTION: " + wordsInCollection + "\n");
        printWriter.write("WORDS IN DICTIONARY: " + (wordsInDictionary-1));

        printWriter.close();

        System.out.println("COLLECTION SIZE: " + sizeCollection + " bytes" );
        System.out.println("DICTIONARY SIZE: " + sizeDictionary + " bytes" );
        System.out.println("WORDS IN COLLECTION: " + wordsInCollection);
        System.out.println("WORDS IN DICTIONARY: " + (wordsInDictionary-1));

    }
}