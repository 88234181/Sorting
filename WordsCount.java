import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class WordsCount {
	
	public List<String> wordFrequency(String strfilePath, int nCount) throws IOException
	{
		//create reader to read in files
		FileReader fr = new FileReader(new File(strfilePath));
		BufferedReader br = new BufferedReader(fr);
		
		// list to return
		List<String> wordsCountList = new ArrayList<String>();
		
		// map to keep track of the word counts
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		String line;

		while((line = br.readLine()) != null)
		{
			line = line.trim();
			// regex of how words are separated
			String[] words = line.split("\\s+|,\\s*|\\.\\s*");
			for(String str: words)
			{
				// updating the word counts
				if(wordMap.containsKey(str))
				{
                    wordMap.put(str, wordMap.get(str)+1);
                } else {
                    wordMap.put(str, 1);
                }
			}
		}
		
		//sort the list according to the frequency of words
		List<Entry<String, Integer>> list = sortByValue(wordMap);
		
		int i = 0;
		
		//output a list contains only words string
		for(Entry<String, Integer> e: list)
		{
			//keep track of the number of items return
			if(i >= nCount)
			{
				break;
			}
			
			String strWord = e.getKey();
			Integer nValue = e.getValue();
			
			System.out.println("word: " + strWord + " count : " + nValue);
			
			//create list of words string
			wordsCountList.add(strWord);
			i++;
		}
		
		// close file after the work is done.
		br.close();
		
		return wordsCountList;
	}
	
	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap)
	{
		
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        
        //implement the sort method of Collection to create new sort method
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        
        return list;
    }
	
	public static void main(String arg[]) throws IOException
	{
		WordsCount wc = new WordsCount();
		
		Integer nCount = Integer.valueOf(3); //number of items return
		
		//directory of the test files
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.dir"));
		sb.append("/src/test2.txt");
		
		//execute the method
		wc.wordFrequency(sb.toString(), nCount);
	}

}
