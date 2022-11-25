import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateToSqlDate {
    public static void main(String[] args) throws ParseException {
        Date dataUtil = new Date();
        System.out.println("Java util date: " + dataUtil);   
        
        java.sql.Date data_sql01 = converteData(dataUtil);
        System.out.println("Java sql date: " + data_sql01);


        //data formatada

        String dataSF = "01-01-2001";

        Date formataData = new SimpleDateFormat("dd-MM-yyyy").parse(dataSF);
        System.out.println("DATA SEM FORMATAÇÃO: "+formataData);

        java.sql.Date data_sql02 = converteData(formataData);
        System.out.println("DATA FORMATADA E CONVERTIDA: "+data_sql02);
    
    }

    private static java.sql.Date converteData(java.util.Date data) {
        java.sql.Date minhaData = new java.sql.Date(data.getTime());
        return minhaData;
    }
}
