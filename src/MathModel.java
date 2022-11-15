import java.util.ArrayList;
import java.util.List;

public class MathModel implements Model {

    private String res;

    public MathModel() {

    }

    @Override
    public void setText(String s) {
        res = s;
    }

    @Override
    public String execute() {
        while (findOper() != -1){
            String[] nums = findNums(findOper());
            calculate(findOper(), nums);
        }
        return res;
    }

    private int findOper(){
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '*' || res.charAt(i) == '/'){
                return i;
            }
        }
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '+' || res.charAt(i) == '-'){
                return i;
            }
        }
        return -1;
    }

    private String[] findNums(int index){
        String[] nums = new String[2];
        for (int i = index-1; i >= 0; i--) {
            if (!((res.charAt(i) >= '0' && res.charAt(i) <= '9') || res.charAt(i) == '.' || res.charAt(i) == ',')){
                nums[0] = (res.substring(i + 1, index));
                break;
            }
            if (i == 0){
                nums[0] = (res.substring(i, index));
            }
        }
        for (int i = index+1; i < res.length(); i++) {
            if (!((res.charAt(i) >= '0' && res.charAt(i) <= '9') || res.charAt(i) == '.' || res.charAt(i) == ',')){
                nums[1] = (res.substring(index + 1, i));
                break;
            }
            if (i == res.length() -1){
                nums[1] = (res.substring(index + 1));
            }
        }
        return nums;
    }

    private void calculate(int index, String[] nums){
        char oper = res.charAt(index);
        double num1 = Double.parseDouble(nums[0]);
        double num2 = Double.parseDouble(nums[1]);
        double res = 0;
        switch (oper){
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.res.substring(0, index-nums[0].length()));
        stringBuilder.append(res);
        stringBuilder.append(this.res.substring(index + nums[1].length() + 1));
        this.res = stringBuilder.toString();
    }
}
