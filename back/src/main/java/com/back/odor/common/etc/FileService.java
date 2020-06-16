package com.back.odor.common.etc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {

    @Autowired
    SecuredPropertySource propertySource;

    public String[] fileUpload(MultipartFile[] multipartFiles, String type, String subPath) {
        FTPClient client = new FTPClient();
        String rootPath = type + "/" + subPath;
        String[] files = null;

        try {
            this.connectToFTP(client);
            this.initializeFTPPath(client, type, subPath, rootPath);

            client.setFileType(FTP.BINARY_FILE_TYPE);
            files = this.storeFileToFTP(multipartFiles, client, rootPath);

            client.logout();

        } catch (Throwable e) {
            if (client.isConnected()) {
                try {
                    client.logout();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    private void connectToFTP(FTPClient client) throws IOException {
        client.setControlEncoding("UTF-8");

        client.connect(propertySource.getFileIp(), propertySource.getFilePort());

        int resultCode = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(resultCode)) {
            log.error("FTP Connection Failed");
            return;
        }
        client.setSoTimeout(1000);

        if (!client.login(propertySource.getFileUserId(), propertySource.getFileUserPw())) {
            log.error("FTP credential failed");
            return;
        }
    }

    private void initializeFTPPath(FTPClient client, String type, String subPath, String rootPath) throws IOException {
        this.deleteFTPFile(client, rootPath);
        client.changeWorkingDirectory(type);
        client.removeDirectory(subPath);
        client.makeDirectory(subPath);
        client.changeWorkingDirectory(subPath);
    }

    private void deleteFTPFile(FTPClient client, String path) throws IOException {
        if (client.changeWorkingDirectory(path)) {
            for (FTPFile file : client.listFiles()) {
                if (file.isFile()) {
                    client.deleteFile(file.getName());
                } else if (file.isDirectory()) {
                    this.deleteFTPFile(client, file.getName());
                    client.changeWorkingDirectory(path);
                    client.removeDirectory(file.getName());
                }
            }
            client.changeWorkingDirectory("/");
        }
    }

    private String[] storeFileToFTP(MultipartFile[] multipartFiles, FTPClient client, String rootPath) throws IOException {
        String[] files = new String[multipartFiles.length];
        int idx = 0;
        for (MultipartFile file : multipartFiles) {
            if (client.storeFile(file.getOriginalFilename(), file.getInputStream())) {
                files[idx] = rootPath + "/" + file.getOriginalFilename();
                log.info("File Uploaded : " + files[idx]);
            }
        }
        return files;
    }



    public byte[] displayImage(String path) {
        byte[] result = null;
        try {
            result = Files.readAllBytes(Paths.get(propertySource.getFileTmpPath() + path));
        } catch (IOException e) {
            this.makeTmpDir(path);
            result = this.retrieveFileFromFTP(path);
        } finally {
            return result;
        }
    }

    private void makeTmpDir(String path) {
        String dir = propertySource.getFileTmpPath() + path.substring(0, path.lastIndexOf("/"));
        File folder = new File(dir);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private byte[] retrieveFileFromFTP(String path) {
        FTPClient client = new FTPClient();
        byte[] result = null;
        try {
            this.connectToFTP(client);
            String localPath = propertySource.getFileTmpPath() + path;

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(localPath);
                if (client.retrieveFile(path, fos)) {
                    result = Files.readAllBytes(Paths.get(localPath));
                }
                fos.close();
            } catch (FileNotFoundException e) {
                fos.close();
                this.retrieveFileFromFTP(path);
            }
            client.logout();

        } catch(IOException e) {
            if (client.isConnected()) client.logout();
            e.printStackTrace();
            this.retrieveFileFromFTP(path);

        } finally {
            try {
                client.disconnect();
            } catch(IOException e){
                e.printStackTrace();
            }
            return result;
        }
    }




    @Scheduled(fixedDelay = 600000) // 10분에 한번씩 tmp폴더 삭제
    public void deleteFilesScheduledTask() throws IOException {
        this.emptyTmpFolder(propertySource.getFileTmpPath());
    }

    private void emptyTmpFolder(String tmpPath) throws IOException {
        List<File> fileList = Files.list(Paths.get(tmpPath))
                .map(path -> path.toFile())
                .collect(Collectors.toList());

        for (File file : fileList) {
            if (file.isDirectory()) {
                this.emptyTmpFolder(file.getAbsolutePath());
            } else {
                file.delete();
            }
        }
    }

}
