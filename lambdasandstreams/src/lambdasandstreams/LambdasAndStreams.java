package lambdasandstreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Class to sort Strings by length ( ascending ) 
class StringLengthComparator implements Comparator {
	private StringLengthComparator() 
	{
		
	}
	public static final StringLengthComparator INSTANCE =
	new StringLengthComparator();
	public int compare(Object o1, Object o2)
	{
		String s1 = (String) o1, s2 = (String) o2;
		return s1.length() - s2.length();
	}
}

interface Vehicle{

   void printVehicle();
	
   static void blowHorn() {
      System.out.println("Blowing horn!!!");
   }
}

interface FourWheeler{

   void printFourWheeler(); 
}

class Car implements Vehicle, FourWheeler{
	
	public void printVehicle()
	{
		System.out.println("I am a vehicle!");
	}
	
	public void printFourWheeler()
	{
		System.out.println("I am a four wheeler!");
	}

	   public void print() {
	      printVehicle();
	      printFourWheeler();
	      Vehicle.blowHorn();
	      System.out.println("I am an old car!");
	   }
	}


interface VehicleDefault {

   default void print() {
      System.out.println("I am a vehicle!");
   }
	
   static void blowHorn() {
      System.out.println("Blowing horn!!!");
   }
}

interface FourWheelerDefault {

   default void print() {
      System.out.println("I am a four wheeler!");
   }
}

class CarDefault implements VehicleDefault, FourWheelerDefault {

   public void print() {
      VehicleDefault.super.print();
      FourWheelerDefault.super.print();
      VehicleDefault.blowHorn();
      System.out.println("I am a car with default interfaces!");
   }
}

public class LambdasAndStreams {
	
	// Old way of creating Function Objects in Java 
	public static void oldFunctionObjectSort(String[] words)
	{
		Arrays.sort(words, StringLengthComparator.INSTANCE);
	}
	
	public static void print(String title, String[] words)
	{
		System.out.println(title);
		for(String word : words)
		{
			System.out.print(word+"("+word.length()+") ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		String[] words  = {"Hello", "How", "are", "you", "doing"};
		
		LambdasAndStreams.print("Before Sort:", words);		
		
		// Sorting by length 1.00
		LambdasAndStreams.oldFunctionObjectSort(words);
		
		LambdasAndStreams.print("After Sort:", words);
		
		String[] words2  = {"Slightly", "better", "way", "of", "sorting"};
		
		LambdasAndStreams.print("Before Sort:", words2);	
		
		Arrays.sort(words2, new Comparator() {
			public int compare(Object o1, Object o2) 
			{
				String s1 = (String) o1, s2 = (String) o2;
				return s1.length() - s2.length();
			}
		});
		
		LambdasAndStreams.print("After Sort:", words2);
		
		
		String[] words3  = {"The", "rise", "of", "the", "Generics"};
		
		LambdasAndStreams.print("Before Sort:", words3);	
		
		Arrays.sort(words3, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		
		LambdasAndStreams.print("After Sort:", words3);
		
		
		String[] words4  = {"Welcome", "to", "the", "world", "of", "Lambdas"};
		
		LambdasAndStreams.print("Before Sort:", words4);	
		
		Arrays.sort(words4,
				(s1, s2) -> s1.length() - s2.length());
		
		LambdasAndStreams.print("After Sort:", words4);
		
		
		// Streams 
		
		
		List<String> stringList = Arrays.asList(words4);
		
		// Iteration over a collection
		stringList.stream().forEach(System.out::println);
		
		// Iteration over a range of integers
		IntStream.range(0, 10).forEach(System.out::println);
		
		// Creates an IntStream
		"Hello world!".chars().forEach(System.out::print);
		System.out.println();
		
		// Combine Streams with Lambdas
		"Hello world!".chars().forEach(x -> System.out.print((char) x));
		System.out.println();
		
		"Hello world!".chars().mapToObj(x -> (char)x).forEach(System.out::print);
		System.out.println();
		System.out.println();
		
		List<String> filteredList = stringList.stream().filter(s -> s.length() > 3)
				.collect(Collectors.toList());
		
		filteredList.stream().forEach(System.out::println);
		
		System.out.println();
		
		
		List<String> mappedList = stringList.stream()
				.map(s -> s.substring(0,1))
				.collect(Collectors.toList());
		
		mappedList.stream().forEach(System.out::println);
		System.out.println();
		
				
		List<String> filteredMappedList =
				stringList.stream()
				.filter(s -> s.length() > 4)
				.map(s -> s.substring(0,1))
				.collect(Collectors.toList());
		
		filteredMappedList.stream().forEach(System.out::println);
		
		System.out.println();
		
		List<String> dupsRemoved = stringList.stream()
				.map(s -> s.substring(0,1))
				.distinct()
				.collect(Collectors.toList());
		
		dupsRemoved.stream().forEach(System.out::println);
		
		System.out.println();
		
		List<String> sortedList = stringList.stream()
				.map(s -> s.substring(0,1))
				.sorted() // Buffers everything until terminal op
				.collect(Collectors.toList());
		
		sortedList.stream().forEach(System.out::println);
		
		System.out.println();
		
		// Using Interfaces
		Vehicle vehicle = new Car();
	    ((Car)vehicle).print();
	    
	    System.out.println();
		
		
		// Default Methods 
		VehicleDefault vehicleDef = new CarDefault();
	    vehicleDef.print();
	    
	    System.out.println();
		
		
		
	}

}
