import gals.*;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        try {
            // Lê e exibe o código fonte
            File sourceFile = new File("src/arquivo.txt");
            String sourceCode = app.readFile(sourceFile);
            printSourceCode(sourceCode);

            // Executa a análise
            executeAnalysis(sourceCode);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private String readFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    private static void printSourceCode(String sourceCode) {
        System.out.println("=".repeat(50));
        System.out.println("CÓDIGO FONTE:");
        System.out.println("=".repeat(50));

        // Formata e imprime o código-fonte, linha por linha
        String[] lines = sourceCode.split(";");
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty()) {
                System.out.println(line + ";");
            }
        }
        System.out.println("=".repeat(50) + "\n");
    }

    private static void executeAnalysis(String sourceCode) {
        System.out.println("EXECUÇÃO DO PROGRAMA:");
        System.out.println("-".repeat(50));

        try {
            // Inicializa os analisadores
            Lexico lexico = new Lexico(new StringReader(sourceCode));
            Semantico semantico = new Semantico();
            Sintatico sintatico = new Sintatico();

            // Executa a análise
            sintatico.parse(lexico, semantico);

            System.out.println("-".repeat(50));
            System.out.println("Programa executado com sucesso!");

        } catch (LexicalError e) {
            printError("Erro Léxico", e);
        } catch (SemanticError e) {
            printError("Erro Semântico", e);
        } catch (SyntaticError e) {
            printError("Erro Sintático", e);
        }
    }

    private static void printError(String errorType, Exception e) {
        System.err.println("\nERRO NA EXECUÇÃO!");
        System.err.println("-".repeat(50));
        System.err.println(errorType + ": " + e.getMessage());
        System.err.println("-".repeat(50));
    }
}