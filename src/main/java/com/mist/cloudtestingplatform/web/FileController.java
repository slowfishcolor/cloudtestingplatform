package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.File;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Prophet on 2018/1/19.
 */
@Auth
@SessionAttributes("user")
@Controller
public class FileController {

    private FileService fileService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    /***************************** pages *****************************/

    @RequestMapping(value = "file", method = RequestMethod.GET)
    public String uploadFilePage(@ModelAttribute User user) {
        return "file";
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "api/uploadFile", method = RequestMethod.POST)
    public Map<String, String> uploadFile(HttpServletRequest request) throws IOException{
        Map<String, String> result = new HashMap<>();

        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile multipartFile = multipartRequest.getFile("file-data");

        File file = fileService.getFileByName(multipartFile.getOriginalFilename());
        if (file == null) {
            file = new File();
        }
        file.setName(multipartFile.getOriginalFilename());
        file.setType(multipartFile.getContentType());
        file.setData(multipartFile.getBytes());
        file.setUpdateTime(System.currentTimeMillis());

        fileService.saveFile(file);
//        logger.info("type {}", multipartFile.getContentType());

        logger.info("executed");
        result.put("result", "success");
        return result;
    }

    @RequestMapping("api/file/{fileName:.+}")
    public void getFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        logger.info("file name: {}", fileName);

        File file = fileService.getFileByName(fileName);
        if (file != null) {

            response.setContentType(file.getType());
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(file.getData());

            outputStream.flush();
            outputStream.close();

        }

    }

    @ResponseBody
    @RequestMapping(value = "api/getFileList")
    public List<File> getFileList() {
        List<File> files = fileService.listAllFilesWithoutData();
        return files;
    }

    @ResponseBody
    @RequestMapping(value = "api/uploadXml/{fileName}", method = RequestMethod.POST)
    public void uploadXml(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        InputStream inputStream = request.getInputStream();
        int nRead = 1;
        int nTotalRead = 0;
        byte[] bytes = new byte[102400];
        while (nRead > 0) {
            nRead = inputStream.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0)
                nTotalRead = nTotalRead + nRead;
        }
        String str = new String(bytes, 0, nTotalRead, "utf-8");
//        System.out.println(str);

        String xmlFileName = fileName + ".xml";
        File file = fileService.getFileByName(xmlFileName);
        if (file == null) {
            file = new File();
            file.setName(xmlFileName);
            file.setType("text/xml");
        }
        byte[] data = new byte[nTotalRead];
        System.arraycopy(bytes,0, data, 0, nTotalRead);
        file.setData(data);
        file.setUpdateTime(System.currentTimeMillis());

        fileService.saveFile(file);

        logger.info("successful save file: {}", xmlFileName);

        Writer writer = response.getWriter();
        writer.write(str);
        writer.flush();
        writer.close();

    }
}
