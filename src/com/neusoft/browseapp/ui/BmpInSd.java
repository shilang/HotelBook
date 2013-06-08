package com.neusoft.browseapp.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class BmpInSd {
	private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;
	private static final int MB = 1024 * 1024;
	private static final int CACHE_SIZE = 10;
	private static final String CACHDIR = "data/gowin/imgCach";
	private static final CharSequence WHOLESALE_CONV = ".cach";
	private static String TAG = "";
	private static final long mTimeDiff = 3 * 24 * 60 * 60 * 1000;;

	private void saveBmpToSd(Bitmap bm, String url) {
		if (bm == null) {
			Log.w(TAG, " trying to savenull bitmap");
			return;
		}
		// 判断sdcard上的空间
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
			Log.w(TAG, "Low free space onsd, do not cache");
			return;
		}

		String filename = convertUrlToFileName(url);
		String dir = getDirectory();
		File file = new File(dir + "/" + filename);
		try {
			file.createNewFile();
			OutputStream outStream = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			outStream.flush();
			outStream.close();
			Log.i(TAG, "Image saved tosd");
		} catch (FileNotFoundException e) {
			Log.w(TAG, "FileNotFoundException");
		} catch (IOException e) {
			Log.w(TAG, "IOException");
		}
	}

	/** 将url转成文件名 **/
	private String convertUrlToFileName(String url) {
		String[] strs = url.split("/");
		return strs[strs.length - 1] + WHOLESALE_CONV;
	}

	/** 获得缓存目录 **/
	private String getDirectory() {
		String dir = getSDPath() + "/" + CACHDIR;
		String substr = dir.substring(0, 4);
		if (substr.equals("/mnt")) {
			dir = dir.replace("/mnt", "");
		}
		return dir;
	}

	/**** 取SD卡路径不带/ ****/
	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		if (sdDir != null) {
			return sdDir.toString();
		} else {
			return "";
		}
	}

	/**
	 * 计算sdcard上的剩余空间
	 * 
	 * @return
	 */
	private int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;
		return (int) sdFreeMB;
	}

	/**
	 * 修改文件的最后修改时间
	 * 
	 * @param dir
	 * @param fileName
	 */
	private void updateFileTime(String dir, String fileName) {
		File file = new File(dir, fileName);
		long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
	}

	/**
	 * 计算存储目录下的文件大小，
	 * 当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定
	 * 那么删除40%最近没有被使用的文件
	 * 
	 * @param dirPath
	 * @param filename
	 */
	private void removeCache(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null) {
			return;
		}
		int dirSize = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(WHOLESALE_CONV)) {
				dirSize += files[i].length();
			}
		}
		if (dirSize > CACHE_SIZE * MB
				|| FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
			int removeFactor = (int) ((0.4 * files.length) + 1);

			Arrays.sort(files, new FileLastModifSort());

			Log.i(TAG, "Clear some expiredcache files ");

			for (int i = 0; i < removeFactor; i++) {

				if (files[i].getName().contains(WHOLESALE_CONV)) {

					files[i].delete();

				}

			}

		}

	}

	/**
	 * 删除过期文件
	 * 
	 * @param dirPath
	 * @param filename
	 */
	private void removeExpiredCache(String dirPath, String filename) {

		File file = new File(dirPath, filename);

		if (System.currentTimeMillis() - file.lastModified() > mTimeDiff) {

			Log.i(TAG, "Clear some expiredcache files ");

			file.delete();

		}

	}

	/**
	 * TODO 根据文件的最后修改时间进行排序 *
	 */
	class FileLastModifSort implements Comparator<File> {
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
