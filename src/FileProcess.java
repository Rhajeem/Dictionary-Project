import java.io.*;
import java.util.Optional;

/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class FileProcess 
{
    
    private static int i;
    private int a;
    
    public FileProcess() 
    {
    }
    
    public Long parse(File file, Optional c) 
    {
        FpTimer j = new FpTimer();


        System.out.print(c.equals(Tree.class));
    
    
        return j.getDiff();
    }
    
    
    public long parse(Tree a, File file) 
    {
        FpTimer j = new FpTimer();
        try 
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line, word1 = "", definition1 = "", partOfSpeech1 = "";
            while ((line = bufferedReader.readLine()) != null) 
            {
                Data temp = new Data();
                i = 0;
                temp.setWord(getWord(line, word1));
                temp.setPartOfSpeech(getPartOfSpeech(line, partOfSpeech1));
                temp.setDefinition(getDefinition(line, definition1));
                a.insert(temp);
            }
            fileReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return j.getDiff();
    }
    
    
    private String getWord(String line, String word1) 
    {
        do
        {
            word1 = word1 + line.charAt(i);
            i++;
            if (line.charAt(i) == '\t' || line.charAt(i) == ' ')
            {
                i++;
                break;
            }
        }
        while (true);
        return word1;
    }
    
    private String getPartOfSpeech(String line, String partOfSpeech) 
    {
        while (line.charAt(i) != '\t')
        {
    
        	partOfSpeech = partOfSpeech + line.charAt(i);
            i++;
    
            if (line.charAt(i) == '\t') 
            {
                i++;
                break;
            }
        }
        
        if (partOfSpeech.contains("p.") || partOfSpeech.contains("pr.")) partOfSpeech = partOfSpeech.replace("p.", "pronoun");
        
        if (partOfSpeech.contains("v.")) partOfSpeech = partOfSpeech.replace("v.", "verb");
        
        if (partOfSpeech.contains("vb.")) partOfSpeech = partOfSpeech.replace("vb.", "verb");
        
        if (partOfSpeech.contains("a.")) partOfSpeech = partOfSpeech.replace("a.", "adverb");
        
        if (partOfSpeech.contains("adj.")) partOfSpeech = partOfSpeech.replace("adj.", "adjective");
        
        if (partOfSpeech.contains("n.")) partOfSpeech = partOfSpeech.replace("n.", "noun");
        
        return partOfSpeech;
    }
    
    private String getDefinition(String line, String definition) 
    {
        while (true) 
        {
    
    
            if (line.charAt(i) == ';') {
            	definition = definition + "\n\t\t";
                i++;
            }
    
            definition = definition + line.charAt(i);
            i++;
    
            if (line.length() == i)
                break;
        }
        return definition;
    }
    
    
    public long parse(LinkedList dlc, File file) 
    {
        
        FpTimer j = new FpTimer();
        try 
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line, word1 = "", definition1 = "", partOfSpeech1 = "";
            while ((line = bufferedReader.readLine()) != null) 
            {
                Data temp = new Data();
                i = 0;
                temp.setWord(getWord(line, word1));
                temp.setPartOfSpeech(getPartOfSpeech(line, partOfSpeech1));
                temp.setDefinition(getDefinition(line, definition1));
                dlc.insert(temp);
            }
            fileReader.close();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return j.getDiff();
    }
    
    
}