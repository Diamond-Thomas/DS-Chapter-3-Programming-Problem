import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Folder root = new Folder("root");
        Folder Documents = new Folder("Documents");
        Folder Pictures = new Folder("Pictures");
        Folder Vacations  = new Folder("Vacations ");


        root.addItem(Documents);
        root.addItem(Pictures);
        Pictures.addItem(Vacations);

        Vacations.addItem(new FileItem("photo1", 7777));
        Vacations.addItem(new FileItem("photo2", 6789));
        Vacations.addItem(new FileItem("photo3", 1357));



        Documents.addItem(new FileItem("ImportantDocumentLOL", 6543));
        Documents.addItem(new FileItem("AnotherDOC", 3939));

        Documents.addItem(new FileItem("RandomPIC1", 9595));
        Documents.addItem(new FileItem("RandomPIC1", 8654));


        System.out.println(FileSystemAnalyzer.calculateTotalSizeRecursive(Documents));
        FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);
        System.out.println("Largest File: " + largest.getName() + " (Size of file: " + largest.getSizeInKB() + " KB)");


        int fileCount = FileSystemAnalyzer.countFilesRecursive(root);
        System.out.println("Total files counted: " + fileCount);

        int recursiveCount = FileSystemAnalyzer.countFilesRecursive(root);
        int iterativeCount = FileSystemAnalyzer.countFilesIterative(root);

        assert recursiveCount == iterativeCount;



        //scanner  start

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Display File System Structure");
            System.out.println("2. Add File to a Folder");
            System.out.println("3. Add Subfolder");
            System.out.println("4. Run Recursive Audit");
            System.out.println("5. Run Iterative Audit & Verification");
            System.out.println("6. Exit");

            String inputLine = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(inputLine);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- File System Hierarchy ---");
                    FileSystemAnalyzer.printHierarchy(root, "");
                    System.out.print("----------------------------\n");
                    break;

                case 2:
                    System.out.print("Enter target folder name: ");
                    String targetFolder = scanner.nextLine().trim();
                    Folder targetParent = FileSystemAnalyzer.findFolder(root, targetFolder);

                    if (targetParent == null) {
                        System.out.println("[Error] Folder '" + targetFolder + "' not found.");
                        System.out.print("----------------------------\n");
                        break;
                    }

                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine().trim();

                    System.out.print("Enter file size in KB: ");
                    try {
                        int fileSize = Integer.parseInt(scanner.nextLine().trim());
                        if (fileSize < 0) {
                            System.out.println("[Error] File size cannot be negative.");
                            System.out.print("----------------------------\n");
                            break;
                        }
                        targetParent.addItem(new FileItem(fileName, fileSize));
                        System.out.println("Successfully added file '" + fileName + "'.");
                    } catch (NumberFormatException e) {
                        System.out.println("[Error] Invalid size format. Must be an integer.");
                        System.out.print("----------------------------\n");
                    }
                    break;

                case 3:
                    System.out.print("Enter target parent folder name: ");
                    String parentName = scanner.nextLine().trim();
                    Folder parentFolder = FileSystemAnalyzer.findFolder(root, parentName);

                    if (parentFolder == null) {
                        System.out.println("[Error] Parent folder '" + parentName + "' not found.");
                        System.out.print("----------------------------\n");
                        break;
                    }

                    System.out.print("Enter new subfolder name: ");
                    String subfolderName = scanner.nextLine().trim();
                    parentFolder.addItem(new Folder(subfolderName));
                    System.out.println("Successfully added subfolder '" + subfolderName + "'.");
                    System.out.print("----------------------------\n");
                    break;

                case 4:
                    System.out.println("\n--- Recursive Audit ---");
                    int totalFiles = FileSystemAnalyzer.countFilesRecursive(root);
                    int totalSize = FileSystemAnalyzer.calculateTotalSizeRecursive(root);
                    FileSystemAnalyzer.findLargestFileRecursive(root);

                    System.out.println("Total Files: " + totalFiles);
                    System.out.println("Total Storage Size: " + totalSize + " KB");
                    if (largest != null) {
                        System.out.println("Largest File: " + largest.getName() + " (" + largest.getSizeInKB() + " KB)");
                        System.out.print("----------------------------\n");
                    } else {
                        System.out.println("Largest File: No files found.");
                        System.out.print("---------------------------- \n");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Iterative Audit & Verification ---");
                     FileSystemAnalyzer.countFilesIterative(root);
                     FileSystemAnalyzer.countFilesRecursive(root);

                    System.out.println("Iterative File Count: " + iterativeCount);
                    System.out.println("Recursive File Count: " + recursiveCount);

                    assert iterativeCount == recursiveCount;
                    System.out.println("Verification Passed: Both counts match!");
                    System.out.print("---------------------------- \n");
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting program.");
                    System.out.print("---------------------------- \n");
                    break;

                default:
                    System.out.println("[Error] Invalid choice. Select an option from 1 to 6.");
                    System.out.print("---------------------------- \n");
            }
        }
        scanner.close();
    }
    }
