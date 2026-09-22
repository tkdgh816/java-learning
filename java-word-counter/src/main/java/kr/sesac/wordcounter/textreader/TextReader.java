package kr.sesac.wordcounter.textreader;

public interface TextReader {
    default void read(String line){
        System.out.println(line);
    }
}
