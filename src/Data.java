/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class Data {
    private String word;
    private String partOfSpeech;
    private String definition;

    public Data(String word, String partOfSpeech, String definition) 
    {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public Data() 
    {
        this.word = null;
        this.partOfSpeech = null;
        this.definition = null;
    }

    public String getWord() 
    {
        return word;
    }

    public void setWord(String word) 
    {
        this.word = word;
    }

    public String getPartOfSpeech()
    {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) 
    {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() 
    {
        return definition;
    }

    public void setDefinition(String definition) 
    {
        this.definition = definition;
    }

    
   
}
