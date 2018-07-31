package edu.kit.pse.fridget.client.activity;

import android.support.v7.app.AppCompatActivity;
import java.util.Random;

public class GetAccessCodeActivity extends AppCompatActivity {

    public GetAccessCodeActivity(){
        createAllowedCharTable();
    }

    private void createAllowedCharTable(){
        int amountOfNumbers = '9' - '0';
        int amountOfLower = 'z' - 'a';
        int amountOfUpper = 'Z' - 'A';

        allowedCharTable = new char[amountOfLower + amountOfNumbers + amountOfUpper];

        for(int number = '0'; number <= '9'; number++){
            allowedCharTable[number - '0'] = (char)number;
        }

        for(int lower = 'a'; lower <= 'z'; lower++){
            allowedCharTable[amountOfNumbers + lower - 'a'] = (char)lower;
        }

        for(int upper = 'A'; upper <= 'Z'; upper++){
            allowedCharTable[amountOfNumbers + amountOfLower + upper - 'A'] = (char)upper;
        }
    }

    private static Random rand = new Random();

    char[] allowedCharTable;

    private int getRandomNumber() {
        return rand.nextInt(allowedCharTable.length);
    }

    public String generateAccessCode(int length){
        char[] result = new char[length];

        for(int place = 0; place < result.length; place++){
            result[place] = allowedCharTable[getRandomNumber()];
        }

        return new String(result);
    }

}