package homework.exceptions;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.isAllEmpty;
import static org.apache.commons.lang3.StringUtils.length;

public class ExceptionTask {

    public static Optional<String> mergeStrings(String first, String second) {
        if (isAllEmpty(first, second)) {
            return Optional.empty();
        }
        return Optional.of(length(first) > length(second) ? join(first, second) : join(second, first));
    }

    public static long getPower(int n, int p) {
        if (n <= 0 || p <= 0) {
            return -1;
        }
        return (long) (Math.pow(n, p));
    }

    public static void customException(int a) {
        if (a == 0) {
            throw new InvalidZeroParameterException();
        }
        try {
            throwUncheckedException();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public static void exceptionProcessing() {
        try {
            throwCheckedException();
            throwUncheckedException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void throwCheckedException() throws Exception {
        throw new Exception("Checked exception");
    }

    private static void throwUncheckedException() {
        throw new RuntimeException("Unchecked exception");
    }
}
