package com.user;

import java.util.Random;

public class DataGen {

    public static String generateRandomName(int len) {

        String upperchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerchars = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        sb.append(upperchars.charAt(rnd.nextInt(upperchars.length())));

        for (int i = 0; i < len - 1; i++)
            sb.append(lowerchars.charAt(rnd.nextInt(lowerchars.length())));

        return sb.toString();
    }

    public static Integer generateRandomNumber(int min, int max) {

        Integer rndNum = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return rndNum;
    }


    public static String generateRandomAddress(int len) {

        String streetType[] = {
                "Ave", "St", "Dr"
        };

        int rnd2 = new Random().nextInt(streetType.length);

        String upperchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerchars = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);

        sb.append(upperchars.charAt(rnd.nextInt(upperchars.length())));

        for (int i = 0; i < len - 1; i++)
            sb.append(lowerchars.charAt(rnd.nextInt(lowerchars.length())));

        sb.append(" " + streetType[rnd2]);

        return sb.toString();
    }

    public static String generateRandomGender () {

            String upperchars = "MF";
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder(1);
            sb.append(upperchars.charAt(rnd.nextInt(upperchars.length())));

            return sb.toString();
    }

    static String[] generateRandomDob() {

        Integer dayRnd = 1;
        Integer monthRnd = generateRandomNumber(1,12);
        Integer yearRnd = generateRandomNumber(1940,2000);
        String monthAbr = "";

        if (monthRnd == 2) {
            dayRnd = generateRandomNumber(1,28);
        }
        else if (monthRnd == 4 || monthRnd == 6 || monthRnd == 9 || monthRnd == 11) {
            dayRnd = generateRandomNumber(1,30);
        }
        else {
            dayRnd = generateRandomNumber(1,31);
        };

        switch(monthRnd) {
            case 1:
                monthAbr = "Jan";
                break;
            case 2:
                monthAbr = "Feb";
                break;
            case 3:
                monthAbr = "Mar";
                break;
            case 4:
                monthAbr = "Apr";
                break;
            case 5:
                monthAbr = "May";
                break;
            case 6:
                monthAbr = "Jun";
                break;
            case 7:
                monthAbr = "Jul";
                break;
            case 8:
                monthAbr = "Aug";
                break;
            case 9:
                monthAbr = "Sep";
                break;
            case 10:
                monthAbr = "Oct";
                break;
            case 11:
                monthAbr = "Nov";
                break;
            case 12:
                monthAbr = "Dec";
                break;
        }

        String AbrDobStr = monthAbr+"-"+dayRnd.toString()+"-"+yearRnd.toString();

        String fullDobStr = monthRnd.toString()+"-"+dayRnd.toString()+"-"+yearRnd.toString();

        String[] dobInfo = {fullDobStr,monthRnd.toString(),dayRnd.toString(),yearRnd.toString(),AbrDobStr};

        return dobInfo;
    }

    public static String convertToMonthNum (String monthNum) {

        String monthStr = null;

        switch(monthNum) {
            case "1":
                monthStr = "Jan";
                break;
            case "2":
                monthStr = "Feb";
                break;
            case "3":
                monthStr = "Mar";
                break;
            case "4":
                monthStr = "Apr";
                break;
            case "5":
                monthStr = "May";
                break;
            case "6":
                monthStr = "Jun";
                break;
            case "7":
                monthStr = "Jul";
                break;
            case "8":
                monthStr = "Aug";
                break;
            case "9":
                monthStr = "Sep";
                break;
            case "10":
                monthStr = "Oct";
                break;
            case "11":
                monthStr = "Nov";
                break;
            case "12":
                monthStr = "Dec";
                break;
        }
        return monthStr;
    }

    public static String generateRandomTORPhone() {

        String nums = "0123456789";
        String[] areacodes = {"437","416"};
        Random rnd = new Random();
        int rndnum = new Random().nextInt(areacodes.length);
        StringBuilder sb = new StringBuilder(10);
        sb.append(areacodes[rndnum]+"-");
        sb.append(nums.charAt(rnd.nextInt(nums.length())));

        for (int i = 0; i < 2; i++)
            sb.append(nums.charAt(rnd.nextInt(nums.length())));
        sb.append("-");
        for (int i = 0; i < 4; i++)
            sb.append(nums.charAt(rnd.nextInt(nums.length())));

        return sb.toString();
    }
}

