package br.ufscar.dc.pooa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufscar.dc.pooa.SiteNoticias;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String link = "https://g1.globo.com/";
    	String classe = "a.feed-post-link";
    	SiteNoticias noticia = new SiteNoticias(link);
    	noticia.setClasse(classe);
    	String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(String.format("dump-%s.csv", strNow))))) {
            pw.println("Tipo;Notícia;Link");
            Document doc = Jsoup.connect(noticia.getLink()).get();
            Elements titles = doc.select(noticia.getClasse());
            for (Element t : titles) {
            	pw.print(String.format("Principal;%s;", t.text())); //e se quiser mudar para secundário, como seria o melhor código para q essa mudança fosse fácil
            	Element parent = t; // como utilizamos a para selecionar o título, para encontrar o a, começamos pelo próprio t (que é o a) 
                while (parent != null && !parent.tagName().equals("a")) {
                    parent = parent.parent();
                }
                if (parent != null && parent.tagName().equals("a")) {
                    pw.print(String.format("\"%s\"", parent.attr("href")));
                }
                pw.print("\n");
            }
        }
    }
}
