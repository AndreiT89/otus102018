package ru.otus.levelone;
import java.util.Arrays;
import com.google.common.base.Joiner;

public class Application {
    public static void main(String[] args){
        Application app = new Application();
        app.joinString();
    }
    private void joinString(){
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList("Hello","World!")));
    }
}
