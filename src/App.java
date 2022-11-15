public class App {
    public static void main(String[] args) {
        Presenter presenter = new Presenter(new MyView(), new MathModel());
        presenter.onClick();
    }
}
