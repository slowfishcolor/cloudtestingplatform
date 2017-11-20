package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.Image;
import com.mist.cloudtestingplatform.service.ImageService;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import com.mist.cloudtestingplatform.util.FileUtils;
import com.mist.cloudtestingplatform.util.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Prophet on 2017/11/18.
 */
@Auth
@Controller
public class ImageController {

    private ImageService imageService;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    /***************************** pages *****************************/

    @RequestMapping(value = "uploadImage", method = RequestMethod.GET)
    public String uploadImagePage() {
        return "upload-image";
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "api/uploadImage", method = RequestMethod.POST)
    public OperateResult uploadImage(HttpServletRequest request) throws IOException {
        byte[] data = getImageBytesFromRequest(request);
        int imageId = imageService.saveImage(data);
        request.getSession().setAttribute("imageId", imageId);
        return OperateResultFactory.successResult().setData(imageId);
    }

    @RequestMapping(value = "api/image/{imageId}")
    public void image(@PathVariable int imageId, HttpServletResponse response) throws IOException{

        Image image = imageService.getImage(imageId);

        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(image.getData());

        outputStream.flush();
        outputStream.close();

    }


    /*********************** private methods **************************/

    private byte[] getBytesFromRequest(HttpServletRequest request) throws IOException{
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("file");
        return file.getBytes();
    }

    /**
     * 获取图片的 bytes，如果图片大小大于 65kb，则进行压缩后返回
     * @param request
     * @return
     * @throws IOException
     */
    private byte[] getImageBytesFromRequest(HttpServletRequest request) throws IOException{
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile multipartFile = multipartRequest.getFile("file");

        if (multipartFile.getSize() > 65 * 1000) {

//            String tempFileName = System.getProperty("java.io.tmpdir") + "/cloudtest.tmp";
            String fileName = multipartFile.getOriginalFilename();
            String tempFileName = "temp" + fileName.substring(fileName.lastIndexOf("."));
            File tempFile = new File(tempFileName);

            multipartFile.transferTo(tempFile);

            ImageUtils.compressImage(tempFile, 65 * 1000);

            return FileUtils.bytesFromFile(tempFile);
        }
        return multipartFile.getBytes();
    }

}
