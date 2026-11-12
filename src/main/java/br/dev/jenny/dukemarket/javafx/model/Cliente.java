package br.dev.jenny.dukemarket.javafx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dia
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private String endereço;
    private String cidade;
    private String uf;
    private Calendar dataCadastro;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCPF(){
        return cpf;
    }
    
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public String getEndereco(){
        return endereço;
    }
    
    public void setEndereco(String endereco){
        this.endereço = endereço;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getUF(){
        return uf;
    }
    
    public void setUF(String uf){
        this.uf = uf;
    }
    
    
    public void setDataCadastro(LocalDate localDateDataCadastro) {
    
        //localDateDataCadastro;

        // 1. Converter LocalDate para GregorianCalendar diretamente via ZonedDateTime
        this.dataCadastro = GregorianCalendar.from(localDateDataCadastro.atStartOfDay(ZoneId.systemDefault()));
        
    }
    
    
     public void setDataCadastro(String strDataCadastro) {
        try {
            this.dataCadastro = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.dataCadastro.setTime(sdf.parse(strDataCadastro));
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDataCadastro() {
        if (this.dataCadastro != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(this.dataCadastro.getTime());
        } else {
            return "";
        }
    }
    
    
    @Override
    public String toString() {

        return String.format("%3d | %15s | %30s | %50s | %30s | %3s | %10s", this.id,
                this.nome, this.cpf, this.endereço, this.cidade,
                this.uf, this.getDataCadastro());
    }
    
    
    
    
    
}
