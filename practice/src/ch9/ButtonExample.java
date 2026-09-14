package ch9;

public class ButtonExample {
    public static void main(String[] args) {
        // 로컬 클래스(중첩)
        Button button1 = new Button();
        class Button1Listener implements Button.ClickListener {
            @Override
            public void onClick() {
                System.out.println("버튼 1 클릭");
            }
        }
        button1.setClickListener(new Button1Listener());

        // 익명 클래스
        Button button2 = new Button();
        Button.ClickListener button2Listener = new Button.ClickListener() {
            @Override
            public void onClick() {
                System.out.println("버튼 2 클릭");
            }
        };
        button2.setClickListener(button2Listener);

        // 람다 사용
        Button button3 = new Button();
        button3.setClickListener(() -> System.out.println("버튼 3 클릭"));

        // 로컬 클래스(중첩) + 지역 변수 캡처
        Button button4 = createButton("버튼 4");

        // 정적 멤버 클래스(중첩)
        Button button5 = new Button();
        button5.setClickListener(new ButtonListener("버튼 5"));

        // 인스턴스 멤버 클래스(ButtonCase 내부 InnerButtonListener가 중첩)
        ButtonContainer buttonContainer = new ButtonContainer("버튼 6");
        Button button6 = buttonContainer.getButton();
        button6.setClickListener(buttonContainer.new InnerButtonListener());

        button1.click();
        button2.click();
        button3.click();
        button4.click();
        button5.click();
        button6.click();
    }

    private static Button createButton(String buttonName) {
        Button button = new Button();

//        불가능
//        buttonName += "(button)";

        class LocalButtonListener implements Button.ClickListener {
            @Override
            public void onClick() {
                System.out.println(buttonName + " 클릭");
            }
        }

        button.setClickListener(new LocalButtonListener());
        return button;
    }

    private static class ButtonListener implements Button.ClickListener {
        private final String buttonName;

        public ButtonListener(String buttonName) {
            this.buttonName = buttonName;
        }

        @Override
        public void onClick() {
            System.out.println(buttonName + " 클릭");
        }
    }
}

class ButtonContainer {
    private final Button button;
    private final String buttonName;

    ButtonContainer(String buttonName){
        this.button = new Button();
        this.buttonName = buttonName;
    }

    Button getButton() {
        return button;
    }

    class InnerButtonListener implements Button.ClickListener {
        @Override
        public void onClick() {
            System.out.println(buttonName + " 클릭");
        }
    }
}