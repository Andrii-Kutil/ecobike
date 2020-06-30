package util;

import exception.WrongValuesException;
import java.util.List;
import thread.MyThread;

public class Searcher {
    private static final String PATTERN_WITH_USER_PARAMETERS = "%s.*%s.*%s.*%s.*%s.*%s.*%s.*";

    public void findCertainBikes(List<String> content, String userRequest) {
        String[] arguments = userRequest.split(";");
        arguments = checkArgumentsLength(arguments);
        String format = formUserRequestPatternToSearch(arguments);
        divideTaskBetweenThreads(content, format);
    }

    private String formUserRequestPatternToSearch(String[] arguments) {
        return String.format(PATTERN_WITH_USER_PARAMETERS, arguments[0]
                        .toLowerCase().trim(),
                arguments[1].toLowerCase().trim(), arguments[2].toLowerCase().trim(),
                arguments[3].toLowerCase().trim(), arguments[4].toLowerCase().trim(),
                arguments[5].toLowerCase().trim(), arguments[6].toLowerCase().trim());
    }

    private void divideTaskBetweenThreads(List<String> content, String format) {
        MyThread thread1;
        MyThread thread2 = null;
        MyThread thread3 = null;
        int contentSize = content.size();
        int fromIndex = 0;
        int toIndex = contentSize / 3 + contentSize % 3;
        thread1 = new MyThread(fromIndex, toIndex, format);
        contentSize -= toIndex;
        if (contentSize > 0) {
            fromIndex = toIndex;
            toIndex = fromIndex + contentSize / 2 + contentSize % 2;
            thread2 = new MyThread(fromIndex, toIndex, format);
        }
        contentSize -= toIndex - fromIndex;
        if (contentSize > 0) {
            thread3 = new MyThread(toIndex, content.size(), format);
        }
        thread1.start();
        if (thread2 != null) {
            thread2.start();
        }
        if (thread3 != null) {
            thread3.start();
        }
    }

    private String[] checkArgumentsLength(String[] arguments) {
        if (arguments.length < 7) {
            String[] argumentsWithEmptyValues = new String[7];
            System.arraycopy(arguments, 0, argumentsWithEmptyValues, 0, arguments.length);
            for (int i = argumentsWithEmptyValues.length - 1; i >= 0; i--) {
                if (argumentsWithEmptyValues[i] == null) {
                    argumentsWithEmptyValues[i] = "";
                }
            }
            return argumentsWithEmptyValues;
        } else if (arguments.length > 7) {
            throw new WrongValuesException("Failed! Please, input correct values.");
        }
        return arguments;
    }
}
