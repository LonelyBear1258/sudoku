package com.csh.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author csh
 */
public class Utils {

    private static final int RCNUM = 9;
    private static final int NUMAREA1 = 1;
    private static final int NUMAREA2 = 2;
    private static final int NUMAREA3 = 3;

    public static List<Integer> getRCDefectNum(int[][] table,int num,boolean flag){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= RCNUM; i++) {
            list.add(i);
        }
        List<Integer> tmp = new ArrayList<>();
        //flag为true返回行缺失的数字，false返回列缺失的数字
        if (flag){
            for (int i = 0; i < RCNUM; i++) {
                tmp.add(table[num][i]);
            }
        }else {
            for (int i = 0; i < RCNUM; i++) {
                tmp.add(table[i][num]);
            }
        }
        list.removeAll(tmp);
        return list;
    }

    public static List<Integer> getAreaDefectNum(int[][] table,int row,int col){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= RCNUM; i++) {
            list.add(i);
        }
        row = getStartNum(row);
        col = getStartNum(col);
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < NUMAREA3; i++) {
            for (int j = 0; j < NUMAREA3; j++) {
                tmp.add(table[row+i][col+j]);
            }
        }
        list.removeAll(tmp);
        return list;
    }





    public static int getStartNum(int num){
        double flag = num/3.0;
        if (flag <= NUMAREA1){
            return 0;
        }else if (flag > NUMAREA1 && flag <= NUMAREA2){
            return 3;
        }else {
            return 6;
        }
    }

    public static void main(String[] args) {

        int[][] table = new int[9][9];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < RCNUM; i++) {
            String str = sc.nextLine();
            String[] cols = str.split(" ");
            for (int j = 0; j < RCNUM; j++) {
                table[i][j] = Integer.valueOf(cols[j]);
            }
        }
        System.out.println(Arrays.deepToString(table));
        System.out.println(getAreaDefectNum(table,0,0));
        List<Integer> rcDefectNum = getRCDefectNum(table, 0, true);
        List<Integer> rcDefectNum1 = getRCDefectNum(table, 0, false);
        System.out.println(rcDefectNum);
        System.out.println(rcDefectNum1);
        rcDefectNum.retainAll(rcDefectNum1);
        System.out.println(rcDefectNum);

    }

}
