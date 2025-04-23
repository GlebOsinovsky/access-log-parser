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
                int maxLength = 0;
                int minLength = Integer.MAX_VALUE;
                String line;

                    while ((line = reader.readLine()) != null) {
                        int length = line.length();

                        if (line.length() > 1024){
                            throw new LongLineException("Ошибка! Строка"+ (linesCount + 1) +" содержит "+ line.length() +" символов.");
                        }
                        linesCount++;
                        maxLength = Math.max(maxLength, line.length());
                        minLength = Math.min(minLength, line.length());
                    }
                    if (linesCount ==0) minLength =0;

                    System.out.println("Общее кол-во строк "+linesCount);
                    System.out.println("Длина самой длинной строки "+maxLength);
                    System.out.println("Длина самой короткой строки" + minLength);

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


