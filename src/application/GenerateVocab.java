package application;

import java.io.*;

public class GenerateVocab {

    private BufferedReader reader;

    /**
     * Read the words from text file.
     * @author Setthanat Kladee
     */
    public  GenerateVocab(){
        File file = new File("src/Graphics/JavaWords.txt");
        try{
            reader = new BufferedReader(new FileReader(file));

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate String for typing in the game.
     * @return words
     */
    public String getLabel(){
        String string = "";
        try{
            string = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            string = null;
        }
        return string;
    }
}