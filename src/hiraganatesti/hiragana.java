package hiraganatesti;

public class hiragana {

	String hiraganat[] = {"", "a", "e", "u", "i", "o", "ka", "ke", "ku", "ki", "ko", "sa", "se", "su", "shi", "so", "ta", "te", "tsu", "chi", "to", "na", "ne", "nu", "ni", "no", "ha", "he", "fu", "hi", "ho", "ma", "me", "mu", "mi", "mo", "ra", "re", "ru", "ri", "ro", "ya", "yo", "yu", "wa", "wo"};
	String kysymys;
	int[] indexit = new int[6];
	boolean jatka = true;
	
	public hiragana() {
	}
	
	// checks whether the answer is correct
	public boolean check(int index, String vast) {
		kysymys = hiraganat[index];
		if (kysymys.equals(vast)) {
			return true;
		}
		else
			return false;		
	}
	// makes sure that the same question isn't asked twice
	public boolean checkindex(int index, int laskuri) {
		jatka = true;
		for (int i = 0; i < indexit.length; i++) {
			if (indexit[i] == index)
				jatka = false;
		}
		if (jatka == false)
			{
			return false;
			}
		else 
		{
			indexit[laskuri-1] = index;
			if (laskuri >= 5) {
				indexit = new int[6];
				laskuri = 1;
			}
			return true;
			}
	}
}
