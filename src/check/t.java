package check;

import java.io.FileWriter;
import java.io.IOException;

public class t {
    public static void main(String[] args) {
        String str="张三 0 3000 李四 1 5000 王五 0 4000";
        FileWriter writer;
        try {
            writer = new FileWriter("e:\\3.txt");
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
