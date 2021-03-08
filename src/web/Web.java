package web;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Web {
	
	private ChromeDriver driver;
	private String dirCar = System.getProperty("user.dir")+"/records/car";
	private String dirBike = System.getProperty("user.dir")+"/records/bike";
	private String dirWalk = System.getProperty("user.dir")+"/records/walk";
	private String txt;
	
	private String[] details;
	
	private int i;
	
	public boolean start(String from, String goTo, int i) {
		this.i = i;
		
		boolean b = false;

		try {
			System.setProperty("webdriver.chrome.driver", new File("drivers/windows/89/chromedriver.exe").getAbsolutePath());
	        driver = new ChromeDriver();
		} catch (SessionNotCreatedException e) {
			try {
				System.setProperty("webdriver.chrome.driver", new File("drivers/windows/88/chromedriver.exe").getAbsolutePath());
		        driver = new ChromeDriver();
			}catch(SessionNotCreatedException e1) {
				System.setProperty("webdriver.chrome.driver", new File("drivers/windows/87/chromedriver.exe").getAbsolutePath());
		        driver = new ChromeDriver();
			}
		}
        
		
		driver.get("https://www.google.com/maps");
		
		driver.manage().window().maximize();
		
		do {
			try {
				driver.findElement(By.id("searchbox-directions")).click();
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			}
		} while (b);
		
		do {
			try {
				Thread.sleep(800);
				WebElement fromId = driver.findElement(By.id("sb_ifc51"));
				fromId.findElement(By.tagName("input")).sendKeys(from);
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			} catch (InterruptedException e) {
				b = true;
			}
		} while (b);
		
		do {
			try {
				Thread.sleep(800);
				WebElement goToId = driver.findElement(By.id("sb_ifc52"));
				goToId.findElement(By.tagName("input")).sendKeys(goTo);
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			} catch (InterruptedException e) {
				b = true;
			}
		} while (b);
		
		do {
			try {
				WebElement direction = driver.findElement(By.id("directions-searchbox-1"));
				direction.findElement(By.tagName("button")).click();
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			}
		} while (b);
		
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[22]/div[1]/div[2]/div[7]/div/button")).click();
			Thread.sleep(2000);
			if(!setSchedule(dirCar)) {
				driver.close();
				return false;
			}
				
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		do {
			try {
				WebElement bike = driver.findElement(By.className("directions-collapsing-mode-selector"));
				bike.findElement(By.tagName("button")).click();
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			}
		} while (b);
		
		try {
			Thread.sleep(2500);
			driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[22]/div[1]/div[2]/div[7]/div/button")).click();
			Thread.sleep(2000);
			setSchedule(dirBike);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		do {
			try {
				driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/button")).click();
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			}
		} while (b);
		
		try {
			Thread.sleep(2500);
			driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[22]/div[1]/div[2]/div[7]/div/button")).click();
			Thread.sleep(2000);
			setSchedule(dirWalk);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		txt+= from + "\t"+ goTo + "\n";
		
		try {
			Thread.sleep(2000);
			write(true);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		driver.close();
		
		return true;
	}
	
	private boolean setSchedule(String dir) {
		
		boolean b;
		int i=0;
		WebElement distance;
		
		do {
			try {
				i++;
				if(dir.equals(dirCar))
					distance = driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[8]/div/div[1]/div/div/div[5]/div[1]/div/div[1]/div[1]/div[2]/div"));
				else 
					distance = driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[8]/div/div[1]/div/div/div[5]/div[1]/div/div[3]/div[1]/div[2]"));
					
				
				txt += distance.getText()+"\t";
				
				b = false;
			} catch (NoSuchElementException e) {
				if(i==3)
					return false;
				b = true;
			}
		} while (b);
		
		WebElement duration;
		
		do {
			try {
				if(dir.equals(dirCar))
					duration = driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[8]/div/div[1]/div/div/div[5]/div[1]/div/div[1]/div[1]/div[1]/span[1]"));
				else 
					duration = driver.findElement(By.xpath("/html/body/jsl/div[3]/div[9]/div[8]/div/div[1]/div/div/div[5]/div[1]/div/div[3]/div[1]/div[1]"));
				
				
				txt += duration.getText()+"\t";
				
				b = false;
			} catch (NoSuchElementException e) {
				b = true;
			}
		} while (b);
		
		partialScreenShot(dir);
		
		return true;
		
	}
	
	private void partialScreenShot(String dir) {
		 try {
	            Robot robot = new Robot();
	            String format = "png";
	            String fileName;
	            if(dir.equals(dirCar))
	            	fileName = dir+"/car"+i+"." + format;
	            else if(dir.equals(dirBike))
	            	fileName = dir+"/bike"+i+"." + format;
	            else
	            	fileName = dir+"/walk"+i+"." + format;
	            
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            Rectangle captureRect = new Rectangle(screenSize.width / 2-240, screenSize.height / 2-230, screenSize.width / 2, screenSize.height / 2);
	            BufferedImage screenFullImage = robot.createScreenCapture(captureRect); 
	            ImageIO.write(screenFullImage, format, new File(fileName));
	            
	             
	        } catch (AWTException | IOException ex) {
	            System.err.println(ex);
	        }
	}
	
	private void write(boolean type) {
		txt = txt.replace("null", "");
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/src/records/cases.txt",type); 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txt);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void read(int i) {
		BufferedReader br;
		String elements[];
		String line;
		int j = 0;
		try {
			br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/records/cases.txt"));
			while (br.ready()) {
				line = br.readLine();
				elements = line.split("\t");
				details = elements;
				if(j==i) {
					break;
				}
					
				j++;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void read() {
		txt="";
		BufferedReader br;
		String elements[];
		String line;
		boolean repeated = false;
		boolean equal;
		try {
			br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/records/cases.txt"));
			while (br.ready()) {
				line = br.readLine();
				elements = line.split("\t");
				
				equal = elements[0].equals(details[0]) && elements[1].equals(details[1]) &&
						elements[2].equals(details[2]) && elements[3].equals(details[3]) &&
						elements[3].equals(details[3]) && elements[4].equals(details[4]) &&
						elements[5].equals(details[5]) && elements[6].equals(details[6]) &&
						elements[7].equals(details[7]);
				
				if(!equal)
					txt+=line + "\n";
				else if(repeated)
					txt+=line + "\n";
				else 
					repeated = true;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int cont() {
		BufferedReader br;
		int j = 0;
		try {
			br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/records/cases.txt"));
			while (br.ready()) {
				br.readLine();
				j++;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return j;
	}
	
	public void delete(int i) {
		
		File file = new File(dirCar+"/car"+i+".png");
		file.delete();
		file = new File(dirBike+"/bike"+i+".png");
		file.delete();
		file = new File(dirWalk+"/walk"+i+".png");
		file.delete();
		
		for(int j=i;j<cont()-1;j++) {
			file = new File(dirCar+"/Car"+(j+1)+".png");
			file.renameTo(new File(dirCar+"/car"+j+".png"));
			file = new File(dirBike+"/bike"+(j+1)+".png");
			file.renameTo(new File(dirBike+"/bike"+j+".png"));
			file = new File(dirWalk+"/walk"+(j+1)+".png");
			file.renameTo(new File(dirWalk+"/walk"+j+".png"));
		}
		
		read();
		write(false);
		
	}
	
	public void setDetails(int i) {
		read(i);
	}
	
	public String getDetails(int i) {
		return details[i];
	}
	
	public int getRecordSize() {
		return cont();
	}

}
