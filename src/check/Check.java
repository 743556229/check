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
	
	//��ȡ�𰸵�����
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
	
	//����excam�ļ�����ѧ������
	public String[] ergodicStudent(){
		File file=new File("e:\\excam");
		String[] fileName=file.list();
		return fileName;
	}
	
	//����readFile���������ѧ�������׼�𰸣����бȽϣ���óɼ�
	public int check(String name){
		int count=0;
		int grade;
		List <String>result=new ArrayList<String>();
		result=readFile("e:\\result.txt");
		List <String>studentResult=new ArrayList<String>();
		studentResult=readFile("e:\\excam\\"+name);
		try {
			if(result.size()!=studentResult.size()){
				System.out.println("������ͬ�������²鿴");
			}else{		
				outer:for(int i=0;i<result.size();i++){
					for(int j=0;j<result.get(i).length();j++){
						if(studentResult.get(i).length()>result.get(i).length()){
							System.out.println("ѧ���ļ����г������ƣ���鿴");
							break outer;
						}
						else{
							char c=studentResult.get(i).charAt(j);
							char a=result.get(i).charAt(j);
							//��Сд����
							if((a==c)||(a==c-32)){
								count++;
							}
							
						}
					}
			}
				
			}
			//�����쳣������������
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("��ѧ���ı��м�����һ�հ��У�������ٴ��ύ");
		}
		grade=(count)*5;
		return grade;
		
	}
	
	public void getGrade(){
		String[] studentFileName=ergodicStudent();	
		for(String fName:studentFileName){
		System.out.println(fName.replace(".txt", "")+"�ĳɼ�Ϊ��"+check(fName));
		//������õ��ĳɼ�����grade.txt
		}
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("e:\\grade.txt"));
			for(String fName1:studentFileName){
				//ʹ��replace��������txtȥ����
			    out.write(fName1.replace(".txt", "")+"�ĳɼ�Ϊ��"+check(fName1)+"\r\n");
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
