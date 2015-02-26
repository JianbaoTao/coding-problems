package com.tao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerUsage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter lines below:");
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) break; else lines.add(line);
        }
        System.out.println("Below are the lines from the input:");
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
