package gl.linpeng.analyzer.sample;

/**
 * @author lin.peng
 * @since 1.0
 **/
public class CheckTheSameDemo {


    public static void main(String[] args) throws Exception {
        String str = "感冒";
        String str2 = "流行性感冒";
        String str3 = "风湿性感冒炎症";
        Long begin = System.currentTimeMillis();


        SimHash hash1 = new SimHash(str, 64);
        SimHash hash2 = new SimHash(str2, 64);
        SimHash hash3 = new SimHash(str3, 64);


        System.out.println(hash1.getSemblance(hash2));
        System.out.println(hash2.getSemblance(hash3));
        System.out.println(hash1.getSemblance(hash3));

        System.out.println("Cost-> " + (System.currentTimeMillis() - begin));
    }
}
