package utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	public Locale locale = new Locale("en");
	public Faker faker = new Faker(locale);
	
	public static DataHelper getData() {
		return new DataHelper();
	}
	
	public String getFirstName() {
		return faker.address().firstName();
	}
	
	public String getLastName() {
		return faker.address().lastName();
	}
	
	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getCreditCard() {
		return faker.finance().creditCard();
	}
	
	public String getFullAddress() {
		return faker.address().fullAddress();
	}
	
	public String getCellPhone() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
}