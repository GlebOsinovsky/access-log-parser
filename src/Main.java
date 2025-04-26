import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
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
                    int googlebotCount = 0;
                    int yandexbotCount = 0;

                String line;

                    while ((line = reader.readLine()) != null) {
                        int length = line.length();

                        if (line.length() > 1024){
                            throw new LongLineException("Ошибка! Строка"+ (linesCount + 1) +" содержит "+ line.length() +" символов.");
                        }
                        linesCount++;
// далее для каждой строки извленкаем компоненты

                        // извлечение IP-адреса
                        int firstSpace = line.indexOf(' ');
                        String ip = line.substring(0, firstSpace);

                        // извлечение двух пропущенных свойств
                        String rest = line.substring(firstSpace + 1);
                        int nextSpace1 = rest.indexOf(' ');
                        int nextSpace2 = rest.indexOf(' ', nextSpace1 + 1);
                        String prop1 = rest.substring(0, nextSpace1);
                        String prop2 = rest.substring(nextSpace1 + 1, nextSpace2);
                        rest = rest.substring(nextSpace2 + 1);

                        // извлечение даты и времени
                        int bracketStart = rest.indexOf('[');
                        int bracketEnd = rest.indexOf(']');
                        String dateTime = rest.substring(bracketStart + 1, bracketEnd);
                        rest = rest.substring(bracketEnd + 2);

                        // извлечение метода и пути
                        int quoteStart = rest.indexOf('"');
                        int quoteEnd = rest.indexOf('"', quoteStart + 1);
                        String request = rest.substring(quoteStart + 1, quoteEnd);
                        String[] requestParts = request.split(" ");
                        String method = requestParts.length > 0 ? requestParts[0] : "";
                        String path = requestParts.length > 1 ? requestParts[1] : "";
                        rest = rest.substring(quoteEnd + 2);

                        // извлечение HTTP-кода и размера данных
                        int space1 = rest.indexOf(' ');
                        int space2 = rest.indexOf(' ', space1 + 1);
                        String httpCode = rest.substring(0, space1);
                        String dataSize = rest.substring(space1 + 1, space2);
                        rest = rest.substring(space2 + 1);

                        // извлечение referer
                        quoteStart = rest.indexOf('"');
                        quoteEnd = rest.indexOf('"', quoteStart + 1);
                        String referer = rest.substring(quoteStart + 1, quoteEnd);
                        rest = rest.substring(quoteEnd + 2);

                        // извлечение User-Agent
                        quoteStart = rest.indexOf('"');
                        quoteEnd = rest.indexOf('"', quoteStart + 1);
                        String userAgent = rest.substring(quoteStart + 1, quoteEnd);

                        System.out.println("User-Agent: " + userAgent);
                        System.out.println(parseUserAgent(userAgent));
                        parseUserAgent(userAgent);

                        String bot = parseUserAgent(userAgent);
                        if ("Googlebot".equals(bot)) {
                            googlebotCount++;
                        } else if ("YandexBot".equals(bot)) {
                            yandexbotCount++;
                        }
                    }
                    double googlebotPart = (100.0*googlebotCount)/linesCount;
                    double yandexbotPart = (100.0*yandexbotCount)/linesCount;

                    System.out.println("кол-во Googlebot " +googlebotCount);
                    System.out.println("кол-во YandexBot " +yandexbotCount);
                    System.out.println("кол-во запросов " +linesCount);
                    System.out.println("Общее кол-во строк "+linesCount);
                    System.out.println("доля запросов от YandexBot: " + yandexbotPart);
                    System.out.println("доля запросов от Googlebot: " +googlebotPart);

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

    private static String parseUserAgent(String userAgent) {
        try {
            int start = userAgent.indexOf('(');
            int end = userAgent.indexOf(')', start + 1);
            if (start == -1 || end == -1) return null;

            String bracketContent = userAgent.substring(start + 1, end);
            String[] parts = bracketContent.split(";");
            if (parts.length >= 2) {
                String fragment = parts[1];
            }
            String fragment = parts[1].trim();
            String userAgentPart = fragment.split("/")[0].trim();
            return userAgentPart;
        } catch (Exception e) {
            return null;
        }
    }
    }


