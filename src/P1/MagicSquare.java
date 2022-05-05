package P1;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MagicSquare {

    public static void main(String[] args) throws IOException {
        //测试generateMagicSquare
        generateMagicSquare(7);
        String[] fileNames = { "1.txt", "2.txt", "3.txt", "4.txt", "5.txt","6.txt"};
        MagicSquare magicSquare = new MagicSquare();
        for(String fileName:fileNames)
        {
            System.out.println("fileName文件名 = " + fileName +"  是否为幻方: " +magicSquare.isLegalMagicSquare(fileName));
        }
    }
    public boolean isLegalMagicSquare(String fileName) throws IOException {
        fileName = "src/P1/txt/" + fileName;
        File file = new File(fileName);
        Scanner in = new Scanner(file);//准备输入
        String line;
        String[] list;
        //利用第一行hasNextLine判断是否为空文件
        if(in.hasNextLine()) {
            line= in.nextLine();
            list = line.split("\t");
        }
        else {
            System.out.println("文件为空\t");
            in.close();
            return false;
        }
        int length = list.length;
        int[][] magic = new int[length][length];
        int[] rowSum = new int[length];//每行的sum
        int[] colSum = new int[length];
        int i, j;
        try {
            for (i = 0, j = 0; in.hasNextLine(); i++, j = 0)
            {
                if(length != list.length)//接下来这一行是不是和第一行length一样
                {
                    System.out.print("原因:（各行的列数并不完全相等）并非矩阵\t");
                    return false;
                }
                for (String num : list) {
                    if(Integer.parseInt(num)<0)
                    {
                        System.out.print("原因:矩阵某些数字非正整数");
                        return false;
                    }
                    magic[i][j] = Integer.parseInt(num);
                    colSum[j] += magic[i][j];
                    rowSum[i] += magic[i][j++];
                }
                line = in.nextLine();
                list = line.split("\t");
            }
            //in.nextline==null
            in.close();
            //处理最后一行
            for (String u : list) {
                magic[i][j] = Integer.parseInt(u);
                colSum[j] += magic[i][j];
                rowSum[i] += magic[i][j++];
            }
            //
            if(i != length-1) {
                System.out.print("原因:（行数不等于列数）行列数不相等\t");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.print("原因:整数形式不规范（矩阵某些数字非正整数）或未用\"\\t\"分割\t");
            return false;
        } catch(ArrayIndexOutOfBoundsException ex) {
            System.out.print("原因:（列数小于函数导致数组越界）行列数不相等\t");
            return false;
        }
        int sum = rowSum[0];//累加和计为sum
        for(i=0;i<length;i++)
        {
            if(rowSum[i]!=sum || colSum[i]!=sum)
            {
                System.out.print("原因:行列数字和不相等\t");
                return false;
            }
        }
        int sum1=0;
        int sum2=0;
        for (i = 0; i < length; i++) {
            sum1 = sum1+magic[i][i];
            j=length-1-i;
            sum2 = sum2+magic[i][j];
        }
        if (sum1!=sum||sum2!=sum) {
            System.out.print("原因:对角线的数字和不相等\t");
            return false;
        }
        return true;
    }
    //它如何根据输入的参数（奇数 n）生成一
    //个 n×n 的 Magic Square
    public static boolean generateMagicSquare(int n) throws FileNotFoundException {
        if(n%2==0 || n<0)
        {
            System.out.println(n+"未满足要求，不能负数，不能非奇数" );
            return false;//要求不能负数，不能非奇数
        }

        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++)
        {
            magic[row][col] = i;//把1放在这一行正中间
            if (i % n == 0)//如果是是n的倍数（包括0），如果是则下一行
                row++;
            else
            {//如果是n的倍数（包括0），如果不是
                if (row == 0)//如果是第一行，跳转最后一行
                    row = n - 1;
                else//如果不是第一行，跳转上一行
                    row--;


                if (col == (n - 1))//如果是最后一列，则跳到第一列
                    col = 0;
                else// 如果不是最后一列，跳到下一列
                    col++;
            }
        }
        //System.setOut()方法可以改变输出流
        PrintStream out = System.out;//保存原来控制台out临时标量
        //修改输出流
        System.setOut(new PrintStream("src/P1/txt/6.txt"));
        for (i = 0; i < n; i++) { // 打印MagicSquare
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }
        System.setOut(out);
        return true;
    }
}
