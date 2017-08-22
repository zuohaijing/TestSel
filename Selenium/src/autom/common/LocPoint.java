package autom.common;

import org.openqa.selenium.WebElement;

public class LocPoint {
//	private String PointName;
	protected int x1;
	protected int y1;
	protected int a;
	protected int b;
	
	public LocPoint(WebElement oWebEle){
		x1=oWebEle.getLocation().x+2;
		y1=oWebEle.getLocation().y+2;
	}
	public LocPoint(){
	}
	
	public LocPoint(String sName){
//		PointName=sName;
	}
	
	public LocPoint(int x, int y){
		x1=x;
		y1=y;
	}
	
	public int GetAdjustedX(){
		a=x1+4;
		return a;
	}   
	public int GetAdjustedY(){
		b=y1+4;
		return b;
	}   
	
}
