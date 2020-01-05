package gl.linpeng.analyzer.sample;

import gl.linpeng.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.StringReader;

public class DiseaseDemo {

	public static void main(String[] args) {
		// 构建IK分词器，使用smart分词模式
		Analyzer analyzer = new IKAnalyzer(true);
		String testString = "饮食健康专家介绍说，近年来，我国由于心脑血管疾病引起的死亡率呈上升状态。为什么会这样呢?是由于我们的生活水平提高了，在饮食方面更是有了根本性的变化，使得很多人患上高血压，目前高血压已经成为人们非常害怕的疾病。</p><p>　　高血压不能吃什么</p><p>　　1、高血压患者不能吃油腻和辛辣的食物。常见的辛辣食物有葱、大蒜、生姜、芥末、韭菜、辣椒、桂皮、八角、洋葱等，高血压患者尤其不能食用辣椒。辣椒属于热性食物，倘若高血压患者有发热、便秘、疼痛等症状，食用辣椒后会加重症状，抵消降压药物起到的疗效。</p><p>　　2、少吃太咸的食物。每日进盐控制在2-5克以内。因为食盐能使小动脉痉挛，血压升高，并促使肾小动脉硬化过程加快。食盐过多，还容易使水钠在体内潴而引起水肿。</p><p>　　3、少吃动物脂肪、不食动物内脏等。</p><p>　　4、不能长期食用高胆固醇的食物。高血压病患者要控制富含胆固醇的动物脂肪和其他食物(如蛋黄、动物内脏、鱼籽、虾、蟹黄、墨鱼等)。</p><p>　　5、少量喝茶。茶叶中含有少量茶碱、黄嘌呤，其利尿作用对治疗高血压病有利。</p><p>　　6、不能吸烟，香烟中的尼古丁，能刺激心脏和血管，使血压升高，加速动脉粥样硬化的形成。</p><p>　　以上就是小编与大家分享的关于高血压患不能吃什么的介绍，不知道大家有没有完全理解呢?还有的患者认为高血压吃点降压药就好了，没必要注意饮食，这是对高血压不够了解的原因，希望大家多多查阅相关内容。";
		String testString2 = "糖尿病更讲究的是饮食疗法，饮食中根据病人的体重和活动量，估计所需总热量，合理安排每天的饮食。含糖量高的食品不吃，含脂肪和淀粉的食品少吃，以吃蔬菜杂粮类为主，配以一定数量的优质蛋白质的食物。1、多食含纤维素的食物2、多吃五谷杂粮3、多食含B族维生素的食物1、禁食胆固醇较高的食物2、忌食糖制甜食3、忌抽烟、喝酒";
		// 获取Lucene的TokenStream对象
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("myfield", new StringReader(
					testString2));
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
