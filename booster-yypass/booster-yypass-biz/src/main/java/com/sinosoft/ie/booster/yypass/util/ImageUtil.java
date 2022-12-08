package com.sinosoft.ie.booster.yypass.util;

import com.sinosoft.ie.booster.yypass.controller.TestImage;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtil {

	public static MultipartFile getMultipartFile(File file) {
		DiskFileItem item = new DiskFileItem("file"
				, MediaType.MULTIPART_FORM_DATA_VALUE
				, true
				, file.getName()
				, (int)file.length()
				, file.getParentFile());
		try {
			OutputStream os = item.getOutputStream();
			os.write(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CommonsMultipartFile(item);
	}

	/**
	 * 通过BufferedImage图片流调整图片大小
	 * 指定压缩后长宽
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	/**
	 * 通过BufferedImage图片流调整图片大小
	 * @param originalImage
	 * @param reduceMultiple 缩小倍数
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, float reduceMultiple) throws IOException {
		int width = (int) (originalImage.getWidth() * reduceMultiple);
		int height = (int) (originalImage.getHeight() * reduceMultiple);
		Image resultingImage = originalImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	/**
	 * 压缩图片到指定大小
	 * @param srcImgData
	 * @param reduceMultiple 每次压缩比率
	 * @return
	 * @throws IOException
	 */

	public static byte[] resizeImage(byte[] srcImgData, float reduceMultiple) throws IOException {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
		int width = (int) (bi.getWidth() * reduceMultiple); // 源图宽度
		int height = (int) (bi.getHeight() * reduceMultiple); // 源图高度
		Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		//g.setColor(Color.RED);
		g.drawImage(image, 0, 0, null); // 绘制处理后的图
		g.dispose();
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ImageIO.write(tag, "JPEG", bOut);
		return bOut.toByteArray();
	}


	/**
	 * BufferedImage图片流转byte[]数组
	 */
	public static byte[] imageToBytes(BufferedImage bImage) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}


	/**
	 * byte[]数组转BufferedImage图片流
	 */
	private static BufferedImage bytesToBufferedImage(byte[] ImageByte) {
		ByteArrayInputStream in = new ByteArrayInputStream(ImageByte);
		BufferedImage image = null;
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static InputStream changeFile(MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			// 压缩到小于指定文件大小200k
			double targetSize = 200 * 1024;
			double minSize = 100 * 1024;
			try {
				//从MultipartFile 中获取 byte[]
				byte[] bytes = file.getBytes();
				System.out.println("头像图片压缩前大小：[{}]"+ bytes.length);
				while (bytes.length > targetSize) {
					float reduceMultiple = 0.5f;
					bytes = resizeImage(bytes, reduceMultiple);
				}
				while (bytes.length < minSize) {
					float reduceMultiple = 1.5f;
					bytes = resizeImage(bytes, reduceMultiple);
				}
				System.out.println("头像图片压缩后大小：[{}]"+bytes.length);
				BufferedImage newImage = bytesToBufferedImage(bytes);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(newImage, "jpg", os);
				InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
                return inputStream;
			} catch (IOException e) {
				//抛出异常
				return null;
			}
		}
		return null;
	}

	public static InputStream changeFileBytes(byte[] bytes) {
			// 压缩到小于指定文件大小200k
			double targetSize = 200 * 1024;
			double minSize = 100 * 1024;
			try {
				//从MultipartFile 中获取 byte[]
				//byte[] bytes = file.getBytes();
				System.out.println("头像图片压缩前大小：[{}]"+ bytes.length);
				while (bytes.length > targetSize) {
					float reduceMultiple = 0.5f;
					bytes = resizeImage(bytes, reduceMultiple);
				}
				while (bytes.length < minSize) {
					float reduceMultiple = 1.5f;
					bytes = resizeImage(bytes, reduceMultiple);
				}
				System.out.println("头像图片压缩后大小：[{}]"+bytes.length);
				BufferedImage newImage = bytesToBufferedImage(bytes);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(newImage, "jpg", os);
				InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
				return inputStream;
			} catch (IOException e) {
				//抛出异常
				return null;
			}

	}

	public static void main(String args[]){
		File local_file = new File("C:\\Users\\chenxuanwen\\Desktop\\application\\1.png");
		MultipartFile file = getMultipartFile(local_file);
		InputStream inputStream = changeFile(file);
		System.out.println(inputStream);
	}
}
