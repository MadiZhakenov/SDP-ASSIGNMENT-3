import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
// Task 1
// Old API
class LegacyOrderProcessor {
    void processLegacyOrder() {
        System.out.println("Processing order using legacy system.");
    }
}
// New interface
interface NewOrderProcessor {
    void processNewOrder();
}
// Adapter class
class OrderAdapter implements NewOrderProcessor {
    private LegacyOrderProcessor legacyOrderProcessor;
    OrderAdapter(LegacyOrderProcessor legacyOrderProcessor) {
        this.legacyOrderProcessor = legacyOrderProcessor;
    }
    public void processNewOrder() {
        legacyOrderProcessor.processLegacyOrder(); // Adapting the old method to the new interface
    }
}

//Task 2
// Renderer interface
interface Renderer {
    void renderShape(String shape);
}
// Windows renderer implementation
class WindowsRenderer implements Renderer {
    public void renderShape(String shape) {
        System.out.println("Rendering " + shape + " on Windows.");
    }
}
// Linux renderer implementation
class LinuxRenderer implements Renderer {
    public void renderShape(String shape) {
        System.out.println("Rendering " + shape + " on Linux.");
    }
}
// Abstract shape class
abstract class Shape {
    protected Renderer renderer;
    Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    abstract void draw();
}
// Circle shape
class Circle extends Shape {
    Circle(Renderer renderer) {
        super(renderer);
    }
    void draw() {
        renderer.renderShape("Circle");
    }
}
// Square shape
class Square extends Shape {
    Square(Renderer renderer) {
        super(renderer);
    }
    void draw() {
        renderer.renderShape("Square");
    }
}

//Task 3
// Common interface for files and directories
interface FileSystemComponent {
    void showDetails();
}
// Class for files
class File implements FileSystemComponent {
    private String name;
    public File(String name) {
        this.name = name;
    }
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
// Class for directories
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();
    public Directory(String name) {
        this.name = name;
    }
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

//Task 4
// Base interface
interface TextEditor {
    String write();
}
// Concrete component (plain text)
class SimpleText implements TextEditor {
    public String write() {
        return "This is plain text.";
    }
}
// Abstract decorator
abstract class TextDecorator implements TextEditor {
    protected TextEditor editor;
    public TextDecorator(TextEditor editor) {
        this.editor = editor;
    }
    public String write() {
        return editor.write();
    }
}
// Bold decorator
class BoldDecorator extends TextDecorator {
    public BoldDecorator(TextEditor editor) {
        super(editor);
    }
    public String write() {
        return "<b>" + super.write() + "</b>";
    }
}
// Italic decorator
class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(TextEditor editor) {
        super(editor);
    }
    public String write() {
        return "<i>" + super.write() + "</i>";
    }
}

//Task 5
// Subsystem for account management
class AccountSystem {
    void manageAccount() {
        System.out.println("Managing account...");
    }
}
// Subsystem for loan processing
class LoanSystem {
    void processLoan() {
        System.out.println("Processing loan...");
    }
}
// Subsystem for transaction handling
class TransactionSystem {
    void makeTransaction() {
        System.out.println("Making transaction...");
    }
}
// Facade class
class BankFacade {
    private AccountSystem accountSystem;
    private LoanSystem loanSystem;
    private TransactionSystem transactionSystem;
    public BankFacade() {
        this.accountSystem = new AccountSystem();
        this.loanSystem = new LoanSystem();
        this.transactionSystem = new TransactionSystem();
    }
    public void performBankOperations() {
        accountSystem.manageAccount();
        loanSystem.processLoan();
        transactionSystem.makeTransaction();
    }
}

