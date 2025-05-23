import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics();
        Scanner scanner = new Scanner(System.in);
        String filePath;
        int rightPathamount=0;
        while (true) {
            System.out.println("Введите полный путь к файлу:");
            filePath = scanner.nextLine();

            File file = new File(filePath);

            boolean fileExists = file.isFile();
            boolean isDirectory = file.isDirectory();

            if (fileExists == true) {
                rightPathamount++;
                System.out.println("Путь к файлу указан верно");
                System.out.println("Это файл номер "+rightPathamount);

                try (FileReader fileReader = new FileReader(filePath);
                    BufferedReader reader = new BufferedReader(fileReader))
                {
                    int linesCount = 0;

                String line;

                    while ((line = reader.readLine()) != null) {
                        int length = line.length();

                        if (line.length() > 1024){
                            throw new LongLineException("Ошибка! Строка"+ (linesCount + 1) +" содержит "+ line.length() +" символов.");
                        }
                        linesCount++;
                        LogEntry a = new LogEntry(line);
                        stats.addEntry(a);
                        System.out.println(a.getIPadress());
                        System.out.println(a.getDateTime());
                        System.out.println(a.getMethod());
                        System.out.println(a.getPath());
                        System.out.println(a.getResponceCode());
                        System.out.println(a.getResponceSize());
                        System.out.println(a.getReferer());
                        System.out.println(a.getUserAgent());
                        System.out.println(a.getUserAgent().getBrowser());
                        System.out.println(a.getUserAgent().getOs());

                        System.out.println("----------");
                    }
                    System.out.println("Средний трафик в час: " + stats.getTrafficRate() + " байт/час");
                } catch (LongLineException e){System.err.println(e.getMessage());
                } catch (Exception ex) {
                    System.err.println("Ошибка при чтении файла: " + ex.getMessage());
                }

            } else if ( isDirectory == true) {
                System.out.println("Указанный путь является путём к папке");continue;
            }
                else {
                    System.out.println("Указаный файл не существует, либо путь указан неверно");continue;
                }
            }
        }
    }


//    private static String parseUserAgent(String userAgent) {
//        try {
//            int start = userAgent.indexOf('(');
//            int end = userAgent.indexOf(')', start + 1);
//            if (start == -1 || end == -1) return null;
//
//            String bracketContent = userAgent.substring(start + 1, end);
//            String[] parts = bracketContent.split(";");
//            if (parts.length >= 2) {
//                String fragment = parts[1];
//            }
//            String fragment = parts[1].trim();
//            String userAgentPart = fragment.split("/")[0].trim();
//            return userAgentPart;
//        } catch (Exception e) {
//            return null;
//        }
//    }