import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DimenTool {

	public static void gen() {
		// 以此文件夹下的dimens.xml文件内容为初始�?�参�?
		File file = new File("./res/values/dimens.xml");

		BufferedReader reader = null;
		StringBuilder sw240 = new StringBuilder();
		StringBuilder sw480 = new StringBuilder();
		StringBuilder sw540 = new StringBuilder();
		StringBuilder sw600 = new StringBuilder();
		StringBuilder sw720 = new StringBuilder();
		StringBuilder sw768 = new StringBuilder();
		StringBuilder sw800 = new StringBuilder();
		StringBuilder sw1080 = new StringBuilder();
		StringBuilder w820 = new StringBuilder();

		try {

			System.out.println("生成不同分辨率：");

			reader = new BufferedReader(new FileReader(file));

			String tempString;

			int line = 1;

			// �?次读入一行，直到读入null为文件结�?

			while ((tempString = reader.readLine()) != null) {

				if (tempString.contains("</dimen>")) {

					// tempString = tempString.replaceAll(" ", "");

					String start = tempString.substring(0, tempString.indexOf(">") + 1);

					String end = tempString.substring(tempString.lastIndexOf("<") - 2);
					// 截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号�?2，取得配置的数字
					Double num = Double.parseDouble(tempString.substring(tempString.indexOf(">") + 1,
							tempString.indexOf("</dimen>") - 2));

					// 根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行�??
					sw240.append(start).append(num * 0.75).append(end).append("\r\n");

					sw480.append(start).append(num * 1.5).append(end).append("\r\n");

					sw540.append(start).append(num * 1.6875).append(end).append("\r\n");

					sw600.append(start).append(num * 1.87).append(end).append("\r\n");

					sw720.append(start).append(num * 2.25).append(end).append("\r\n");

					sw768.append(start).append(num * 2.4).append(end).append("\r\n");

					sw800.append(start).append(num * 2.5).append(end).append("\r\n");

					sw1080.append(start).append(num * 3.375).append(end).append("\r\n");

					w820.append(start).append(num * 2.56).append(end).append("\r\n");

				} else {
					sw240.append(tempString).append("\r\n");

					sw480.append(tempString).append("\r\n");

					sw540.append(tempString).append("\r\n");

					sw600.append(tempString).append("\r\n");

					sw720.append(tempString).append("\r\n");

					sw768.append(tempString).append("\r\n");

					sw800.append(tempString).append("\r\n");

					sw1080.append(tempString).append("\r\n");

					w820.append(tempString).append("\r\n");

				}

				line++;

			}

			reader.close();
			System.out.println("<!--  sw240 -->");

			System.out.println(sw240);

			System.out.println("<!--  sw480 -->");

			System.out.println(sw480);

			System.out.println("<!--  sw600 -->");

			System.out.println(sw600);

			System.out.println("<!--  sw720 -->");

			System.out.println(sw720);

			System.out.println("<!--  sw800 -->");

			System.out.println(sw800);

			String sw240file = "./res/values-sw240dp-land/dimens.xml";

			String sw480file = "./res/values-sw480dp-land/dimens.xml";

			String sw540file = "./res/values-sw540dp-land/dimens.xml";

			String sw600file = "./res/values-sw600dp-land/dimens.xml";

			String sw720file = "./res/values-sw720dp-land/dimens.xml";

			String sw768file = "./res/values-sw768dp-land/dimens.xml";

			String sw800file = "./res/values-sw800dp-land/dimens.xml";

			String sw1080file = "./res/values-sw1080dp-land/dimens.xml";

			String w820file = "./res/values-w820dp/dimens.xml";
			// 将新的内容，写入到指定的文件中去
			writeFile(sw240file, sw240.toString());

			writeFile(sw480file, sw480.toString());

			writeFile(sw540file, sw540.toString());

			writeFile(sw600file, sw600.toString());

			writeFile(sw720file, sw720.toString());

			writeFile(sw768file, sw768.toString());

			writeFile(sw800file, sw800.toString());

			writeFile(sw1080file, sw1080.toString());

			writeFile(w820file, w820.toString());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e1) {

					e1.printStackTrace();

				}

			}

		}

	}

	/**
	 * 162. * 写入方法 163. * 164.
	 */

	public static void writeFile(String path, String text) {

		PrintWriter out = null;
		String folderpath = path.substring(0, path.lastIndexOf("/"));
		System.out.println(folderpath);
		File folderFile = new File(folderpath);
		if (!folderFile.exists()) {
			folderFile.mkdirs();
		}
		try {
			System.out.println(path);
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}

			out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			out.println(text);

		} catch (Exception e) {

			e.printStackTrace();

		}
		out.close();
	}

	public static void main(String[] args) {
		gen();
	}

}
