package application.Person;


public class Person {

	 private String name;
	 private String surname;
	 private String age;
	 private String sex;
	 private String location;
	 private String user;
	
	
	 public Person(String name, String surname, String age, String sex, String location, String user) {
	 super();
	 this.name = name;
	 this.surname = surname;
	 this.age = age;
	 this.sex = sex;
	 this.location = location;
	 this.user = user;
	 }
	
	
	 public String getName() {
	 return name;
	 }
	
	
	 public void setName(String name) {
	 this.name = name;
	 }
	
	
	 public String getSurname() {
	 return surname;
	 }
	
	
	 public void setSurname(String surname) {
	 this.surname = surname;
	 }
	
	
	 public String getAge() {
	 return age;
	 }
	
	
	 public void setAge(String age) {
	 this.age = age;
	 }
	
	
	 public String getSex() {
	 return sex;
	 }
	
	
	 public void setSex(String sex) {
	 this.sex = sex;
	 }
	
	
	 public String getLocation() {
	 return location;
	 }
	
	
	 public void setLocation(String location) {
	 this.location = location;
	 }


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

}
