# Checked Exception, Unchecked Exception 차이

## Checked Exception
- Checked Exception은 컴파일러가 예외 처리를 강제하는 예외
- Exception을 상속하면서 RuntimeException의 하위가 아닌 예외
- 예외를 처리하는 방법
  - try-catch문으로 직접 처리하기: 여기에서 예외를 직접 처리한다.
  - throws로 상위 호출자에게 넘기기: 여기에서는 예외를 처리하지 않고 호출자에게 넘긴다.
- 파일이 존재하지 않거나, 네트워크 통신 실패, DB 접근 실패 등 호출자가 현실적으로 대응할 필요가 있는 외부적 실패를 표현하는 경우

## Unchecked Exception
- Unchecked Exception은 컴파일러가 예외 처리 여부를 검사하지 않는 예외
- RuntimeException을 상속
- 프로그램의 사용법이나 상태가 잘못된 경우를 표현하는 경우

## 둘을 왜 구분했을까?
- 호출자가 반드시 의식해야 하는 실패와 컴파일러가 처리를 강제할 필요가 없는 실패를 구분하려고 했기 때문임
- Checked Exception은 프로그래머의 실수라기보다는 외부 환경의 실패이며 이런 실패를 개발자가 실수로 무시하지 않도록 하고 싶음
- Checked Exception은 API에 실패 가능성을 명시적으로 표현하기 위한 장치이기도 함
- Unchecked Exception은 대체로 호출자가 catch에서 복구하기보다는 코드를 고쳐야 하는 문제임