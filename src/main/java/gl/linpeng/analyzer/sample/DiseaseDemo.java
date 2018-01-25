package gl.linpeng.analyzer.sample;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import gl.linpeng.analyzer.lucene.IKAnalyzer;

public class DiseaseDemo {

	public static void main(String[] args) {
		// 构建IK分词器，使用smart分词模式
		Analyzer analyzer = new IKAnalyzer(true);

		// 获取Lucene的TokenStream对象
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("myfield", new StringReader(
					"糖尿病更讲究的是饮食疗法，饮食中根据病人的体重和活动量，估计所需总热量，合理安排每天的饮食。含糖量高的食品不吃，含脂肪和淀粉的食品少吃，以吃蔬菜杂粮类为主，配以一定数量的优质蛋白质的食物。1、多食含纤维素的食物2、多吃五谷杂粮3、多食含B族维生素的食物1、禁食胆固醇较高的食物2、忌食糖制甜食3、忌抽烟、喝酒"));
			// 获取词元位置属性
			OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
			// 获取词元文本属性
			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			// 获取词元文本属性
			TypeAttribute type = ts.addAttribute(TypeAttribute.class);

			// 重置TokenStream（重置StringReader）
			ts.reset();
			// 迭代获取分词结果
			while (ts.incrementToken()) {
				System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | "
						+ type.type());
			}
			// 关闭TokenStream（关闭StringReader）
			ts.end(); // Perform end-of-stream operations, e.g. set the final
						// offset.

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放TokenStream的所有资源
			if (ts != null) {
				try {
					ts.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
