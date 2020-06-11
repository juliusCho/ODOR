package com.back.odor.common.etc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class FileService {

    @Autowired
    SecuredPropertySource propertySource;

    public String[] fileUpload(MultipartFile[] multipartFiles, String type, String subPath) {
        FTPClient client = new FTPClient();
        String rootPath = type + "/" + subPath;
        String[] files = new String[multipartFiles.length];

        try {
            this.connectToFTP(client);
            this.initializePath(client, type, subPath, rootPath);

            client.setFileType(FTP.BINARY_FILE_TYPE);

            int idx = 0;
            for (MultipartFile file : multipartFiles) {
                if (client.storeFile(file.getOriginalFilename(), file.getInputStream())) {
                    log.info("File Uploaded : [" + type + "] " + file.getOriginalFilename());
                    files[idx] = rootPath + "/" + file.getOriginalFilename();
                }
            }
            client.logout();

        } catch (Throwable e) {
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
        client.connect("localhost", 21);

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

    private void initializePath(FTPClient client, String type, String subPath, String rootPath) throws IOException {
        this.deleteFile(client, rootPath);
        client.changeWorkingDirectory(type);
        client.removeDirectory(subPath);
        client.makeDirectory(subPath);
        client.changeWorkingDirectory(subPath);
    }

    private void deleteFile(FTPClient client, String path) throws IOException {
        if (client.changeWorkingDirectory(path)) {
            for (FTPFile file : client.listFiles()) {
                if (file.isFile()) {
                    client.deleteFile(file.getName());
                } else if (file.isDirectory()) {
                    this.deleteFile(client, file.getName());
                    client.changeWorkingDirectory(path);
                    client.removeDirectory(file.getName());
                }
            }
            client.changeWorkingDirectory("/");
        }
    }

}
