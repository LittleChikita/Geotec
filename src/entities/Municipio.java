package entities;

import java.util.List;

public class Municipio {

    private String codigoIBGE;
    private String nome;
    private String microRegiao;
    private String estado;
    private String regiaoGeografica;
    private String areaKm;
    private String populacao;
    private String domicilios;
    private String pibTotal;
    private String idh;
    private String rendaMedia;
    private String rendaNominal;
    private String peaDia;
    private String idhEducacao;
    private String idhLongevidade;
    private String dataUltimaAtualizacao;

    public Municipio() {
    }

    public String getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(String codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMicroRegiao() {
        return microRegiao;
    }

    public void setMicroRegiao(String microRegiao) {
        this.microRegiao = microRegiao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegiaoGeografica() {
        return regiaoGeografica;
    }

    public void setRegiaoGeografica(String regiaoGeografica) {
        this.regiaoGeografica = regiaoGeografica;
    }

    public String getAreaKm() {
        return areaKm;
    }

    public void setAreaKm(String areaKm) {
        this.areaKm = areaKm;
    }

    public String getPopulacao() {
        return populacao;
    }

    public void setPopulacao(String populacao) {
        this.populacao = populacao;
    }

    public String getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(String domicilios) {
        this.domicilios = domicilios;
    }

    public String getPibTotal() {
        return pibTotal;
    }

    public void setPibTotal(String pibTotal) {
        this.pibTotal = pibTotal;
    }

    public String getIdh() {
        return idh;
    }

    public void setIdh(String idh) {
        this.idh = idh;
    }

    public String getRendaMedia() {
        return rendaMedia;
    }

    public void setRendaMedia(String rendaMedia) {
        this.rendaMedia = rendaMedia;
    }

    public String getRendaNominal() {
        return rendaNominal;
    }

    public void setRendaNominal(String rendaNominal) {
        this.rendaNominal = rendaNominal;
    }

    public String getPeaDia() {
        return peaDia;
    }

    public void setPeaDia(String peaDia) {
        this.peaDia = peaDia;
    }

    public String getIdhEducacao() {
        return idhEducacao;
    }

    public void setIdhEducacao(String idhEducacao) {
        this.idhEducacao = idhEducacao;
    }

    public String getIdhLongevidade() {
        return idhLongevidade;
    }

    public void setIdhLongevidade(String idhLongevidade) {
        this.idhLongevidade = idhLongevidade;
    }

    public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public String getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public static double converter(String valor) {
        String valorSemFormato = valor.replace(".", "").replace(",", ".");
        double valorConvertido = Double.parseDouble(valorSemFormato);
        return valorConvertido;
    }

    public double calcularDensidadeDemografica() {
        double populacaoNumerica = converter(populacao);
        double areaNumerica = converter(areaKm);

        double densidadeDemografica = populacaoNumerica / areaNumerica;

        return densidadeDemografica;
    }

    public double calcularPIBPerCapitaTotal() {
        double pibTotalNumerico = converter(pibTotal);
        double populacaoNumerica = converter(populacao);

        double pibPerCapitaTotal = pibTotalNumerico / populacaoNumerica;

        return pibPerCapitaTotal;
    }

    public String classificarIDH() {
        double idhNumerico = converter(idh);
        if (idhNumerico > 0.80) {
            return "Muito alto";
        } else if (idhNumerico >= 0.70 && idhNumerico <= 0.80) {
            return "Alto";
        } else if (idhNumerico >= 0.55 && idhNumerico < 0.70) {
            return "Médio";
        } else {
            return "Baixo";
        }
    }

    public String converterParaString(double valor) {
        String valorString = String.format("%.5f", valor);
        return valorString;
    }
}
