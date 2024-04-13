package com.test;

class Person {
	
	 int age;
	 String name;
	 String phoneNumber;
	 // 멤버 변수 선언. 여기서 전역변수로 바꾸려면 public 을 달아주면된다. 
	 //하지만, public으로 선언된 멤버 변수를 직접 접근하면 해당 변수에 어떤 값이든 할당할 수 있으므로 
	 //데이터 무결성과 캡슐화의 원칙을 위배할 수 있음. 
	 //따라서 주의해서 사용해야 합니다. 일반적으로는 멤버 변수를 private으로 선언하고, 
	 //외부에서 접근할 수 있도록 Getter와 Setter 메소드를 제공하는 것이 더 안전하고 좋은 
	 //프로그래밍 실천법
	
	 //생성자 정의
	public Person(String name, int age, String phoneNumber) {
		this.age = age;
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	
	// 정보 메서드 정의
	public void Info() {
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("전화번호 : " + phoneNumber);
	}
	
	
	// 나이 증가 메서드
	public void increaseAge() {
		age++; 
	}
	
}

public class Prac0413 {
	public static void main(String[] args) {
		
		Person person1 = new Person("John", 30, "010-321-1233");
		Person person2 = new Person("Alice", 25, "010-444-1323");
		
		System.out.println("person1의 정보");
		person1.Info();
		
		System.out.println("\nperson2의 정보"); // \n = 한줄 띄워주는거
		person2.Info();
		
		person1.increaseAge();
		
		System.out.println("\nperson1의 나이를 증가시킨 후 정보");
		person1.Info();

	}
}
