package WorkDay_Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Myrunner {

	public static void main(String[] args) throws BiffException, IOException, WriteException 
	{
		
		// Connect to Excel File
		
		File f= new File("D:\\Selenium2018\\Selenium\\selenium2\\src\\testdata\\TestData.xls");
		
		Workbook rwb= Workbook.getWorkbook(f);
		
		Sheet rsh1= rwb.getSheet(0);
		
		int nour1= rsh1.getRows();
		
		int nouc1= rsh1.getColumns();
		
		Sheet rsh2= rwb.getSheet(1);
		
		int nour2= rsh2.getRows();
		
		int nouc2= rsh2.getColumns();
		
		//Open same Excel to write in it
		
		WritableWorkbook wwb= Workbook.createWorkbook(f,rwb);
		
		WritableSheet wsh1= wwb.getSheet(0);
		
		WritableSheet wsh2= wwb.getSheet(1);
		
		WritableCellFormat cf= new WritableCellFormat();
		
		cf.setAlignment(Alignment.JUSTIFY);
		
		cf.setWrap(true);
		
		Date dt= new Date();
				
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		
		String cname= df.format(dt);
		
		
		// Set name to result column in Sheet1
		
		Label l1= new Label(nouc1,0,cname,cf);
		
		wsh1.addCell(l1);
		
		
		//Set name to result column in Sheet2
		
		Label l2= new Label(nouc2,0,cname,cf);
		
		wsh1.addCell(l2);
		
		Mymethods mm= new Mymethods();
		
		// collect methods info using Method class Object
		
		Method m[]= mm.getClass().getMethods();
		
		
		// Keyword Driven Testing
		
		try
		{
			// calling methods one after other
			
			for(int i=1; i<nour1;i++)
			{
				int flag=0;
				
				String tid= rsh1.getCell(0, i).getContents();
				
				String mode= rsh1.getCell(2, i).getContents();
				
				if(mode.equalsIgnoreCase("yes"))
				{
					for(int j=1;j<nour2;j++)
					{
						String sid= rsh2.getCell(0,j).getContents();
						
						if(tid.equalsIgnoreCase(sid))
						{
							String mn= rsh2.getCell(2, j).getContents();
							String e= rsh2.getCell(3, j).getContents();
							String d= rsh2.getCell(4, j).getContents();
							String c= rsh2.getCell(5, j).getContents();
							
							for(int k=0;k<m.length;k++)
							{
								if(m[k].getName().equals(mn))
									
								{
									String r= (String)m[k].invoke(mm,e,d,c);
									
									Label lb= new Label(nouc2,j,r,cf);
									wsh2.addCell(lb);
									
									if(r.equalsIgnoreCase("Invalid Browser name"))
									{
										wwb.write();
										wwb.close();
										rwb.close();
										System.exit(0);
									}
									if(r.equalsIgnoreCase("Failed") || r.contains("interrupted"))
									{
										flag=1;
									}
								}
							}
							
							
						}
					}
					if(flag==0)
					{
						Label l= new Label(nouc1,i,"Passed",cf);
						wsh1.addCell(l);
					}
					
					else
					{
						Label l= new Label(nouc1,i,"Failed",cf);
						wsh1.addCell(l);
					}
				}
				
				
				
				
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
		wwb.write();
		wwb.close();
		rwb.close();
	}
		

}
