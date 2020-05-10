 //18908148 Nicholas Di Blasi 
 //Part 2: 
 
 //Word.java establishes the words characteristic, determining other words that are similar length but are
 //different by one letter, frequency of that word and it's sequence etc. Originally had my insertion sort inside 
 //Lexicon tester but was causing problems but was fixed once implemented inside Word. Had troubles
 //with my pattern method as it is half implemented. 
 import java.util.*;
 import java.util.regex.Pattern; 

public class Word implements Comparable<Word> 
{ 
	private String sequence; 
	private int freq; 
	private List<Word> neighbours; 
	
	public Word(String word) 
	{ 	
		this.sequence = word; 
		freq = 1; 
		neighbours = new ArrayList<Word>(); 
	} 
	
	
	//here is what i had so far for my match method, it iterated through each 
	//pattern using '?' and '*' as a wildcards where ? would find a match of a
	//word from a-z (regex) and * would match the number of characters from a-z (match goes from 
	//zero to one) 
		
	public static boolean match(String word, String pattern)
	{
		String newP = new String(); 
		
		for(int i = 0; i < pattern.length(); i++)
		{
			if(pattern.charAt(i) == '*')
			{ 
				newP += "[a-z]*"; 
			} 
			
			else if(pattern.charAt(i) == '?')
			{ 
				newP += "[a-z]";
			}
			else 
			{ 
				newP += pattern.charAt(i); 
			} 
		}
		return Pattern.matches(newP, word); 
	}	 
	
	
	//Returns the amount of frequency a word is present 
    public int getFreq() 
	{
        return freq;
    }
	
    //Adds one each time that word is found 
    public void addFreq() 
	{
        freq++;
    }

    // Will return the words sequence
    public String getSequence() 
	{
        return sequence;
    }

    //Methods that returns true word contains neighbour (contain same length but only
	// 
	//A neighbor can only be a neighbor if they have the same length
    // and the difference of letters is one.
    public boolean addNeighbours(Word word) 
	{
        //Checks to see the difference of no. words where 
		//no words in the lexicon match the pattern
		if (diffNumWords(word) != 1 || Word.findSequence(neighbours, word.sequence) != null) 
		{
			return false;
        }
		
		//Adds neighbours to each word it belongs to then uses sorting method
		//to alphabetically sort the words now consisting of its neighbours 
        neighbours.add(word);
        Word.insertionSort(neighbours);
        
		//obviously if its has difference of numbers by one returns true 
		return true;
    }

    // Method to calculate the difference of each word
    public int diffNumWords(Word instance) 
	{
         //if the length of the word sequence is none that is the same 
		 //clearly cant have any 
        if (sequence.length() != instance.sequence.length()) 
		{
            return -1;
        }
		
        int diffNoWords = 0;
		
		//iterates through each word to check it's length in comparison to another 
		//words length
        for (int i = 0; i < sequence.length(); i++) 
		{
			if (sequence.charAt(i) != instance.sequence.charAt(i)) 
			{
                diffNoWords++;
            }
        }

        return diffNoWords;
    }

	//Helper method which compares two words, will be used for insertion sort 
    public int compareTo(Word instance) 
	{
        return sequence.compareTo(instance.sequence);
    }

    //This will return the string that will display the word's
    //sequence followed by it's frequency and neighbours
    public String toString() 
	{
        String string = "";
        string += sequence + " " + freq + " ";
        string += "[";

        for (int i = 0; i < neighbours.size(); i++) 
		{
            string += neighbours.get(i).sequence;

            if (i + 1 < neighbours.size()) 
			{
                string += ", ";
            }
        }

        string += "]";
        return string;
    }

    //Helper method to find a word by it's sequence 
    public static Word findSequence(List<Word> list, String wordSequence) 
	{
		for (Word word : list) 
		{
            if (word.getSequence().equals(wordSequence)) 
			{
                return word;
            }
        }
		
		//Clearly if the word can't find a sequence that is equal to it, will 
		//return nothing 
        return null;
    }

    //Helper method that will sort each of the words alphabetically
	//Similar to the one give inside sorter.java
	public static void insertElement(List<Word> list, int next)
	{ 
		int i = next; 
		Word sequence = list.get(next); 
		
		while(true)
		{ 
			
			if(i == 0)
			{
				list.set(0, sequence); 
				return;
			} 
			else if(list.get(i-1).compareTo(sequence) <= 0)
			{
				list.set(i, sequence); 
				return; 
			}
			else
			{
				list.set(i, list.get(i-1)); 
				--i; 
			} 
		}
	}
	
	//Method to preform insertion sort, this will be called upon inside
	//the lexicon tester
	public static void insertionSort(List<Word> list)
	{ 
		for(int i = 1; i < list.size(); i++)
		{ 
			insertElement(list, i); 
		}
	}
	

    //Helper function that will decompose a word by the assignments requirements
	//
    public static String decomposedSequence(String sequence)
	{
        //iterates through the words sequence ignoring numeric characters 
		for (int i = 0; i < sequence.length(); i++) 
		{
            if (sequence.charAt(i) >= '0' && sequence.charAt(i) <= '9') 
			{
                return "";
            }
        }
		
		//words need to be treated in a case-insensitive manner 
        sequence = sequence.toLowerCase().trim();
        String newSequence = "";
		
		//checks to make sure a word's sequence is in lowercase
        for (int i = 0; i < sequence.length(); i++) 
		{
            if (sequence.charAt(i) >= 'a' && sequence.charAt(i) <= 'z') 
			{
                //corrects the original word's sequence by storing it in the 
				//new sequence
				newSequence += sequence.charAt(i);
            }
        }

        return newSequence.trim();
    }
}