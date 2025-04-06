package com.bbmore.product.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(
            String uploadPath,
            String productImgOriName,
            byte[] fileData) throws Exception {

        UUID uuid = UUID.randomUUID();
        String extension = productImgOriName.substring(productImgOriName
                .lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return savedFileName;
/**
 * 입력:
 * - uploadPath: "/home/user/uploads"
 * - productImgOriName: "상품사진.jpg"
 * - fileData: [실제 이미지의 바이트 데이터]
 *
 * 처리:
 * 1. UUID 생성: "550e8400-e29b-41d4-a716-446655440000"
 * 2. 확장자 추출: ".jpg"
 * 3. 새 파일명: "550e8400-e29b-41d4-a716-446655440000.jpg"
 * 4. 전체 경로: "/home/user/uploads/550e8400-e29b-41d4-a716-446655440000.jpg"
 * 5. 이 경로에 파일 데이터 저장
 *
 * 반환:
 * - "550e8400-e29b-41d4-a716-446655440000.jpg"
 */

    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
