public class ConsoleLogger implements Logger{
    @Override
    public void log(String message) {
        /*
         * print message to console
         */
        System.out.print(message);
    }
}
