package com.users.services;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

public class EncryptionServiceImpl implements EncryptionService{
	
	@Autowired
	private StrongPasswordEncryptor strongEncryptor;

	@Override
	public String encryptString(String input) {
		return strongEncryptor.encryptPassword(input);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
