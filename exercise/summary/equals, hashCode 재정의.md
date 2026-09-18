# equals()를 재정의하면 왜 hashCode()도 함께 재정의해야 할까?

## 재정의해야 하는 이유
- 두 객체의 동등성 비교 시 `equals()`와 `hashCode()` 메서드를 모두 사용하는 경우가 많기 때문이다.

## 개요
- HashSet이나 HashMap 같은 컬렉션은 항목을 찾을 때 hashCode() 결과가 같은지 우선 비교하고 그 다음 equals() 결과가 같은지 비교하여 동등 객체임을 확인한다.

```mermaid
graph LR;
  S["동등성 비교"]-->A;
  A{"hashCode()"}-->|같음|B{"equals()"};
  B-->|True|Y["동등함"];
  A-->|다름|N["동등하지 않음"];
  B-->|False|N
```

- Streams.distinct(), Collectors.toSet()과 같은 경우도 객체의 정상적인 equals/hashCode 계약을 전제로 한다.

- equals()만 사용하는 기능에서는 hashCode() 재정의 여부가 직접적인 문제가 되지는 않지만, 객체를 해시를 이용해 분류한 뒤 equals()로 동일성을 판단하는 기능에서는 둘의 계약이 반드시 맞아야 한다.

- 두 메서드를 오버라이딩할 때 equals() 결과가 같다면 hashCode()의 결괏값도 같게 만들어야 한다. 즉, `a.eqauls(b) == true`라면 `a.hashCode() == b.hashCode()`여야 한다. 역은 성립하지 않는다. 

## equals()를 재정의하고 hashCode()를 재정의하지 않았을 때
- 다음과 같이 name을 필드로 갖는 Member 클래스를 정의하고 두 Member 객체의 이름이 같으면 동등하게 판단한다고 하자.

```java
class Member {
    private final String name;

    public Member(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Member other) {
            return name.equals(other.name);
        }

        return false;
    }
}
```

```java
Member m1 = new Member("James");
Member m2 = new Member("James");

System.out.println(m1 == m2);   // false
System.out.println(m1.equals(m2));  // true
System.out.println(m1.hashCode() == m2.hashCode()); // false

System.out.println(m1.hashCode()); 
System.out.println(m2.hashCode());

Set<Member> members = new HashSet<>();
members.add(m1);

System.out.println(members.contains(m2));   // false
```

```
[ 실행 결과 ]
false
true
false
603742814
1067040082
false
```

- 위 코드에서 m1과 m2는 동일한 이름이므로 equals()는 true인데  hashCode() 값은 다르다. HashSet에 m1을 넣었을 때 논리적으로 m1과 m2는 동등하므로 contains(m2)도 true여야 한다.
- 하지만 HashSet은 먼저 hashCode()를 보기 때문에 두 객체의 hashCode()값이 다르므로 equals()를 호출할 기회도 없이 false를 반환한다.
- 이번엔 다음과 같이 HashSet에 두 객체를 모두 넣어 보자. 의도한 동작은 두 객체가 동등하여 HashSet에 중복되지 않게 들어가는 것인데, 실제로 크기를 확인해 보면 1이 아니라 2가 나온다. 

```java
Set<Member> members = new HashSet<>();
members.add(m1);
members.add(m2);

System.out.println(members.size());   // 2
```

## 여러 상황들
- 두 객체를 비교할 때 다음과 같은 세 가지 상황을 생각해 볼 수 있다.

**1. equals()가 true이지만 hashCode() 다름 → 잘못된 구현**
- 위의 예시가 이에 해당한다.

**2. hashCode()는 같지만 equals()는 false  → 정상적인 해시 충돌**
- 대표적인 예시는 String이다. 다음과 같은 예시를 보자.

```java
String a = "FB";
String b = "Ea";

System.out.println(a.hashCode()); // 2236
System.out.println(b.hashCode()); // 2236

System.out.println(a.equals(b));  // false

Set<String> set = new HashSet<>();
set.add("FB");
set.add("Ea");
System.out.println(set.size()); // 2
```

- a와 b는 hashCode() 값이 같지만 equals()는 false이다. 이 때 HashSet에 각 항목을 추가할 때 같은 해시 값을 가지더라도 최종적인 동등성 판정이 다름으로 판단되어 별개의 항목으로 들어갈 수 있는 것이다.

- 위의 Member 클래스를 다음과 같이 두 메서드를 모두 오버라이딩하고, equals 호출 시 호출되었음을 확인하는 코드를 넣어 본다.

```java
class Member {
    private final String name;

    public Member(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals 호출");
        if (this == obj) {
            return true;
        }

        if (obj instanceof Member other) {
            return name.equals(other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
```

- 이제 다음 코드를 실행해 보면, m2를 추가했을 때 두 객체의 hashCode()가 같기 때문에 HashMap에서 equals()를 호출하여 동등성 비교를 하였음을 알 수 있다. equals()도 true이기 때문에 m2 추가 시 Set에 m2는 추가되지 않고 false를 반환한다.

```java
Member m1 = new Member("James");
Member m2 = new Member("James");
System.out.println(m1.hashCode() == m2.hashCode()); // true

Set<Member> members = new HashSet<>();
System.out.println(members.add(m1));    // true
System.out.println(members.add(m2));    // false

System.out.println(members.size());   // 1
```

```
[ 실행 결과 ]
true
true
equals 호출
false
1
```

- 실제로 String, Long, UUID 등을 비롯해 많은 Java 클래스에서 서로 다른 객체가 같은 해시를 가질 가능성이 있다.

**3. equals()가 true인 것과 hashCode()가 같은 것이 동치인 경우**
- Integer 타입은 eqauls()로 자신이 가진 정수값을 비교하고 hashCode()도 그 정수값(int) 자체이다. 이런 식으로 말이다.

```java
@Override
public int hashCode() {
    return Integer.hashCode(value);
}
public static int hashCode(int value) {
    return value;
}

@Override
public boolean equals(Object obj) {
    if (obj instanceof Integer i) {
        return value == i.intValue();
    }
    return false;
}
```

- 이런 방식은 동등성을 결정하는 값 자체가 int 하나이고, 그 값을 그대로 해시 코드로 사용할 수 있을 때 현실적으로 사용할 수 있다.

- 반대로 String처럼 가능한 상태가 int 해시값의 2³²가지보다 압도적으로 많으면 완전한 동치는 애초에 불가능하다. 서로 다른 문자열 중 일부는 반드시 같은 해시값을 갖게 되기 때문이다. 이것이 equals()가 별도로 필요한 근본적인 이유이기도 하다.

## 그렇다면 hashCode()를 어떻게 재정의할 것인가?
- 보통은 동등성을 결정하는 필드를 먼저 정하고, equals()와 hashCode()에서 정확히 같은 필드 집합을 사용하는 원칙으로 작성한다.

- 예를 들어 위의 Member 클래스에 id라는 int 필드가 추가된다면 다음과 같이 작성할 수 있다.
```java
import java.util.Objects;

class Member {
    private final int id;
    private final String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Member other) {
            return id == other.id && name.equals(other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

- 핵심은 두 메서드 다 id, name을 동일하게 사용한다는 것이다. equals()가 true인 것은 비교에 사용된 id, name이 모두 같다는 것을 의미하는데, hashCode도 같은 입력값들로 계산하여야 같은 hashCode를 반환하게 될 수 있는 것이다.

- 참고로 Objects.hash(...)는 여러 값의 해시 코드를 조합해서 하나의 int 해시값을 만들어 주는 static 메서드이다.