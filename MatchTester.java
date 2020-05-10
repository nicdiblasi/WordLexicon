import java.io.*;
import java.util.*;

public class MatchTester
{
	// Entry point for word matching, find words that matches a pattern
    public static void main(String[] args) throws Exception 
	{
        List<Word> words = LexiconTester.constructLexicon(new String[]{"sample1-pp.txt", "sample2-zoo.txt"});
        
        // These were the pattern chosen because they use both '*' and '?' as combination and individually
		//These have to be hardcoded
        String[] patterns = { "ma?", "?o?", "mr*", "i*", "?ear*", "?ing", "ma*", "a?e", "as?", "oo??" };
        
        PrintWriter writer = new PrintWriter(new File("sample4-results.txt"));
        
        for(String pattern : patterns) 
		{
            writer.println("The pattern:");
            writer.println("\t" + pattern);
            
            for(Word word : words) 
			{
                if(Word.match(word.getSequence(), pattern)) 
				{
                    writer.println("\t" + word.getSequence() + " " + word.getFreq());
					
					System.out.print("The Pattern: "); 
					System.out.println("[" + pattern + "]\n"); 	
				}
            }
        }
        
        writer.close();
        
        System.out.println("Finished");
    }
}