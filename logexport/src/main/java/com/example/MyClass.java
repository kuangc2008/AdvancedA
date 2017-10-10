package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {


        String filePath = "/home/kc/Desktop/shared/每周工作/log/log";

        String destPath = "/home/kc/Desktop/shared/每周工作/log/log2";



        String fileName = copyFile();

        new File(destPath).delete();
        try {
            new File(destPath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> datas = new ArrayList<>();
        ArrayList<String> datas2 = new ArrayList<>();

        try {
            read(filePath, datas, datas2);

            writeLine(filePath);
//            writeString(filePath, fileName);
            write1(filePath, datas);

            writeLine(filePath);
            write1(filePath, datas2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String copyFile() {
        String downloadPath = "/home/kc/Downloads";
        File downloadPathFile = new File(downloadPath);


        String[] files = downloadPathFile.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("newssdk-V") && name.endsWith(".aar")) {
                    return true;
                }
                return false;
            }
        });

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareToIgnoreCase(o1);
            }
        });

        String fileName = files[0];
        String filePath = downloadPath + "/" +  fileName;

//        String destFilePath = "/media/kc/god/home/kc/workspace/code/dev/m_browser_chromium/browser/browser/libs/newssdk.aar";
        String destFilePath = "/media/kc/god/home/kc/workspace/code/dev/m_browser_chromium/browser/browser/libs/";
        File destFile = new File(destFilePath);

//        if (destFile.exists()) {
//            destFile.delete();
//        }

        Process process = null;
        try {
            String downloadFile = downloadPath + "/" + "newssdk.aar";
            String shell = "rename "+ filePath + " " + downloadFile;
//            process = Runtime.getRuntime().exec("mv -f " + filePath + " " + destFilePath);
            process = Runtime.getRuntime().exec(shell);

//            int exitValue = process.exitValue();

            byte[] b = new byte[112048];
            int len = process.getInputStream().read(b);
            if (len > 0) {
                System.out.println(new String(b, 0, len));
            }


            byte[] c = new byte[112048];
            len = process.getErrorStream().read(c);
            if (len > 0) {
                System.out.println(new String(c, 0, len));
            }


//            if (exitValue == 0) {
                return fileName;
//            }
        } catch (IOException e) {
            return null;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }


//        return null;

    }

    private static void read(String filePath, ArrayList<String> datas, ArrayList<String> datas2) throws IOException {

        int currentLine = 0;
        BufferedReader bis = new BufferedReader(new FileReader(new File(filePath)));
        String line = null;


        List<String> headers = new ArrayList<String>();
        List<String> tail = new ArrayList<String>();
        int maxHeaderLen = 0;

        while ( (line = bis.readLine()) != null ) {
//            System.out.println(line);

            String[] parts = line.split(" ", 8);
            if (parts.length == 8) {
                currentLine++;
                datas.add( currentLine + ". " + parts[7] + "###" + parts[1]);


                String header = getHeader(parts);
                headers.add(header);
                if (maxHeaderLen < header.length()) {
                    maxHeaderLen = header.length();
                }
                tail.add(parts[7]);
            }
        }

        for (int i = 0; i < headers.size(); i++) {
            String content = headers.get(i);
            int spaceCount = maxHeaderLen - content.length() + 1;
            for (int j = 0; j<spaceCount; j++) {
                content += ' ';
            }

            content += tail.get(i);
            datas2.add(content);
        }



        bis.close();
    }

    private static String getHeader(String[] parts) {
        StringBuilder content = new StringBuilder();
        content.append("【");
        String[] years = parts[3].split("/");
        content.append(years[0]);
        content.append("月");
        content.append(years[1]);
        content.append("号");

        if (parts[6].equals("AM")) {
            content.append("上午");
        } else if (parts[6].equals("PM")) {
            content.append("下午");
        }

        content.append(parts[5]);

        content.append("，");
        content.append(parts[1]);


        content.append("】");

        return content.toString();
    }


    private static void writeLine(String filePath) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true));
        for (int i =0; i< 3; i++) {
            br.write('\n');
        }
        br.close();
    }

    private static void write1(String filePath, ArrayList<String> datas) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true));
        for (String data : datas) {
            br.write(data);
            br.write('\n');
            System.out.println(data);
        }

        br.close();
    }

    private static void writeString(String filePath, String fileName) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true));
        br.write(fileName);
        System.out.println(fileName);
        br.close();
    }
}
