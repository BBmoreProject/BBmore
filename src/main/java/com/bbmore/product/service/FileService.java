package com.bbmore.product.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {



    public String uploadFile(String uploadPath, String originalFileName,
                             byte[] fileData) throws Exception {

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // UUID 생성 및 확장자 처리 (점이 없을 경우 기본값 처리)
        UUID uuid = UUID.randomUUID();
        int dotIndex = originalFileName.lastIndexOf(".");
        String extension = (dotIndex > 0) ? originalFileName.substring(dotIndex) : "";
        String savedFileName = uuid.toString() + extension;

        // uploadPath가 "/"로 끝나는지 확인
        if (!uploadPath.endsWith("/")) {
            uploadPath += "/";
        }
        String fileUploadFullUrl = uploadPath + savedFileName;

        // try-with-resources 사용하여 스트림 자동 닫기
        try (FileOutputStream fos = new FileOutputStream(fileUploadFullUrl)) {
            fos.write(fileData);
        }

        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            if (deleteFile.delete()) {
                log.info("file Delete");
            } else {
                log.warning("file delete failed");
            }
        } else {
            log.info("file not exist");
        }
    }
}
