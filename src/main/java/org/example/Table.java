package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Table {
    public char[] text;
    public List<char[][]> charTables;

    Table (String text) {
        this.text= text.toCharArray();
        this.charTables = new ArrayList<>();
    }

    /**
     * builds all possible tables and writes text to them by rows
     */
    public Table generateCharTables () {
        for (int integer: getTablesWidths()){
            char[][] table = new char[integer][text.length/integer];
            int row=0, column = 0, index=0;
            while (row < table.length) {
                while (column < table[0].length) {
                    table[row][column] = text[index];
                    column++;
                    index++;
                }
                column=0;
                row++;
            }
            charTables.add(table);
        }
        return this;
    }

    /**
     * writes out tables by columns
     */
    public Table listTables() {
        for (char[][] tables : charTables) {
            System.out.println("Table size: " + tables[0].length + " * " + tables.length);
            int row = 0, column = 0;
            while (column < tables[0].length) {
                while (row < tables.length) {
                    System.out.print(tables[row][column]);
                    row++;
                }
                System.out.print('\n');
                row = 0;
                column++;
            }
            System.out.print('\n');
        }
        return this;
    }

    public Table listTablesAsString() {
        for (char[][] tables : charTables) {
            System.out.println("Table size: " + tables[0].length + " * " + tables.length);
            int row = 0, column = 0;
            while (column < tables[0].length) {
                while (row < tables.length) {
                    System.out.print(tables[row][column]);
                    row++;
                }
                row = 0;
                column++;
            }
            System.out.print("\n\n");
        }
        return this;
    }

    /**
     * writes out tables by columns to strings
     * @return list of strings
     */
    public List<String> mergeTablesToStrings() {
        return charTables.stream().map( chars -> {
            StringBuilder s = new StringBuilder(text.length);
            int row=0, column= 0;
            while (column < chars[0].length) {
                while (row< chars.length) {
                    s.append(chars[row][column]);
                    row++;
                }
                row=0;
                column++;
            }
            return s.toString();
        }).collect(Collectors.toList());
    }

    public char[][] getChartable( int index) {
        return  charTables.get(index);
    }

    public void generateTablePermutations(char[][] originalTable) {
        List<char[][]> permutations = new ArrayList<>();

        // Převést řádky tabulky na seznam
        List<char[]> rows = new ArrayList<>();
        for (char[] row : originalTable) {
            rows.add(row);
        }

        // Generovat permutace řádků
        permuteByRows(rows, 0, permutations);

        charTables = permutations;
    }

    private void permuteByRows(List<char[]> rows, int index, List<char[][]> permutations) {
        if (index == rows.size()) {
            // Přidat aktuální permutaci řádků jako novou tabulku
            char[][] permutedTable = new char[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                permutedTable[i] = rows.get(i).clone();
            }
            permutations.add(permutedTable);
        } else {
            for (int i = index; i < rows.size(); i++) {
                Collections.swap(rows, i, index);
                permuteByRows(rows, index + 1, permutations);
                Collections.swap(rows, i, index); // Vrátit zpět pro další permutace
            }
        }
    }



    private List<Integer> getTablesWidths(){
        List<Integer> numbers = new ArrayList<>();
        int textLength = this.text.length;
        for (int i=1; i<=textLength;i++) {
            if(textLength %i ==0) {
                numbers.add(i);
            }
        }
        return numbers;
    }
}
