package gl.linpeng.analyzer.sample;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author lin.peng
 * @since 1.0
 **/
public class StockAndVector {
    public static void main(String[] args) throws IOException {
        Set<StringWarp> diseaes = new HashSet<StringWarp>();
        diseaes.add(new StringWarp("感冒"));
        diseaes.add(new StringWarp("流行性感冒"));
        diseaes.add(new StringWarp("病毒性感冒"));
        diseaes.add(new StringWarp("流行性感冒伴胸膜渗漏流行性感冒病毒未标明"));
        diseaes.add(new StringWarp("流行性感冒伴喉炎流行性感冒病毒未标明"));
        diseaes.add(new StringWarp("病毒性肺炎伴流感"));
        diseaes.add(new StringWarp("流行性感冒并发脑炎流行性感冒病毒未标明"));
        diseaes.add(new StringWarp("副流行性感冒肺炎"));


        Stack<StringWarp> stack = new Stack<StringWarp>();
        stack.addAll(diseaes);
        Set<StringWarp> handled = new HashSet<StringWarp>();

        int i = 0;
        StringWarp disease = null;
        while ((disease = stack.pop()) != null && !stack.empty()) {
            handled.add(disease);
            SimHash hash1 = new SimHash(disease.getName(), 64);
            for (StringWarp d : diseaes) {
                if (d.getName().equalsIgnoreCase(disease.getName())) {
                    continue;
                }
                if (handled.contains(d)) {
                    continue;
                }
                i++;
                SimHash hash2 = new SimHash(d.getName(), 64);
                double semblance = hash1.getSemblance(hash2);
                if (disease.getScore() < semblance) {
                    disease.setId(d.getName());
                    disease.setScore(semblance);

                    // back write
                    d.setId(disease.getName());
                    d.setScore(semblance);
                }
                System.out.println(disease + " " + d + " " + semblance);
            }
        }
        System.out.println("=----->" + handled);
        System.out.println("=----->" + i);


    }

}
