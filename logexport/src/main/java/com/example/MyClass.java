package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {


        String filePath = "/home/kc/Desktop/shared/每周工作/log/log";

        String fileName = copyFile();
        if (fileName == null) {
            return;
        }

        System.out.println("file name-" + fileName);

        ArrayList<String> datas = new ArrayList<>();
        ArrayList<String> datas2 = new ArrayList<>();

        try {
            read(filePath, datas, datas2);

            writeLine(filePath);

            writeString(filePath, "[browser][newssdk][2.1.4][aar] " + fileName);
            write1(filePath, datas);

            writeLine(filePath);
            writeString(filePath, fileName);
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


        String fileMd5 = null;
        try {
            fileMd5 = getMD5Checksum(filePath);
            System.out.println("md5 is-" + fileMd5);
        } catch (Exception e1) {
            e1.printStackTrace();
            System.err.println("md5 error");
        }

        String destFilePath = "/media/kc/god/home/kc/workspace/code/dev/m_browser_chromium/browser/browser/libs/newssdk.aar";
        File destFile = new File(destFilePath);

        boolean success = copyFile(new File(filePath), destFile);

        if (!success) {
            return null;
        }


        String destFileMd5 = null;
        try {
            destFileMd5 = getMD5Checksum(destFilePath);
            System.out.println("md5 is-" + destFileMd5);
        } catch (Exception e1) {
            e1.printStackTrace();
            System.err.println("md5 error");
        }

        if (destFileMd5 != null && destFileMd5.equals(fileMd5)) {
            return fileName;
        }

        return null;

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

    private static void writeString(String filePath, String string) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true));
        br.write(string);
        br.write('\n');
        System.out.println(string);
        br.close();
    }

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";

        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }


    public static boolean copyFile(File fromPath, File toPath) {
        if (fromPath != null && fromPath.exists() && toPath != null) {
            InputStream is = null;
            OutputStream ops = null;
            try {
                if (toPath.exists()) {
                    toPath.delete();
                }
                is = new FileInputStream(fromPath);
                ops = new FileOutputStream(toPath);
                int len = 0;
                byte[] buffer = new byte[4096];
                while (len != -1) {
                    len = is.read(buffer);
                    if (len > 0) {
                        ops.write(buffer, 0, len);
                    }
                }
                return true;
            } catch (Throwable ex) {
                ex.printStackTrace();

            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ops != null) {
                    try {
                        ops.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
