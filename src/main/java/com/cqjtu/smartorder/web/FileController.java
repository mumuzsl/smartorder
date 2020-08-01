package com.cqjtu.smartorder.web;

import com.cqjtu.smartorder.util.UploadHelper;
import com.cqjtu.smartorder.util.UploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/30
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    // 本地文件基本路径
    @Value(value = "${web.upload.path}")
    private String uploadPath;

    //图片上传接口
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        String uploadDir = "D:/upload/";// + (target <= 3 ? "user/" : "dishes/");
        try {
            // 图片路径
            String imgUrl = null;
            //上传
            String filename = UploadUtils.upload(file, uploadDir, file.getOriginalFilename());
            if (filename != null) {
                imgUrl = "api/file/downloadImage/" + filename;
            }
            map.put("code", 0);
            map.put("msg", "");
            map.put("imgUrl", imgUrl);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "上传失败");
            map.put("imgUrl", null);
            return map;
        }
    }

    /**
     * 在线打开图片
     *
     * @param fileName
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadImage/{fileName}")
    public void download(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (fileName != null) {
            UploadHelper.sendFile(
                    response,
                    uploadPath + fileName,
                    fileName,
                    "inline",
                    "image/jpeg");
            return;
        }
    }

}
