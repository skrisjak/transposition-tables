package org.example;

public class Main {
    public static void main(String[] args) {
        String cypheredText = "AHSNCUUTUNEINAUOLITAIASALNTHHNDDYSAHDNSIAAKAERRMHRECAORBHIEVNNLRULEOSCTHRLASAIAIFIADEODETUNNIRIIEYEDIIOEEPTOAGITTRODSBHEOIOSOTSRDONCBRYOSASNONEMZXYYERSRBGECFDTNYWSYIEESSOZYKNHHANRCAETOBWLEEWETEOIOOACONEINOFARGHHTDNEEITKODELAETTAAPCHUTHCAWWD";
        Table table =  new Table(cypheredText);
        table.generateCharTables();

        //generates every possible table by rows, prints by columns
        //table.listTablesAsString();

        //generates every possible table by rows, gives by columns to string
        //and for each string is generated new set of tables that are listed the same way
        table.mergeTablesToStrings().forEach(t -> {
            new Table(t).generateCharTables().listTablesAsString();
        });

    }
}