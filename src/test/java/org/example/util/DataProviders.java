package org.example.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> loginNegative_Sel_19() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/Sel-19_LoginNegative.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginNegative_Sel_20() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomLogin(getChars()),
                    this.generateRandomPassword(getChars())});
        }

        return data.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginPositive() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginPositive.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> newListCreating() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/newListCreating.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }



    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"log1", "pass1"});
        data.add(new Object[]{"log2", "pass2"});
        data.add(new Object[]{"log3", "pass3"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }


    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }


    public List<Character> getChars() {
        // ----- Create collection of chars according to the specified condition -----
        List<Character> list = new ArrayList<>();
        char[] ch = new char[128];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = (char)i;
        }
        int i = 0;
        for(char character : ch) {
            if ((48 <= (int)ch[i] && (int)ch[i]<= 57 ) || 65 <= (int)ch[i] && (int)ch[i] <= 90
                    || 97 <= (int)ch[i] && (int)ch[i] <= 122){

                list.add(character);
            }
            i++;
        }
        return list;
    }


    public Object generateRandomLogin(List<Character> getChars) {
        int min = 8;
        int max = 12;
        Random r = new Random();
        String res = "";
        for(int i = 0; i < countSymbols(min, max); i++) {
            int index = r.nextInt(getChars.size());
            res += getChars.get(index);
        }
        return res;
    }

    public Object generateRandomPassword(List<Character> getChars) {
        int min = 5;
        int max = 10;
        Random r = new Random();
        String res = "";
        for(int i = 0; i < countSymbols(min, max); i++) {
            int index = r.nextInt(getChars.size());
            res += getChars.get(index);
        }
        return res;
    }

    public int countSymbols(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}


