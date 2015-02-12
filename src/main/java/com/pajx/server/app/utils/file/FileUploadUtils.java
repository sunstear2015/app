package com.pajx.server.app.utils.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by admin on 2015/2/9.
 */
public class FileUploadUtils {
    //默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 52428800;

    //默认上传的地址
    private static String defaultBaseDir = "upload";

    //默认的文件名最大长度
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    public static final String[] IMAGE_EXTENSION = {
            "bmp", "gif", "jpg", "jpeg", "png"
    };

    public static final String[] FLASH_EXTENSION = {
            "swf", "flv"
    };

    public static final String[] MEDIA_EXTENSION = {
            "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb"
    };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            //图片
            "bmp", "gif", "jpg", "jpeg", "png",
            //word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "html", "htm", "txt",
            //压缩文件
            "rar", "zip", "gz", "bz2",
            //pdf
            "pdf"
    };


    private static int counter = 0;


    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param request 当前请求
     * @param file    上传的文件
     * @param result  添加出错信息
     * @return
     */
    public static final String upload(HttpServletRequest request, MultipartFile file, BindingResult result) {
        return upload(request, file, result);
    }


    /**
     * 以默认配置进行文件上传
     *
     * @param request          当前请求
     * @param file             上传的文件
     * @return
     */
    public static final String upload(HttpServletRequest request, MultipartFile file) {
        try {
            return upload(request, getDefaultBaseDir(), file);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件上传
     *
     * @param request          当前请求 从请求中提取 应用上下文根
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @return 返回上传成功的文件名
     *                                        文件名太长
     * @throws java.io.IOException                    比如读写文件出错时
     */
    public static final String upload(
            HttpServletRequest request, String baseDir, MultipartFile file)
            throws Exception {
        String filename = extractFilename(file, baseDir);
        File desc = getAbsoluteFile(extractUploadDir(request), filename);
        file.transferTo(desc);
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {

        uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);

        File desc = new File(uploadDir + File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }


    public static final String extractFilename(MultipartFile file, String baseDir)
            throws Exception {
        String filename = file.getOriginalFilename();
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
            filename = filename.substring(slashIndex + 1);
        }
            filename = baseDir + File.separator + filename;

        return filename;
    }

    /**
     * 编码文件名,默认格式为：
     * 1、'_'替换为 ' '
     * 2、hex(md5(filename + now nano time + counter++))
     * 3、[2]_原始文件名
     *
     * @param filename
     * @return
     */
    private static final String encodingFilename(String filename) {
        filename = filename.replace("_", " ");
        return filename;
    }

    /**
     * 日期路径 即年/月/日  如2013/01/03
     *
     * @return
     */
    private static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 提取上传的根目录 默认是应用的根
     *
     * @param request
     * @return
     */
    public static final String extractUploadDir(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }


    public static final void delete(HttpServletRequest request, String filename) throws IOException {
        if (StringUtils.isEmpty(filename)) {
            return;
        }
        File desc = getAbsoluteFile(extractUploadDir(request), filename);
        if (desc.exists()) {
            desc.delete();
        }
    }
}
