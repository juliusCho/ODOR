package com.back.odor.common.etc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileService {

    @Autowired
    SecuredPropertySource propertySource;

    public void fileUpload(MultipartFile[] multipartFiles, String type) {
        FTPClient client = new FTPClient();

        try {
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

            List<String> files = new ArrayList<>();
            List<String> directories = new ArrayList<>();

//            if (getFile)


        } catch (Throwable e) {

        }

//        String path = propertySource.getFilePath() +
    }

//    private boolean getFileList(FTPClient client, String )

}
