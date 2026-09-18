# Checked Exception, Unchecked Exception 차이

## Checked Exception
- Checked Exception은 컴파일러가 예외 처리를 강제하는 예외이다.
- Exception을 상속하면서 RuntimeException의 하위가 아닌 예외이다.
- 예외를 처리하는 방법
  - try-catch문으로 직접 처리하기: 여기에서 예외를 직접 처리한다.
  - throws로 상위 호출자에게 넘기기: 여기에서는 예외를 처리하지 않고 호출자에게 넘긴다.
- 파일이 존재하지 않거나, 네트워크 통신 실패, DB 접근 실패 등 호출자가 현실적으로 대응할 필요가 있는 외부적 실패를 표현하는 경우에 해당한다.
- Checked Exception은 실행 중 발생할 수 있는 예외이지만, 컴파일러가 그 예외의 처리 여부를 컴파일 시점에 검사한다. Checked Exception은 컴파일 시 발생하는 것이 아니다. 실행 중에 발생하는 것이다.

## Unchecked Exception
- Unchecked Exception은 컴파일러가 예외 처리 여부를 검사하지 않는 예외이다.
- RuntimeException을 상속한다.
- 프로그램의 사용법이나 상태가 잘못된 경우를 표현하는 경우에 해당한다.

## 둘을 왜 구분했을까?
- 호출자가 반드시 의식해야 하는 실패와 컴파일러가 처리를 강제할 필요가 없는 실패를 구분하려고 했기 때문이다.
- Checked Exception은 프로그래머의 실수라기보다는 외부 환경의 실패이며 이런 실패를 개발자가 실수로 무시하지 않도록 하고 싶을 때이다.
- Checked Exception은 API에 실패 가능성을 명시적으로 표현하기 위한 장치이기도 하다.
- Unchecked Exception은 대체로 호출자가 catch에서 복구하기보다는 코드를 고쳐야 하는 문제이다.

## 예시
1. Unchecked Exception을 처리하지 않음: NullPointerException은 Unchecked Exception이다. 컴파일러가 처리를 강제하지 않으므로 예외를 처리하지 않아도 실행된다.

```java
String str = null;
System.out.println(str.length());
```

```
[ 실행 결과 ]
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "str" is null
	at excercise.Ex3.main(Ex3.java:6)
```

2. Unchecked Exception을 try-catch문으로 처리

```java
String str = null;
try {
    System.out.println(str.length());
} catch(NullPointerException e){
    System.out.println("예외 처리됨");
}
````

```
[ 실행 결과 ]
예외 처리됨
```

3. Checked Exception을 try-catch문으로 직접 처리: FileNotFoundException은 Checked Exception이다. 예시의 test.txt는 존재하지 않는 파일이다.

```java
try {
    FileReader reader = new FileReader("test.txt");
} catch (FileNotFoundException e) {
    System.out.println("파일을 찾을 수 없습니다.");
}
```

```
[ 실행 결과 ]
파일을 찾을 수 없습니다.
```

4. Checked Exception을 throws로 넘기기

```java
public static void main(String[] args) {
    try {
        readFile();
    } catch (FileNotFoundException e) {
        System.out.println("파일을 찾을 수 없습니다.");
    }
}

public void readFile() throws FileNotFoundException {
    FileReader reader = new FileReader("test.txt");
}
```

- readFile 메서드 자체는 FileNotFoundException을 직접 처리하지 않고 이 메서드를 호출하는 쪽으로 예외를 전달한다. 예시에서는 main 메서드가 readFile 메서드를 호출하므로 main에서 이 예외를 처리하거나 또다시 throws로 예외를 넘길 수 있다.