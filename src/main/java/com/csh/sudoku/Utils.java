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
        //System.out.println("start row and col:"+row+","+col);
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
        if (flag < NUMAREA1){
            return 0;
        }else if (flag >= NUMAREA1 && flag < NUMAREA2){
            return 3;
        }else {
            return 6;
        }
    }

    public static List<Integer> getDefectNum(int[][] table, int row, int col){
        List<Integer> rowNums = getRCDefectNum(table, row, true);
        List<Integer> colNums = getRCDefectNum(table, col, false);
        List<Integer> areaNums = getAreaDefectNum(table, row, col);
        rowNums.retainAll(colNums);
        rowNums.retainAll(areaNums);
        return rowNums;
    }

    public static void printTable(int[][] table){
        for (int i = 0; i < RCNUM; i++) {
            for (int j = 0; j < RCNUM; j++) {
                System.out.print(table[i][j]);
                if (j<8) {
                    System.out.print(" ");
                }
            }
            System.out.println();
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
        List<Integer> flagList = new ArrayList<>();
        flagList.add(0);
        while(flagList.contains(0)){
            flagList.clear();
            for (int i = 0; i < RCNUM; i++) {
                for (int j = 0; j < RCNUM; j++) {
                    if (table[i][j] == 0){
                        List<Integer> defectNum = getDefectNum(table, i, j);
                        System.out.println(i+","+j+":"+defectNum);
                        if (defectNum.size() == 1){
                            table[i][j] = defectNum.get(0);
                        }
                    }
                    flagList.add(table[i][j]);
                }
            }
            System.out.println(flagList);
            printTable(table);
        }
    }

}
