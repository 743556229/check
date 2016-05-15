package check;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Check {
	
	//读取答案到集合
	public List readFile(String address){
		String txtLine;
		BufferedReader in;
		List <String>result=new ArrayList<String>();
		try {
			in=new BufferedReader(new FileReader(address));
			while((txtLine=in.readLine())!=null){
				result.add(txtLine);
			}	
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	//遍历excam文件，得学生数组
	public String[] ergodicStudent(){
		File file=new File("e:\\excam");
		String[] fileName=file.list();
		return fileName;
	}
	
	//调用readFile方法，获得学生答案与标准答案，进行比较，获得成绩
	public int check(String name){
		int count=0;
		int grade;
		List <String>result=new ArrayList<String>();
		result=readFile("e:\\result.txt");
		List <String>studentResult=new ArrayList<String>();
		studentResult=readFile("e:\\excam\\"+name);
		try {
			if(result.size()!=studentResult.size()){
				System.out.println("行数不同，请重新查看");
			}else{		
				outer:for(int i=0;i<result.size();i++){
					for(int j=0;j<result.get(i).length();j++){
						if(studentResult.get(i).length()>result.get(i).length()){
							System.out.println("学生文件单行超过限制，请查看");
							break outer;
						}
						else{
							char c=studentResult.get(i).charAt(j);
							char a=result.get(i).charAt(j);
							//大小写都可
							if((a==c)||(a==c-32)){
								count++;
							}
							
						}
					}
			}
				
			}
			//处理异常，若超过界限
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("该学生文本中间输入一空白行，请检查后再次提交");
		}
		grade=(count)*5;
		return grade;
		
	}
	
	public void getGrade(){
		String[] studentFileName=ergodicStudent();	
		for(String fName:studentFileName){
		System.out.println(fName.replace(".txt", "")+"的成绩为："+check(fName));
		//将计算得到的成绩存入grade.txt
		}
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("e:\\grade.txt"));
			for(String fName1:studentFileName){
				//使用replace方法将。txt去掉，
			    out.write(fName1.replace(".txt", "")+"的成绩为："+check(fName1)+"\r\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    public static void main(String[] args) {
		Check c=new Check();
		c.getGrade();
	}
}
