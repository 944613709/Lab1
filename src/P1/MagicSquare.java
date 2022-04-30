package P1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MagicSquare {

    public static void main(String[] args) throws IOException {
        String[] fileNames = { "1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "7.txt", "8.txt"};
        MagicSquare magicSquare = new MagicSquare();
        for(String fileName:fileNames)
        {
            System.out.println("fileName = " + fileName +": " +magicSquare.isLegalMagicSquare(fileName));
        }
    }
    public boolean isLegalMagicSquare(String fileName) throws IOException {
        //fileName = ".\\txt\\" + fileName1;
        File file = new File("fileName");
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
                System.out.print("行列数不相等 ");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.print("含有非正整数或未用\"\\t\"分割  ");
            return false;
        } catch(ArrayIndexOutOfBoundsException ex) { //列数小于行数造成数组越界
            System.out.print("行列数不相等 ");
            return false;
        }
        if(rowSum[0] != colSum[0]) {
            System.out.print("行列和不相等");
            return false;
        }
        for (i = 0; i < length - 1; i++) {
            if ((rowSum[i] != rowSum[0]) || (colSum[i] != colSum[0])) {
                System.out.print("行或列和不相等 ");
                return false;
            }
        }
        for (i = 0; i < length; i++) {
            sum1 += square[i][i];
        }
        for (i = 0, j = length - 1; i < length; i++, j--) {
            sum2 += square[i][j];
        }
        if (sum1 != sum2 || rowSum[0] != sum1) {
            System.out.print("对角线和不相等 ");
            return false;
        }
        return true;
    }
}
