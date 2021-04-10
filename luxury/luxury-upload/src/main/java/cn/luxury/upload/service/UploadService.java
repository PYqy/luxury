package cn.luxury.upload.service;

import cn.luxury.upload.controller.UploadController;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    // 支持的文件类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/png", "image/jpeg");
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    public String upload(MultipartFile file) throws IOException {
        try {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            //检查文件类型
            if (!CONTENT_TYPE.contains(contentType)) {
                logger.info("文件类型不合法： {}", originalFilename);
                return null;
            }
            //检查文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                logger.info("文件不合法： {}", originalFilename);
                return null;
            }
            //上传
            //file.transferTo(new File("/Users/hehe/Downloads/images/" + originalFilename));
            String substringAfter = StringUtils.substringAfter(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), substringAfter, null);
            //返回url

            return "http://image.luxury.com/" + storePath.getFullPath();
        }catch (IOException e) {
            logger.info("服务器内部错误");
            e.printStackTrace();
        }
        return null;
    }
}
