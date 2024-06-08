package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVUtils {

    public static List<Municipio> lerCSV(String caminho) {
        List<Municipio> municipios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho, StandardCharsets.UTF_8))) {
            String linha;
            String[] headers = br.readLine().split(";");
            NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
            while ((linha = br.readLine()) != null) {
                String[] values = linha.split(";");
                Municipio municipio = new Municipio();
                municipio.setCodigoIBGE(values[0]);
                municipio.setNome(values[1]);
                municipio.setMicroRegiao(values[2]);
                municipio.setEstado(values[3]);
                municipio.setRegiaoGeografica(values[4]);
                municipio.setAreaKm(values[5]);
                municipio.setPopulacao(values[6]);
                municipio.setDomicilios(values[7]);
                municipio.setPibTotal(values[8]);
                municipio.setIdh(values[9]);
                municipio.setRendaMedia(values[10]);
                municipio.setRendaNominal(values[11]);
                municipio.setPeaDia(values[12]);
                municipio.setIdhEducacao(values[13]);
                municipio.setIdhLongevidade(values[14]);
                municipios.add(municipio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return municipios;
    }
}
