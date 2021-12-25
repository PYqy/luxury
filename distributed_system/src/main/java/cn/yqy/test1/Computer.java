package cn.yqy.test1;

public interface Computer<E, F> {
    void makeCHorus(E x, F y);
}

class Chorus<E, F> implements Computer<E, F> {

    public void makeCHorus(E x, F y) {
        x.toString();
        y.toString();
    }
}

class YueQi {
    @Override
    public String toString() {
        System.out.println("|351-|135_|");
        return "";
    }
}

class Singer {
    @Override
    public String toString() {
        System.out.println("你和我，我和你，同住地球村");
        return "";
    }
}
