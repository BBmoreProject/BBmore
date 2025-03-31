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
                             byte[] filedData)throws Exception{

        UUID uuid = UUID.randomUUID(); /// 고유식별자 생성
        String extension = originalFileName.substring(originalFileName.
                lastIndexOf(".")); /// 확장자 .jpg 가져옴
        String savedFileName = uuid.toString() + extension; /// 새로운 파일명 생성
        ///  예: "123e4567-e89b-12d3-a456-426614174000.jpg"와 같은 형태
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        ///  String fileUploadFullUrl = uploadPath + "/" + savedFileName; 파일이 저장될 디렉토리
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        ///  FileOutputStream을 사용하여 바이트 데이터를 파일로 저장
        fos.write(filedData);
        fos.close();
        return savedFileName;
        ///  생성된 고유 파일명을 반환합니다.
        /// 이 파일명은 나중에 데이터베이스에 저장되어 파일을 참조하는 데 사용될 수 있습니다.

    }

    public void deleteFile(String filePath) throws Exception{

        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("file Delete");
        } else {
            log.info("file not exist");
        }
    }
}
