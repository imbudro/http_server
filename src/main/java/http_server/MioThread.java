package http_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            DataOutputStream outBinary = new DataOutputStream(s.getOutputStream());

            File file = new File("negozio/index.html");

            String firstLine = in.readLine();
            System.out.println("------");
            System.out.println(firstLine);
            System.out.println("------");
            String[] a = firstLine.split(" ");
            String path = a[1];
            String method = a[0];
            String h;

            do {
                h = in.readLine();
                System.out.println(h);
            } while (!h.isEmpty());
            System.out.println("la richiesta è terminata, ora rispondo!");

            if (method =="GET") {
                String response = "Method not allowed";
                out.println("HTTP/1.0 405 METHOD NOT ALLOWED");
                out.println("Content-Type: text/html");
                out.println("Content-Length: " + response.length());
                out.println("Server: BudroServer");
                out.println("");
                out.println(response);
            }else{
                if (path.endsWith("/")) {
                    path = path + "index.html";
                }
            }

            

            if (file.exists()) {
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Length: " + file.length() + "");
                out.println("Content-Type: " + getContentType(file) + "");
                out.println("");
                InputStream input = new FileInputStream(file);
                byte[] buf = new byte[8192];
                int n;
                while ((n = input.read(buf)) != -1) {
                    outBinary.write(buf, 0, n);
                }
                input.close();
            }

            
            

            s.close();
        } catch (Exception e) {
        }
        
    }

    private static String getContentType(File f) {

        String nomeFile = f.getName();
        int dopoPunto = nomeFile.lastIndexOf(".");
        String estensione = "";
    
        if (dopoPunto > 0) {
            estensione = nomeFile.substring(dopoPunto + 1).toLowerCase();
    
            switch (estensione) {
                // HTML, CSS, JavaScript
                case "html":
                    return "text/html";
                case "css":
                    return "text/css";
                case "js":
                    return "text/js";
                case "json":
                    return "application/json";
        
                // Audio
                case "mp3":
                    return "audio/mpeg";
                case "wav":
                    return "audio/wav";
                case "m4a":
                    return "audio/mp4";
        
                // Video
                case "mp4":
                    return "video/mp4";
        
                // Immagini (Grafica)
                case "jpg":
                    return "image/jpg";
                case "jpeg":
                    return "image/jpeg";
                case "png":
                    return "image/png";
                case "gif":
                    return "image/gif";
                case "webp":
                    return "image/webp";
                case "svg":
                    return "image/svg+xml";
                case "ico":
                    return "image/ico";
        
                // Documenti di testo
                case "txt":
                    return "text/plain";
                case "md":
                    return "text/markdown";
                case "xml":
                    return "application/xml";
                case "csv":
                    return "text/csv";
                case "pdf":
                    return "application/pdf";
        
                // File compressi
                case "zip":
                    return "application/zip";
                case "tar":
                    return "application/x-tar";
                case "gz":
                    return "application/gzip";
                case "rar":
                    return "application/x-rar-compressed";
        
                // Altri tipi comuni
                case "exe":
                    return "application/vnd.microsoft.portable-executable";
                case "apk":
                    return "application/vnd.android.package-archive";
                case "iso":
                    return "application/x-iso9660-image";
                case "psd":
                    return "application/vnd.adobe.photoshop";
        
                default:
                    return "application/octet-stream"; // Se non riconosciuto, tipo generico
            }
        } else {
            return "no estensioni";
        }
    }
    
    
}