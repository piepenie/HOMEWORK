package com.test;

import java.util.HashMap;
import java.util.Map;

//class Yesterday {
//	String name; // 멤버 변수
//	int age;
//	
//	public Yesterday(int age, String name) {
//		this.age = age;
//		this.name = name; 
//	}
//	
//	public void Info() {
//		System.out.println("나이 : " + age);
//		System.out.println("이름 : " + name);
//	}
//	
//	public void AgeIncrease() {
//		age++;
//	}
//}
//
//public class Prac0414 {
//	public static void main(String[] args) {
//		Yesterday person1 = new Yesterday(10,"name");
//		System.out.println("사람 정보 출력");
//		person1.Info();
//		
//		person1.AgeIncrease();
//		
//		System.out.println("나이가 증가된 사람 정보 출력");
//		person1.Info();
//		
//	}
//}


public class Prac0414 {
	public static void main(String[] args) {
		Map<String, Integer> hashMap = new HashMap<>();
		
		hashMap.put("apple", 1000);
		hashMap.put("banana", 500);
		hashMap.put("orange", 800);
		
		int applePrice = hashMap.get("apple");
		System.out.println("사과가격 : " + applePrice);
		
		System.out.println("맵의 크기 확인 : " + hashMap.size());
		
		
		
	}
}
