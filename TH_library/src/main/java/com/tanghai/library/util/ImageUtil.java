/**
 * 
 */
package com.tanghai.library.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tanghai.library.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片异步加载缓存类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public class ImageUtil {
	private static ImageUtil imageUtil;

	private Context context;

	private ImageUtil() {

	}

	public static ImageUtil getInstance() {
		if (imageUtil == null) {
			imageUtil = new ImageUtil();
		}
		return imageUtil;
	}

	/**
	 * 初始化ImageLoader
	 * 
	 * @param ctx
	 * @param cacheDir
	 *            缓存目录
	 */
	public void init(Context ctx, String cacheDir) {
		context = ctx;
		initImageLoader(ctx, cacheDir);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressWarnings("unused")
	private void initImageLoader(Context context, String cacheDir) {
		if (Constants.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());

		}
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		String reserveCacheDir = "/data/data/" + context.getPackageName() + "/cache/";
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(10 * 1024 * 1024))
				// .diskCacheSize(50 * 1024 * 1024)
				.diskCache(new UnlimitedDiscCache((new File(cacheDir)), new File(reserveCacheDir))).diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																					// for
																					// release
																					// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 图片下载
	 * 
	 * @param dir
	 *            下载文件存储目录
	 * @param path
	 *            网络文件路径
	 * @return 下载的文件
	 */
	public File downloadImage(String dir, String path) {

		// http协议连接对象
		HttpURLConnection conn;
		try {
			URL url = new URL(path);// 获取到路径
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");// 这里是不能乱写的，详看API方法
			conn.setConnectTimeout(6 * 1000);
			// 别超过10秒。
			if (conn.getResponseCode() == 200) {
				InputStream inputStream = conn.getInputStream();
				String filename = MD5Utils.encode(url.toString()) + ".jpg";
				byte[] data = readStream(inputStream);
				File file = new File(dir + filename);// 给图片起名字
				FileOutputStream outStream = new FileOutputStream(file);// 写出对象
				outStream.write(data);// 写入
				outStream.close(); // 关闭流
				return file;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 读取文件流返回字节码
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	private byte[] readStream(InputStream inStream) throws IOException {

		byte[] buffer = new byte[1024]; // 用数据装װ
		int len = -1;
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();
		// 关闭流一定要记得。
		return outstream.toByteArray();
	}
}
