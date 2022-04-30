package P1;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MagicSquare {

    public static void main(String[] args) throws IOException {
        String[] fileNames = { "1.txt", "2.txt", "3.txt", "4.txt", "5.txt"};
        MagicSquare magicSquare = new MagicSquare();
        for(String fileName:fileNames)
        {
            System.out.println("fileName = " + fileName +": " +magicSquare.isLegalMagicSquare(fileName));
        }
    }
    public boolean isLegalMagicSquare(String fileName) throws IOException {
        fileName = "src/P1/txt/" + fileName;
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        String myLine;
        String[] list;
        if(in.hasNextLine()) {
            myLine= in.nextLine();
            list = myLine.split("\t");
        }
        else {
            System.out.print("空文件输入 ");
            in.close();
            return false;
        }
        int length = list.length;
        int[][] square = new int[length][length];
        int[] rowSum = new int[length];//每行的sum
        int[] colSum = new int[length];
        int sum1 = 0, sum2 = 0, i, j;
        try {
            for (i = 0, j = 0; in.hasNextLine(); i++, j = 0)
            {
                for (String u : list) {
                    square[i][j] = Integer.parseInt(u);
                    colSum[j] += square[i][j];
                    rowSum[i] += square[i][j++];
                }
                myLine = in.nextLine();
                list = myLine.split("\t");
            }
            //in.nextline==null
            in.close();
            //处理最后一行
            for (String u : list) {
                square[i][j] = Integer.parseInt(u);
                colSum[j] += square[i][j];
                rowSum[i] += square[i][j++];
            }
            //
            if(i != length-1) { //？？？？？？？？？？？？？？？？？？？？行数不等于列数
                System.out.println("行列数不相等 ");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.println("含有非正整数或未用\"\\t\"分割  ");
            return false;
        } catch(ArrayIndexOutOfBoundsException ex) { //列数小于行数造成数组越界
            System.out.println("行列数不相等 ");
            return false;
        }
        int sum = rowSum[0];//累加和计为sum
        for(i=0;i<length;i++)
        {
            if(rowSum[i]!=sum || colSum[i]!=sum)
            {
                System.out.println("行列数字和不相等");
                return false;
            }
        }
        for (i = 0; i < length; i++) {
            sum1 = sum1+square[i][i];
            j=length-1-i;
            sum2 = sum2+square[i][j];
        }
        if (sum1!=sum||sum2!=sum) {
            System.out.println("对角线的数字和不相等 ");
            return false;
        }
        return true;
    }
    //它如何根据输入的参数（奇数 n）生成一
    //个 n×n 的 Magic Square
    public static boolean generateMagicSquare(int n) {
        if(n%2==0 || n<0)
            return false;//要求不能负数，不能非奇数
        int [][]magic = new int[n][n];          //数组
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            } }
        PrintStream out = System.out;
        try {
            System.setOut(new PrintStream("src/P1/txt/6.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (i = 0; i < n; i++) { // 打印MagicSquare
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }
        System.setOut(out);
        return true;
    }
}
