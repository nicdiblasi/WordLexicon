//18908148 Nicholas Di Blasi 
//Assignment Task 1: 

//LexiconTester consists of the class and methods that construct the lexicon, lists particular 
//word's neighbours and most importantly reads the two sample files data and writes it to a new file 
//creating a lexicon of words. 

import java.io.*;
import java.util.*;

public class LexiconTester 
{
    public static void main(String[] args) throws Exception 
	{
        //The list of words is applied to the sample1 and 2
		String[] x = {args[0], args[1]};//, args[2], args[3]}; 
		List<Word> words = constructLexicon (x); 
        //writing method is used to write words to the new sample3
		writeWords(words, "sample3-wordlist.txt");
        System.out.println("The Lexicon has been created and been written to sample3-wordlist.txt");
		
		//used in-order to print out the lexicon list to the display 
		for(Word word : words)
		{ 
			System.out.println(word); 
		}
	}
	// Constructing the lexicon by creating each word that is in each of the abstract 
	//sample1 or 2 
    public static List<Word> constructLexicon(String[] sampleNames) throws Exception 
	{
	   //ArrayList of words 
	   List<Word> words = new ArrayList<Word>();

        //Iterates through each sample file by there name scanning all their 
		//words 
        for (String sampleName : sampleNames) 
		{
            Scanner inFile = new Scanner(new File(sampleName));

            while (inFile.hasNext()) 
			{
                //word is decomposed(class in Word.java) where the word sequence 
				// is removed of any punctuation or digits eg. don't (more info in Word.java) 
				
				String wordSequence = inFile.next();
				
				wordSequence = wordSequence.toLowerCase();
			    wordSequence = wordSequence.replaceAll("[^a-z]", ""); 
                //checks to make sure word sequence is not empty, if so find word by 
				//it's sequence
				if (!wordSequence.isEmpty()) 
				{
                    Word word = Word.findSequence(words, wordSequence);

					//If a word is not found, add a new word based on its sequence 
					//This is used to make sure no duplicates exist 
                    if (word == null) 
					{
                        words.add(new Word(wordSequence));
                    } 
					//obviously if word is found with this sequence then add its frequency by 1
					else 
					{
                        word.addFreq();
                    }
                }
            }

            inFile.close();
        }

		//Sorts the words in the constructed lexicon alphabetically with the use of insertion sort 
		//inside Word.java 
        Word.insertionSort(words);

        //With the use of the neighbours method this finds word's neighbours 
        listOfNeighbours(words);
        
        //now this can be used in order for the other methods to use the words inside the files
		return words;
    }

    // Method for creating a list of neighbours for each word
    private static void listOfNeighbours(List<Word> words) 
	{
        for (int i = 0; i < words.size(); i++) 
		{
            for (int j = 0; j < words.size(); j++) 
			{
                if (i != j) 
				{
                    words.get(i).addNeighbours(words.get(j));
                }
            }
        }
    }

    // Writing method that gathers each word and writes to new file 
    private static void writeWords(List<Word> words, String sampleName) throws Exception 
	{
        PrintWriter writer = new PrintWriter(new File(sampleName));

		//iterates through each word 
        for (Word word : words) 
		{
            writer.println(word);
        }

        writer.close();
    }

 
   
}
