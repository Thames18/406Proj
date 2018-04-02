//Jonathan Phu
//500776576
//March 21,2017
//CPS209

import java.util.Comparator;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;



public class Person implements Comparable<Person>, Comparator<Person>{
	//instance variables
	private String lastname;
	private String firstname;
	private String telephone;
	private String address;
	private String email;
	
	//constructor methods
	public Person(){
		
	}
	
	public Person(String last, String first, String telephone, String address, String email){
		lastname = last;
		firstname = first;
		this.telephone = telephone;
		this.address = address;
		this.email = email;
	}
	
	//get and set methods
	public String getFirst(){ return firstname;}
	public String getLast(){ return lastname;}
	public String getTelephone(){ return telephone;}
	public String getAddress(){ return address;}
	public String getEmail(){ return email;}
	
	public void setFirst(String first){ firstname = first;}
	public void setLast(String last){ lastname = last;}
	public void setTelephone(String telephone){ this.telephone = telephone;}
	public void setAddress(String address){ this.address = address;}
	public void setEmail(String email){ this.email = email;}


	public String toString(){
		//prints contact info
		return "WITH: " + firstname + " " + lastname + " " + telephone + " " + email;
	}
	
	//compares first names if last names are the same
	public int compareTo(Person person) {
		if (this.lastname.equals(person.lastname)){
			return this.firstname.compareTo(person.firstname);
		}
		return this.lastname.compareTo(person.lastname);
	}
	
	public int compare(Person a, Person b){
		return a.email.compareTo(b.email);
	}
		

	public int compareTelephone(Person a, Person b){
		return a.telephone.compareTo(b.telephone);
	}


}



//helper class
class Contacts {
	//list of contacts and iterator to search through linked list
	static LinkedList<Person> contacts = new LinkedList<Person>();
	static ListIterator<Person> search;
	
	
	public static Person FindPerson(String last, String first){
		//creates a temporary person object and checks if a contact exists
		Person tmp = new Person();
		tmp.setFirst(first);
		tmp.setLast(last);
		Person person;
		search = contacts.listIterator();
		while(search.hasNext()){
			person = search.next();
			if (person.compareTo(person) == 0){
				return person;
			}
		}
		return null;
	}
	
	public static Person FindPersonEmail(String email){
		//same as above using email instead
		Person tmp = new Person();
		tmp.setEmail(email);
		Person person;
		search = contacts.listIterator();
		while(search.hasNext()){
			person = search.next();
			if (person.compare(person, tmp) == 0){
				return person;
			}
		}
		return null;
	}
	
	public static Person FindPersonTelephone(String telephone){
		//same as above using telephone instead
		Person tmp = new Person();
		tmp.setTelephone(telephone);
		Person person;
		search = contacts.listIterator();
		while(search.hasNext()){
			person = search.next();
			if (person.compareTelephone(person, tmp) == 0){
				return person;
			}
		}
		return null;
	}


	//reads contacts file and adds to linked list
	 static void readContactsFile() throws Exception{
		//scanner for file
		Scanner read = new Scanner(new File("contacts.txt"));
		Person tmp = new Person();
		//gets number of records
		int records = Integer.parseInt(read.next());
		int count = 1;
		//iterates through sets of 5 lines until the count of sets is greater than # of records
		while(count <= records){
			String line = read.next();
			if (line.isEmpty())
				//throw if last name empty
				throw new Exception();
			tmp.setLast(line);
			line = read.next();
			tmp.setFirst(line);
			line = read.next();
			tmp.setAddress(line);
			line = read.next();
			tmp.setTelephone(line);
			if (line.isEmpty())
				throw new Exception();
			line = read.next();
			tmp.setEmail(line);
			contacts.add(tmp);
			count++;
		}		
		//sort contacts after
		Collections.sort(contacts);
	}
	
}


