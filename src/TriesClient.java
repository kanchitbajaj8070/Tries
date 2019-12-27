import java.util.Objects;

public class TriesClient {
    public static void main(String[] args) {
        String[]words={"art","arts","bugs","boy","amit","see","sea","seen"};
        Tries tries= new Tries();
        for(String w:words)
            tries.addWord(w);
        tries.display();
        tries.isPresent("arts");
        tries.remove("art");
        tries.remove("arts");
        System.out.println(tries.numWords);
        tries.display();

    }
}
