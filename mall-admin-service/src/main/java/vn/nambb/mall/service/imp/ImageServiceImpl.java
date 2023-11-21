package vn.nambb.mall.service.imp;


import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteArgs;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.nambb.mall.common.contant.CommonResponseCode;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.dto.ImageUploadDTO;
import vn.nambb.mall.service.ImageService;

import java.text.SimpleDateFormat;

@Service

public class ImageServiceImpl implements ImageService {
    @Value("${minio.endPoint}")
    private String minioEndPoint;
    @Value("${minio.accessKey}")
    private String minioAccessKey;
    @Value("${minio.secretKey}")
    private String minioSecretKey;
    @Value("${minio.bucketName}")
    private String minioBucketName;

    @Override
    public ImageUploadDTO create(MultipartFile file) {
        MinioClient minioClient = getMinioClient();

        String filename = file.getOriginalFilename();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String objectName = simpleDateFormat.format(System.currentTimeMillis()) + "/" + filename;

        uploadFileToMinio(file, minioClient, objectName);

        ImageUploadDTO imageUploadDTO = new ImageUploadDTO();
        imageUploadDTO.setName(filename);
        imageUploadDTO.setUrl(minioEndPoint + "/" + minioBucketName + "/" + objectName);
        return imageUploadDTO;
    }

    void uploadFileToMinio(MultipartFile file, MinioClient minioClient, String objectName) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE)
                    .build();
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new CRuntimeException(CommonResponseCode.MINIO_UPLOAD_FILE_ERROR);
        }

    }


    MinioClient getMinioClient() {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndPoint)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioBucketName).build());
            if (isExist) {
                return minioClient;
            }
        } catch (Exception e) {
            throw new CRuntimeException(CommonResponseCode.MINIO_CONNECTION_ERROR);
        }
        throw new CRuntimeException(CommonResponseCode.MINIO_CONNECTION_ERROR);
    }

}
