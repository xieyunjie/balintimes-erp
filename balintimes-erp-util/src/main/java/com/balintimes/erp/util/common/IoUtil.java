package com.balintimes.erp.util.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class IoUtil {

	public static List<FileDetail> upload(HttpServletRequest request,
			HttpServletResponse response, String path, String fileName)
			throws IllegalStateException, IOException {
		List<FileDetail> list = new ArrayList<FileDetail>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				String uid = UUID.randomUUID().toString();

				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// System.out.println(myFileName);
						// 重命名上传后的文件名
						int index = myFileName.lastIndexOf(".");
						String prx = myFileName.substring(index);

						String newfileName = uid + "_" + fileName + prx;
						// 定义上传路径

						String url = request.getSession().getServletContext()
								.getRealPath(path);
						File targetFile = new File(url, newfileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						file.transferTo(targetFile);

						FileDetail fd = new FileDetail();
						fd.setUid(uid);
						fd.setFileName(fileName);
						fd.setPrx(prx);
						fd.setFileFullName(newfileName);

						list.add(fd);
					}
				}
			}

		}

		return list;
	}

	public static List<FileDetail> upload(HttpServletRequest request,
			HttpServletResponse response, String path)
			throws IllegalStateException, IOException {
		List<FileDetail> list = new ArrayList<FileDetail>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				String uid = UUID.randomUUID().toString();

				String n = iter.next();
				String filename = new String(Base64.decodeBase64(multiRequest
						.getParameter(n + "_f")));

				MultipartFile file = multiRequest.getFile(n);
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// System.out.println(myFileName);
						// 重命名上传后的文件名
						int index = filename.lastIndexOf(".");
						String prx = filename.substring(index);
						String name = filename.substring(0, index);

						String newfileName = uid + "_" + filename;
						// 定义上传路径

						String url = request.getSession().getServletContext()
								.getRealPath(path);
						File targetFile = new File(url, newfileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						file.transferTo(targetFile);

						FileDetail fd = new FileDetail();
						fd.setUid(uid);
						fd.setFileName(name);
						fd.setPrx(prx);
						fd.setFileFullName(newfileName);

						list.add(fd);
					}
				}
			}

		}

		return list;
	}

	public static void copy(String oldPath, String newFileName, String newPath)
			throws IOException, Exception {
		InputStream fileInputStream = null;
		OutputStream fileOutputStream = null;
		try {

			String newUrl = newPath + "/" + newFileName;

			File file = new File(oldPath);
			File newDir = new File(newUrl);

			File u = new File(newPath);
			if (!u.exists()) {
				u.mkdir();
			}

			if (!newDir.exists()) {
				newDir.createNewFile();
			} else {
				newDir.delete();
				newDir.createNewFile();
			}

			fileInputStream = new FileInputStream(file);

			fileOutputStream = new FileOutputStream(newDir, true);

			if (fileInputStream != null && fileOutputStream != null) {
				int temp = 0;
				while ((temp = fileInputStream.read()) != -1) {
					fileOutputStream.write(temp);
				}
				System.out.println("复制完成");

			}

		} catch (IOException ioe) {
			throw ioe;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if (fileInputStream != null)
				fileInputStream.close();
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

	}

	public static void cut(String oldPath, String newFileName, String newPath)
			throws IOException, FileNotFoundException, Exception {
		copy(oldPath, newFileName, newPath);
		File file = new File(oldPath);
		file.delete();
	}

	public static ResponseEntity<byte[]> download(String filePath,
			String filename) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", filename);

		return new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(new File(filePath)), headers,
				HttpStatus.CREATED);
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// System.out.println(DateUtil.dateToString(new Date()));
	//
	// String old = "J:\\Viso2007-SN.txt";
	// String newPath = "H:\\";
	// String newFileName = "aa.txt";
	//
	// try {
	// copy(old, newFileName, newPath);
	// System.out.println("OK");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println(DateUtil.dateToString(new Date()));
	// }

}
