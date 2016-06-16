package com.markbro.dzd.common.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片处理方法
 *
 * @ClassName: ImageUtils 
 * @author mywhile 
 * @date 2015-12-29 上午9:39:10 
 *
 */
public class ImageUtils {
	
	/**
	 * 文件压缩
	 *
	 * @Title: rotateImage 
	 * @param @param image	原图片
	 * @param @param degree	旋转角度
	 * @param @param rImage	目标图片
	 * @param @throws IOException    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void rotateImage(final File image, final int degree, File rImage) throws IOException{
		BufferedImage bufferImage = ImageIO.read(image); 
		BufferedImage bimg = rotateImg(bufferImage, degree);
		
		FileOutputStream fos = new FileOutputStream(rImage); // 输出到文件流 
        // 可以正常实现bmp、png、gif转jpg 
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos); 
        encoder.encode(bimg); // JPEG编码 
        fos.close(); 
	}
	/**
	 * 旋转图片为指定角度
	 * @param bufferedimage 目标图像
	 * @param degree 旋转角度
	 * @return
	 */
	private static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(h, w, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w/2 ,h/2 );
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}
	private static BufferedImage rotateImg(BufferedImage image, int degree) throws IOException {  
			int type = image.getColorModel().getTransparency();
	        int iw = image.getWidth();//原始图象的宽度   
	        int ih = image.getHeight();//原始图象的高度  
	        int w = 0;  
	        int h = 0;  
	        int x = 0;  
	        int y = 0;  
	        degree = degree % 360;  
	        if (degree < 0)  
	            degree = 360 + degree;//将角度转换到0-360度之间  
	        double ang = Math.toRadians(degree);//将角度转为弧度  
	  
	        if (degree == 180 || degree == 0 || degree == 360) {  
	            w = iw;  
	            h = ih;  
	        } else if (degree == 90 || degree == 270) {  
	            w = ih;  
	            h = iw;  
	        } else {  
	            int d = iw + ih;  
	            w = (int) (d * Math.abs(Math.cos(ang)));  
	            h = (int) (d * Math.abs(Math.sin(ang)));  
	        }  
	  
	        x = (w / 2) - (iw / 2);//确定原点坐标  
	        y = (h / 2) - (ih / 2);  
	        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());  
	        Graphics2D gs = (Graphics2D)rotatedImage.getGraphics();  
	        //if(bgcolor==null){  
	            rotatedImage  = gs.getDeviceConfiguration().createCompatibleImage(w, h, type);  
	       // }else{  
	        //    gs.setColor(bgcolor);  
	      //      gs.fillRect(0, 0, w, h);//以给定颜色绘制旋转后图片的背景  
	      //  }  
	          
	        AffineTransform at = new AffineTransform();  
	        at.rotate(ang, w / 2, h / 2);//旋转图象  
	        at.translate(x, y);  
	        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);  
	        op.filter(image, rotatedImage);  
	        return rotatedImage;  
	          
	    }  
	/**
	 * 压缩图片
	 *
	 * @Title: compressImage 
	 * @param @param originalImage	原图
	 * @param @param width	压缩宽
	 * @param @param height 压缩高
	 * @param @param destFile	目标文件
	 * @param @throws Exception    设定文件 
	 * @throws
	 */
	public static void compressImage(File originalImage,int width,int height,File destFile) throws Exception{ 
        // 压缩图片 
        BufferedImage originalBufferedImage = ImageIO.read(originalImage); 
        BufferedImage resizedBufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
        resizedBufferedImage.getGraphics().drawImage(originalBufferedImage,0,0,width,height,null); 
  
        FileOutputStream fos = new FileOutputStream(destFile); // 输出到文件流 
        // 可以正常实现bmp、png、gif转jpg 
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos); 
        encoder.encode(resizedBufferedImage); // JPEG编码 
        fos.close(); 
    } 

	/**
	 * 图片等比压缩
	 *
	 * @Title: compressImage 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author mywhile
	 * @param @param originalImage	原图片
	 * @param @param scale	压缩比例”0.1~1"之间
	 * @param @param destFile	目标图片
	 * @param @throws Exception    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void compressImage(final File originalImage,final float scale,File destFile) throws Exception{ 
        // 压缩图片 
        BufferedImage originalBufferedImage = ImageIO.read(originalImage); 
        int originalWidth = originalBufferedImage.getWidth(); // 原始宽度 
        int originalHeight = originalBufferedImage.getHeight();// 原始高度 
        int resizeWidth = (int) (originalWidth*scale); // 压缩后 宽度 
        int resizeHeight = (int) (originalHeight*scale); // 压缩后 高度 
  
        BufferedImage resizedBufferedImage = new BufferedImage(resizeWidth,resizeHeight,BufferedImage.TYPE_INT_RGB); 
        resizedBufferedImage.getGraphics().drawImage(originalBufferedImage,0,0,resizeWidth,resizeHeight,null); 
  
        FileOutputStream fos = new FileOutputStream(destFile); // 输出到文件流 
        // 可以正常实现bmp、png、gif转jpg 
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos); 
        encoder.encode(resizedBufferedImage); // JPEG编码 
        fos.close(); 
    } 
}
