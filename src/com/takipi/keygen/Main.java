package com.takipi.keygen;

import com.takipi.keygen.instructions.InstructionsBuilder;
import com.takipi.keygen.key.SecretKeyGenerator;
import com.takipi.keygen.net.TakipiCom;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		if (!(args.length == 2 || args.length == 3))
		{
			System.out.println("OverOps Key Generator - www.overops.com");
			System.out.println("=======================================");
			
			printUsage();
			
			return;
		}
		

		
		String username 	= args[0];
		String password 	= args[1];
		String baseURL = "";
		if  (args.length > 2)
			baseURL = args[2];
		
		if ((username.isEmpty()) || (password.isEmpty()))
		{
			System.err.println("Problem with parameters.");
			
			printUsage();
			
			return;
		}
		
		if (baseURL.isEmpty())
			baseURL="https://app.overops.com";
		
		String proxy = "";
		
		String keyPrefix = TakipiCom.generateKeyPrefix(username, password, baseURL);
		
		if (keyPrefix == null)
		{
			System.err.println("Contact hello@overops.com for help.");
			return;
		}
		
		String secretKey = SecretKeyGenerator.generateKey(keyPrefix); 
		
		InstructionsBuilder.buildInstructionsFile(username, secretKey, proxy);
		
		System.out.println("-- OverOps Key Generator ended.");
	}
	
	private static void printUsage()
	{
		System.out.println("Usage: USERNAME PASSWORD URL");
		System.out.println("Example: john@example.com Pa$$woRd http://localhost:8080");
		System.out.println("URL defaults to https://app.overops.com if left off" );
	}
}
