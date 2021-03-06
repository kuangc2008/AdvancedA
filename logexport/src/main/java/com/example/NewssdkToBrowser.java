package com.example;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NewssdkToBrowser {
    public static final String VERSION = "2.1.6";

    public static final String BROWSER_PROJECT_DIR = "/media/kc/god/home/kc/workspace/code/dev/m_browser_chromium/";

    public static final String AAR_DOWNLOAD_PATH = "/home/kc/Downloads/";

    public static final String NEWSDK_FILE_DIR = "/media/kc/god/home/kc/workspace/code/dev/m_browser_chromium/newsfeed/";

    public static final String LOG_BRANCH = "origin/developer";

    public static int LOG_COUNT = 15;

    public static void main(String[] args) {
        String version = NewssdkToBrowser.VERSION;
        if (args != null && args.length > 0) {
            LOG_COUNT = Integer.parseInt(args[0]);
        }

        int count = NewssdkToBrowser.LOG_COUNT;

        String streamToStr = null;
        try {
            int index = NewssdkToBrowser.LOG_BRANCH.indexOf("/");
            if (index > 0 && index < NewssdkToBrowser.LOG_BRANCH.length()) {
                String remoteName = NewssdkToBrowser.LOG_BRANCH.substring(0, index);
                String branchName = NewssdkToBrowser.LOG_BRANCH.substring(index + 1);
                String[] fetchFetch = {"/bin/sh", "-c",
                        "cd " + NewssdkToBrowser.NEWSDK_FILE_DIR + "; " +
                                "git fetch " + remoteName + " " + branchName};
                // 更新远程log
                Runtime.getRuntime().exec(fetchFetch);
            }

            String[] cmd = {"/bin/sh", "-c",
                    "cd " + NewssdkToBrowser.NEWSDK_FILE_DIR + "; " +
                            "git log " + NewssdkToBrowser.LOG_BRANCH + " -n " + count + " --pretty=format:\"%an %ai %s\";"};

            Process process = Runtime.getRuntime().exec(cmd);
            streamToStr = streamToString(process.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (streamToStr == null) {
            return;
        }


        String fileName = copyFile();
        if (fileName == null) {
            return;
        }

        System.out.println("file name-" + fileName);

        ArrayList<String> datas = new ArrayList<>();
        ArrayList<String> datas2 = new ArrayList<>();

        try {
//            read(filePath, datas, datas2);
            read2(streamToStr, datas, datas2);

            writeLine();

            writeString("[browser][newssdk][" + version + "][aar] " + fileName);
            write1(datas);

            writeLine();
            writeString(fileName);
            write1(datas2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String copyFile() {
        String downloadPath = NewssdkToBrowser.AAR_DOWNLOAD_PATH;
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

        String destFilePath = NewssdkToBrowser.BROWSER_PROJECT_DIR + "browser/browser/libs/newssdk.aar";
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



    private static void read2(String streamToStr, ArrayList<String> datas, ArrayList<String> datas2) throws IOException {
        int currentLine = 0;
        BufferedReader bis = new BufferedReader(new StringReader(streamToStr));
        String line = null;

        List<String> headers = new ArrayList<String>();
        List<String> tail = new ArrayList<String>();


        while ( (line = bis.readLine()) != null ) {
            String[] parts = line.split(" ", 5);
            if (parts.length == 5) {
                currentLine++;
                datas.add( currentLine + ". " + parts[4] + "###" + parts[0]);


                String header = getHeader(parts);
                headers.add(header);
                tail.add(parts[4]);
            }
        }


        for (int i = 0; i < headers.size(); i++) {
//            String start = (i < 9 ? ("0" + (i+1) + ".") : ((i+1) +"."));
//            String content = start;
            String content = "";
            content +=  headers.get(i);
            content += '\n';
            content +=  tail.get(i);
            content += '\n';
            content += "-------------------------------------------";
            datas2.add(content);
        }


        bis.close();
    }

    private static String getHeader(String[] parts) {
        StringBuilder content = new StringBuilder();
//        content.append("【");
        String[] years = parts[1].split("-");
        content.append(years[1]);
        content.append("月");
        content.append(years[2]);
        content.append("号");
        content.append(parts[2]);
        content.append(" | ");
        content.append(parts[0]);
//        content.append("】");

        return content.toString();
    }


    private static void writeLine() throws IOException {
        for (int i =0; i< 3; i++) {
            System.out.println();
        }
    }

    private static void write1(ArrayList<String> datas) throws IOException {
        for (String data : datas) {
            System.out.println(data);
        }
    }

    private static void writeString(String string) throws IOException {
        System.out.println(string);
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


    public static String streamToString(InputStream in) {
        if (in == null) {
            return "";
        }

        byte[] b = new byte[1024 * 4];
        int len = 1024 * 4;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while( (len = in.read(b)) > 0 ) {
                bos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toString();
    }
}