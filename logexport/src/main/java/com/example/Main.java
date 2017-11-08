package com.example;

/**
 * @author kc create on 11/8/17.
 * @copyright litebrowser
 */
public class Main {

    public static void main(String[] args) {
        if (args.length <= 0) {
            return;
        }

        String dest[] = new String[args.length - 1];
        System.arraycopy( args, 1, dest, 0, dest.length );
        String cmd = args[0];

        if ("log".equals(cmd)) {

            NewssdkToBrowser.main(dest);
        } else if ("history".equals(cmd)) {
            History.main(dest);
        }


    }
}