//Task 6
//// Shape interface
//interface Shape {
//    void draw();
//}
//// Concrete Circle class
//class Circle implements Shape {
//    private String color; // Intrinsic property
//    private int x, y, radius; // Extrinsic properties
//    public Circle(String color) {
//        this.color = color;
//    }
//    public void setX(int x) {
//        this.x = x;
//    }
//    public void setY(int y) {
//        this.y = y;
//    }
//    public void setRadius(int radius) {
//        this.radius = radius;
//    }
//    public void draw() {
//        System.out.println("Drawing Circle [Color: " + color + ", x: " + x + ", y: " + y + ", radius: " + radius +
//                "]");
//    }
//}
//// ShapeFactory for managing flyweight objects
//class ShapeFactory {
//    private static final Map<String, Shape> circleMap = new HashMap<>();
//    public static Shape getCircle(String color) {
//        Circle circle = (Circle) circleMap.get(color);
//        if (circle == null) {
//            circle = new Circle(color);
//            circleMap.put(color, circle);
//            System.out.println("Creating circle of color: " + color);
//        }
//        return circle;
//    }
//}

// Interface for bank accounts
interface BankAccount {
    void accessAccount();
}
// Real bank account object
class RealBankAccount implements BankAccount {
    private String accountHolder;
    public RealBankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    public void accessAccount() {
        System.out.println("Accessing bank account of: " + accountHolder);
    }
}
// Proxy class
class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;
    private String accountHolder;
    public BankAccountProxy(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    public void accessAccount() {
        if (authenticate()) {
            if (realAccount == null) {
                realAccount = new RealBankAccount(accountHolder);
            }
            realAccount.accessAccount();
        } else {
            System.out.println("Access denied for: " + accountHolder);
        }
    }
    private boolean authenticate() {
        // Simple authentication logic
        return "authorizedUser".equals(accountHolder);
    }
}


public class Main {
    public static void main(String[] args) {
        LegacyOrderProcessor legacyProcessor = new LegacyOrderProcessor();
        NewOrderProcessor adapter = new OrderAdapter(legacyProcessor);
        adapter.processNewOrder(); // Working with the new interface while using old code

        Renderer windowsRenderer = new WindowsRenderer();
        Renderer linuxRenderer = new LinuxRenderer();
        Shape circle = new Circle(windowsRenderer);
        Shape square = new Square(linuxRenderer);
        circle.draw(); // Rendering Circle on Windows
        square.draw(); // Rendering Square on Linux

        File file1 = new File("File1.txt");
        File file2 = new File("File2.txt");
        Directory dir1 = new Directory("Folder1");
        Directory dir2 = new Directory("Folder2");
        dir1.addComponent(file1);
        dir2.addComponent(file2);
        dir1.addComponent(dir2);
        dir1.showDetails(); // Output the contents of Folder1, including nested directories and files

        TextEditor plainText = new SimpleText();
        TextEditor boldText = new BoldDecorator(plainText);
        TextEditor italicBoldText = new ItalicDecorator(boldText);
        System.out.println(plainText.write()); // Output: This is plain text.
        System.out.println(boldText.write()); // Output: <b>This is plain text.</b>
        System.out.println(italicBoldText.write()); // Output: <i><b>This is plain text.</b></i>

        BankFacade bankFacade = new BankFacade();
        bankFacade.performBankOperations(); // Simplified access to banking operations

//        private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};
//        public static void main(String[] args) {
//            for(int i=0; i < 20; ++i) {
//                Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
//                circle.setX(getRandomX());
//                circle.setY(getRandomY());
//                circle.setRadius(100);
//                circle.draw();
//            }
//        }
//        private static String getRandomColor() {
//            return colors[(int)(Math.random()*colors.length)];
//        }
//        private static int getRandomX() {
//            return (int)(Math.random()*100);
//        }
//        private static int getRandomY() {
//            return (int)(Math.random()*100);
//        }

        BankAccount authorizedAccount = new BankAccountProxy("authorizedUser");
        authorizedAccount.accessAccount(); // Access granted
        BankAccount unauthorizedAccount = new BankAccountProxy("unauthorizedUser");
        unauthorizedAccount.accessAccount(); // Access denied
    }
}
