package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name="OpencartLoginData")
	public String[][] getData() throws IOException {
		
		String path=".\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtilitiesClass xlutil =new ExcelUtilitiesClass(path);
		  int totalrows=xlutil.getRowCount("Sheet1");
		  int totalcols=xlutil.getCellCount("Sheet1", 1);
		  
		  String logindata[][]=new String[totalrows][totalcols]; // instance variable of class
		  
		  
		  if(totalrows>0) {
			  for(int i=1;i<=totalrows;i++) {
				  for(int j=0;j<totalcols;j++) {
					  logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
				  }
			  }
		  } 
		  
		  else {
			  System.out.println("No data found in the Excel sheet.");
		  }
		  
		  return logindata;
		  
		  
	}
	
	
}

	//Notes:
		//String[][] logindata = new String[totalrows][totalcols];
		//String logindata1[][]=new String[totalrows][totalcols];
		// What is difference between logindata and logindata1 ?
		// logindata is a local variable inside the getData() method,
		// it will be destroyed once the method execution is completed. 
		// logindata1 is an instance variable of the DataProviders class,
		// it can be accessed by any method within the class and retains
		// its value as long as the instance of the class exists.
		// In this case, since logindata1 is not being used outside the getData() method,
		//it may not be necessary to declare it as an instance variable. 
		//It can be declared as a local variable within the getData() method instead.
		
	
