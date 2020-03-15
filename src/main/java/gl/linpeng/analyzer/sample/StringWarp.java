package gl.linpeng.analyzer.sample;

/**
 * @author lin.peng
 * @since 1.0
 **/
public class StringWarp {
    private String name;
    private double score;
    private String id;

    public StringWarp(String str) {
        this.name = str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StringWarp{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringWarp that = (StringWarp) o;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
