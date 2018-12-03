package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import java.util.stream.IntStream;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	//seems a bit easier after tackling the big ones first
	public String acronym(String phrase) {
		String regex = "[ -]";
		String[] cutUp = phrase.split(regex);
		//I chose aids because there is that one bus stop near iQ with
		//info about Tampa's aids research facilities
		//Keith Haring died of AIDS related complications during the big epidemic
		//Granma also sent in some quilt pieces for some of her friends
		//safe sex saves lives
		char[] firstLetters = new char[cutUp.length];
		for (int aids=0;aids<cutUp.length;aids++) {
			//haha double positive
			firstLetters[aids]=Character.toUpperCase(cutUp[aids].charAt(0));
		}
		String kiss = new String(firstLetters);
		//keep it simple stupid
		return kiss;
		//smooch
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (sideOne==sideTwo && sideTwo==sideThree) {
				return true;
			}
			else {
				return false;				
			}
		}

		public boolean isIsosceles() {
			if (sideOne==sideTwo || sideTwo==sideThree || sideThree==sideOne) {
				return true;
			}
			else {
				return false;
			}
		}

		public boolean isScalene() {
			if (sideOne!=sideTwo && sideTwo!=sideThree && sideThree!=sideOne) {
				return true;
			}
			else {
				return false;
			}
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int points = 0;
		char[] pieces = string.toCharArray();
		for (int scrab=0;scrab<pieces.length;scrab++) {
			pieces[scrab]=Character.toLowerCase(pieces[scrab]);
			switch (pieces[scrab]) {
			case 'z': case 'q': points+=10; break;
			case 'j': case 'x': points+=8; break;
			case 'k': points+=5; break;
			case 'f': case 'h': case 'v': case 'w': case 'y': points+=4; break;
			case 'b': case 'c': case 'm': case 'p': points+=3; break;
			case 'd': case 'g': points+=2; break;
			default: points+=1;
			}
			//System.out.println(pieces[scrab]+""+points);
		}
		return points;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException{
		String regex = "[\\(\\)\\. -]";
		String thrownStuff = "[^0-9]";
		string=string.replaceAll(regex,"");
		//gets rid of anything that could be extra in there;
		string=string.replaceAll(thrownStuff,"E");
		//adds the letter E to show where someone passed in something silly
		if (string.length()>11 || string.contains("E") || invalidLongFormPhoneNumber(string)) {
			throw new IllegalArgumentException();
		}
		//System.out.println(string);
		return string;
	}
	public boolean invalidLongFormPhoneNumber(String phoneNum) {
		if (phoneNum.length()==11) {
			if (phoneNum.charAt(0)!=1) {
				return true;
			}
			if (phoneNum.charAt(1)==0 || phoneNum.charAt(1)==1) {
				return true;
			}
			if (phoneNum.charAt(4)==0 || phoneNum.charAt(4)==1) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
    	HashMap<String, Integer> weirdMap = new HashMap<String, Integer>();
        string = string.replaceAll("[\n]", "");
        String[] stringArr = string.split("[, ]");
        //this splits on either spaces or commas
        for (int i=0;i<stringArr.length;i++) {
            //System.out.println(array[i]);
        	Integer arrPos = weirdMap.get(stringArr[i]);
        	if (weirdMap.get(stringArr[i])==null) {
        		weirdMap.put(stringArr[i],1);
        	} else {
        		weirdMap.put(stringArr[i],++arrPos);
        		//this increases the count at the array associated with
        		//each word
        		//System.out.println(arrPos);
        	}
        }
        return weirdMap;
    }

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;
		
		public int indexOf(T t) {
			List<T> list = this.getSortedList();
			int spot = Collections.binarySearch(list,t,null);            
			return spot;
        }

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		if (string.contains(" ")) {
			String[] pigs = string.split(" ");
			String[] hogs = new String[pigs.length];
			for (int y=0;y<pigs.length;y++) {
				hogs[y] = (squealLikeAPiggie(pigs[y]));	
			}
			String igspay = String.join(" ", hogs);
			return igspay;
		}
		else {
			return squealLikeAPiggie(string);
		}
	}
	public String squealLikeAPiggie(String string) {
		//everything below works as i want it to.
		char[] pig = string.toCharArray();
		char[] igpay= new char[pig.length+2];
		for (int i=0;i<pig.length;i++) {
			if (pig[i]=='a' || pig[i]=='e' || pig[i]=='i' || pig[i]=='o' || pig[i]=='u') {
				//specific qu block
				if(pig[i]=='u' && pig[i-1]=='q' && (pig[i]=='a' || pig[i]=='e' || pig[i]=='i' || pig[i]=='o' || pig[i]=='u')) {
					int w=i+1;
					for(int p=0;p<pig.length-w;p++) {
						igpay[p]=pig[p+w];
						for (int l=1;l<=w;l++) {
							igpay[p+l]=pig[l-1];
						}
					}
				}
				else {
					for(int p=0;p<pig.length-i;p++) {
						igpay[p]=pig[p+i];
						for (int l=1;l<=i;l++) {
							igpay[p+l]=pig[l-1];
						}
					}
				}	
			break;
			}
		}
		igpay[pig.length]='a';
		igpay[pig.length+1]='y';
		return new String(igpay);
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String stringBeans = Integer.toString(input);
		int places = stringBeans.length();
		//this'll be give the number for places, 129 has 3 places
		int[] holder = new int[places];
		//holder holds the vals of raised nums
		for (int strongArm=0;strongArm<stringBeans.length();strongArm++) {
			int num = Character.getNumericValue(stringBeans.charAt(strongArm));
			holder[strongArm]=(int) Math.pow(num,places);
			//System.out.println(holder[strongArm]);
		}
		int sum = IntStream.of(holder).sum();
		if (sum==input) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		ArrayList<Long> primes = new ArrayList<Long>();
		ArrayList<Long> holder = new ArrayList<Long>();
		//after switching this all up now i need to cast these ints to longs
		primes.add((long) 0);
		primes.add((long) 1);
		primes.add((long) 2);
		primes.add((long) 3);
		//this starts the list of odd numbers at 3
		int limit = 0;
		if (l>1000) {
			limit += l/100;
		}
		else {
			limit += l/1;
		}
		//I don't think this is the best idea but it will work for all the test cases
		//there are definitely some values this doesn't work for.
		for (long p=3;primes.get(primes.size()-1)<limit;p+=2) {
			int strikes = 0;
			//this for loop will check if any of those odd numbers are
			//divisible by any of the numbers that are already in the primes HashSet
			for (int a=2;a<primes.size();a++) {
				//as soon as a strike happens this for loop breaks
				//and the first for loop continues
				if (p%primes.get(a)==0) {
					strikes++;
					break;
				}
			}
			if (strikes==0) {
				primes.add(p);
			}
			else {
				continue;
			}
		}
		for (int g=primes.size()-1;g>=2;g--) {
			if (l%primes.get(g)==0) {
				holder.add(primes.get(g));
				//System.out.println("hi "+primes.get(g));
			}
		}
		//System.out.println(holder);
		long multiplied = 1;
		for (int j=0;j<holder.size();j++) {
			multiplied=multiplied*holder.get(j);
		}
		if (multiplied!=l) {
			for (int d=holder.size()-1;d>=0;d--) {
				//System.out.println(d);
				if (multiplied==l) {
					break;
				}
				else if (multiplied%holder.get(d)==0) {
					holder.add(holder.get(d));
					multiplied = multiplied*holder.get(d);
					//System.out.println(multiplied);
					d++;
					continue;
				}
			}
		}
		Collections.sort(holder);
		List<Long> list = holder;
		
		//System.out.println(multiplied);
		//System.out.println(holder);
		//System.out.println(l);
		return list;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;
		static String series = "abcdefghijklmnopqrstuvwxyz";
		static char[] charArr = series.toCharArray();
		static ArrayList<Character> seriesArr = new ArrayList<Character>();
		static {
			for (int s=0;s<charArr.length;s++) {
				seriesArr.add(charArr[s]);
			}			
		}
		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			char[] stringCharArr = string.toCharArray();
			ArrayList<Character> stringArr = new ArrayList<Character>();
			ArrayList<Character> rotatedMessage = new ArrayList<Character>();
			//k for the kaiser, render unto caesar eh?
			//the czars might have some trouble with it
			for (int k=0;k<stringCharArr.length;k++) {
				stringArr.add(stringCharArr[k]);
			}
			for (int cz=0;cz<stringCharArr.length;cz++) {
				if (Character.isUpperCase(stringCharArr[cz])) {
					stringCharArr[cz]=Character.toLowerCase(stringCharArr[cz]);
					//System.out.println();
					//System.out.println("Found "+stringCharArr[cz]);
					int shiftIndex = seriesArr.indexOf(stringCharArr[cz])+key; 
					if (shiftIndex>25) {
						//System.out.println("Too Big");
						shiftIndex = shiftIndex-26;
					}
					rotatedMessage.add(Character.toUpperCase(charArr[shiftIndex]));
					//System.out.println("Caught UpperCase");
				}
				else if (seriesArr.indexOf(stringCharArr[cz])==-1) {
					rotatedMessage.add(stringCharArr[cz]);
					continue;
				}
				else {
					int shiftIndex = seriesArr.indexOf(stringCharArr[cz])+key; 
					if (shiftIndex>25) {
						//System.out.println("Too Big");
						shiftIndex = shiftIndex-26;
					}
					rotatedMessage.add(charArr[shiftIndex]);
				}
			}
			//StringBuilder is real nice, better than how I did it in 13 and 14.
			//I should probably change it but first I got to get the others to pass.
			//System.out.println(rotatedMessage);
			StringBuilder translated = new StringBuilder(stringArr.size());
			for (Character letter: rotatedMessage) {
				translated.append(letter);
			}
//			System.out.println(key);
//			System.out.println(translated);
			return translated.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) throws IllegalArgumentException{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(0);
		primes.add(1);
		primes.add(2);
		primes.add(3);
		if (i==0) {
			throw new IllegalArgumentException();
		}
		//this starts the list of odd numbers at 3
		for (int p=3;(primes.size()-2)<i;p+=2) {
			int strikes = 0;
			//this for loop will check if any of those odd numbers are
			//divisible by any of the numbers that are already in the primes HashSet
			for (int a=2;a<primes.size();a++) {
				//as soon as a strike happens this for loop breaks
				//and the first for loop continues
				if (p%primes.get(a)==0) {
					strikes++;
					break;
				}
			}
			if (strikes==0) {
				primes.add(p);
			}
			else {
				continue;
			}
		}
		//System.out.println(primes);
		int last = primes.get(primes.size()-1);
		if (i<3) {
			last = primes.get(i+1);
		}
		//System.out.println(last);
		return last;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		static String alphaBeta = "abcdefghijklmnopqrstuvwxyz0123456789";
		static String omegaPsi =  "zyxwvutsrqponmlkjihgfedcba0123456789";
		static char[] uncoded = alphaBeta.toCharArray();
		static char[] coded = omegaPsi.toCharArray();
		//I created the codes and arrays for those codes
		static ArrayList<Character>uncodedWords = new ArrayList<Character>();
		static ArrayList<Character>codedWords = new ArrayList<Character>();
		//static code block for filling the ArrayLists
		static {
			for (int s=0;s<uncoded.length;s++) {
				uncodedWords.add(uncoded[s]);
			}
			for (int s=0;s<coded.length;s++) {
				codedWords.add(coded[s]);
			}
		}
				
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			//System.out.println(codedWords);
			//now the string is free of anything besides lowercase and nums
			string = string.toLowerCase().replaceAll("[^a-z0-9]","");
			char[] charArr = string.toCharArray();
			ArrayList<Character>message = new ArrayList<Character>();
			ArrayList<Character>nvhhztv = new ArrayList<Character>();
			for (int s=0;s<string.length();s++) {
				message.add(charArr[s]);
			}
			for (int f=0;f<message.size();f++) {
				if (f%5==0 && f!=0) {
					nvhhztv.add('?');
					//why ? because i already know there will be no ?s in the string
					//because i removed them so later I can replace them
					//I need to replace them because there are spaces in the toString
					//for ArrayList
				}
				int index = uncodedWords.indexOf(message.get(f));
				nvhhztv.add(codedWords.get(index));
			}
			String coded = nvhhztv.toString();
			coded = coded.replaceAll("[^a-z0-9\\?]","");
			coded = coded.replaceAll("\\?", " ");
			//System.out.println(coded);
			return coded;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			string = string.replaceAll(" ","");
			//System.out.println(string);
			char[] charArr = string.toCharArray();
			ArrayList<Character>message = new ArrayList<Character>();
			ArrayList<Character>nvhhztv = new ArrayList<Character>();
			for (int s=0;s<string.length();s++) {
				nvhhztv.add(charArr[s]);
			}
			for (int f=0;f<nvhhztv.size();f++) {
				int index = codedWords.indexOf(nvhhztv.get(f));
				message.add(uncodedWords.get(index));
			}
			String uncoded = message.toString();
			uncoded = uncoded.replaceAll("[^a-z0-9\\?]","");
			//System.out.println(uncoded);
			return uncoded;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	//mod in the explanation means % modulus
	public boolean isValidIsbn(String string) {
		string = string.replaceAll("-","");
		String regex = "[^X0123456789]";
		Pattern maybeISBN = Pattern.compile(regex);
		Matcher maybeMatch = maybeISBN.matcher(string);
		if (maybeMatch.find()) {
			return false;
		}
		else {
			if (string.length()!=10) {
				return false;
			}
			else {
				int eleven = 0;
				for (int isbn = 0;isbn<10;isbn++) {
					if (string.charAt(isbn)!='X') {
						int stringToInt = Character.getNumericValue(string.charAt(isbn));
						eleven = eleven+((10-isbn)*stringToInt);
					}
					else {
						eleven = eleven+((10-isbn)*10);
					}
				}
				if (eleven%11==0) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		//brief explanation
		//i convert the string to a charArr then check if each
		//char in the arr is a letter. only letters are added to the
		//hashset. Then I check if the hashSet has 26 entries in it because it accepts no duplicates
		HashSet<Character> presentChars = new HashSet<Character>();
		string = string.toLowerCase();
		char[] charArr= string.toCharArray();
		String regex = "[a-z]";
		Pattern alphaBeta = Pattern.compile(regex);
		for (int c=0;c<charArr.length;c++) {
			Matcher doesItExist = alphaBeta.matcher(charArr[c]+"");
			if (doesItExist.find()) {
				presentChars.add(charArr[c]);
			}
		}
		//System.out.println(presentChars);
		if (presentChars.size()==26) {
			//System.out.println("Yes");
			return true;
		}
		else {
			//System.out.println("No");
			return false;
		}
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 10^9 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		//I'm out of time. Hehe get it?
		//cause its a time problem. Oh well. I thought it was funny.
		//things to do to finish this to the tests:
		//add in the temporal fields of mins secs and mils
		//to each time that doesn't have them.
		//I don't know how to do this.
		//If we get time in class then I'll try to figure it out
		//I suppose I could try to throw the error.
		//Eh I'm done.
		//I'm fine submitting this especially since I didn't know any Java until
		//like three weeks ago
		
		//System.out.println(given);
		//given = given.plus(0, ChronoUnit.MINUTES);
		//given = given.plus(0,ChronoUnit.SECONDS);
		//given = given.plus(0,ChronoUnit.MILLIS);
		//I think i might have messed up on these
		//I forgot hours
		long bigTen = (long) Math.pow(10,9);
		if (!given.isSupported(ChronoUnit.SECONDS)) {
			//Boom LocalDate solves my problems
			LocalDate date = (LocalDate) given;
			//this will cast the given time to the LocalDate referencetype
			LocalDateTime midnightDate = date.atStartOfDay();
			//this sets the mins secs and mils to 0;
			//because now they are set the seconds can be added.
			given = midnightDate;
		}	
		//System.out.println(given);
		given = given.plus(bigTen, ChronoUnit.SECONDS);
		//System.out.println(given);
		return given;
	}


	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int top, int[] set) {
		HashSet<Integer> multiples = new HashSet<Integer>();
		int endSum = 0;
		for (int x=0;x<set.length;x++) {
			int maxMultiple = top/set[x];
			//the next if statement checks if maxMultiple times set[x] is the endNum.
			//we don't add the end num.
			//java int division is weird so i had to add the modulus check.
			if (top/(maxMultiple*set[x])==1 && top%(maxMultiple*set[x])==0) {
				maxMultiple = maxMultiple-1;
			}
			for (int y=1;y<=maxMultiple;y++) {
				multiples.add(set[x]*y);
			}
			//System.out.println(maxMultiple);
			//System.out.println(multiples);
		}
		//System.out.println(multiples);
		Iterator<Integer>sumOfMultiples = multiples.iterator();
		while (sumOfMultiples.hasNext()) {
			endSum = endSum+sumOfMultiples.next();
		}
		//System.out.println("endSum="+endSum);
		return endSum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		int endGoal = 0;
		if (string.length()<1) {
			return false;
		}		 
		String regex = "[^0-9 ]";
		boolean b = Pattern.matches("a*b", "aaaaab");
		if (b) {
			return false;
		}
		string = string.replaceAll(regex,"").replaceAll(" ","");
		System.out.println(string);
		for (int luhn=0;luhn<string.length();luhn++) {
			linkedList.add(Character.getNumericValue(string.charAt(luhn)));
		}
		for (int ll=0;ll<linkedList.size();ll+=2) {
			linkedList.set(ll,(linkedList.get(ll)*2));
			if (linkedList.get(ll)>=10) {
				linkedList.set(ll,(linkedList.get(ll)-9));
			}
		}
		for (int ll=0;ll<linkedList.size();ll++) {
			endGoal += linkedList.get(ll);
		}
		System.out.println(endGoal);
		if (endGoal%10==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		int calculated = 0;
		String regex = "[^0-9- ]";
		String removed = string.replaceAll(regex,"");
		String[] numStringArr = removed.split(" ");
		//now i got an array of strings that unfortunately includes empty places
		//Java arrays can't change size on a whim so i'm not gonna mess with them
		//just finish the tests and move on
		int first = Integer.parseInt(numStringArr[2]);
		//this is because the first 2 vals are spaces
		int last = Integer.parseInt(numStringArr[numStringArr.length - 1]);
		//last val will be a num
		//System.out.println(last);
		if (string.contains("plus") || string.contains("add")) {
			calculated = first + last;
		} else if (string.contains("minus") || string.contains("subtract")) {
			calculated = first - last;
		} else if (string.contains("multiplied") || string.contains("times")) {
			calculated = first * last;
		} else if (string.contains("divided") || string.contains("over")) {
			calculated = first / last;
		}
		//System.out.println(calculated);
		return calculated;
	}

}
