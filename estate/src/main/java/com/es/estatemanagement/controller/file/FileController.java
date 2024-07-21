package com.es.estatemanagement.controller.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;

import io.swagger.annotations.Api;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/estate")
@Api(tags = "文件上传控制器",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "FileController类")
public class FileController {
	/**
	 * 功能描述: 上传文件
	 * @param file	上传文件的参数名，必须和前端参数名称一致
	 * @param request	请求对象
	 * @param response 	响应对象
	 * @return : com.es.estatemanagement.common.Result
	 */
	@RequestMapping(value="/fileupload")
	public Result uploads(@RequestParam("file")MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取当前项目所在绝对路径
		String absolutePath = System.getProperty("user.dir");
		//当前项目名称，可以自定义
//		String projectName = "/estate-management";
		//文件上传后所在绝对路径
		String savePath = "src/main/resources/static/fileupload/";
//		String savePath = "D:/------/fileupload/";
		//保存在数据库的文件地址
		String saveUrl = "/fileupload/";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		// 最大文件大小
		long maxSize = 10000000; //10M
		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			return new Result(false, StatusCode.ERROR,MessageConstant.NO_FILE_SELECTED);
		}

		File uploadDir = new File(savePath);
		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return new Result(false, StatusCode.ERROR,MessageConstant.NO_WRITE_PERMISSION);
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return new Result(false, StatusCode.ERROR,MessageConstant.INCORRECT_DIRECTORY_NAME);
		}

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String fileName = null;
		Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
		for (String s : fileMap.keySet()) {

		}
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			fileName = mFile.getOriginalFilename();
			// 检查文件大小
			if (mFile.getSize() > maxSize) {
				return new Result(false, StatusCode.ERROR,MessageConstant.SIZE_EXCEEDS__LIMIT);
			}
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
			if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt.toLowerCase())) {
				return new Result(false, StatusCode.ERROR,MessageConstant.FILE_TYPE_ERROR);
			}
			UUID uuid = UUID.randomUUID();
			String path = savePath + uuid.toString() +"."+ fileExt;
			System.out.println("文件存储地址:【"+path+"】");
			saveUrl = saveUrl  + uuid.toString() +"."+ fileExt;

			try {
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
				FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return new Result(true,StatusCode.OK, MessageConstant.COMMUNITY_PIC_UPLOAD_SUCCESS,saveUrl);
	}
	/**
	 * 功能描述: 删除文件
	 * @param fileName	文件名称参数，和前台传递的参数保持一致
	 * @return : com.es.estatemanagement.common.Result
	 */
	@RequestMapping(value="/delfile")
	public Result fileDel(@RequestParam String fileName){
		String name = fileName.replace("/fileupload/","");
		//获取当前项目所在绝对路径
//		String absolutePath = System.getProperty("user.dir");
		//当前项目名称，可以自定义
//		String projectName = "/estate-management";
		//文件上传后所在绝对路径
//		String savePath = absolutePath+projectName+"/src/main/resources/static/fileupload/";
		String savePath = "D:/------/fileupload/";
		File file = new File(savePath+name);
		if (file.exists()){//文件是否存在
			file.delete();//删除文件
		}
		return new Result(true,StatusCode.OK, MessageConstant.COMMUNITY_PIC_DEL_SUCCESS);
	}



}

