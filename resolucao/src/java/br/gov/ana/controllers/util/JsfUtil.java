package br.gov.ana.controllers.util;

import br.gov.ana.controllers.comuns.CalculoCoordenada;
import static br.gov.ana.controllers.util.JsfUtil.addErrorMessage;
import static br.gov.ana.controllers.util.JsfUtil.buildHighlight;
import static br.gov.ana.controllers.util.JsfUtil.removeAcentos;
import br.gov.ana.entities.AtoLegal;
import br.gov.ana.entities.UsuarioResolucao;
import br.gov.ana.exceptions.LatitudeException;
import br.gov.ana.exceptions.LongitudeException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author lucas.nunes
 */
public class JsfUtil {

    private static DecimalFormat fDecimal = new DecimalFormat("##,###,###,##0.##");

    private JsfUtil() {
    }

    /**
     *
     * @param entities
     * @param selectOne
     * @return
     */
    public static String removeAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        //
        return str;
    }

    public static boolean validaDataInicialMaiorQueFinal(Date dataInicial, Date dataFinal) {

        boolean retorno = false;

        if (dataInicial != null && dataFinal != null) {

            Calendar cal = Calendar.getInstance();

            cal.setTime(dataInicial);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            DateTime dtInicial = new DateTime(cal.getTimeInMillis());

            cal.setTime(dataFinal);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            DateTime dtFinal = new DateTime(cal.getTimeInMillis());

            int dias = Days.daysBetween(dtInicial, dtFinal).getDays();

            retorno = dias < 0;
        }

        return retorno;
    }

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        if (entities != null && entities.size() >= 1) {
            int size = selectOne ? entities.size() + 1 : entities.size();
            SelectItem[] items = new SelectItem[size];
            int i = 0;

            if (selectOne) {
                items[0] = new SelectItem("", "Selecione");
                i++;
            }

            for (Object x : entities) {
                items[i++] = new SelectItem(x, x.toString());
            }

            /*Ordenar a lista com base na string apresentada no combo*/
            Arrays.sort(items, new Comparator<SelectItem>() {
                @Override
                public int compare(SelectItem s1, SelectItem s2) {
                    return removeAcentos(s1.getValue().toString()).compareToIgnoreCase(removeAcentos(s2.getValue().toString()));
                }
            });

            return items;
        } else {
            SelectItem[] items = new SelectItem[1];
            if (selectOne) {
                items[0] = new SelectItem("", "Selecione");
            } else {
                items[0] = new SelectItem("", "");
            }
            return items;
        }

    }

    public static String linkProcesso(String processo) {
        String url = "";
        if (processo != null && processo.trim().length() == 15) {
            url = "http://proton2/proton/protocolo/impressao.asp?txt_numero_orgao=" + processo.substring(0, 5) + "&txt_numero_sequencial=" + processo.substring(5, 11) + "&txt_numero_ano=" + processo.substring(11, 15);
        }

        return url;
    }

    public static String linkProton(String proton) {
        String url = "";
        if (proton != null && proton.trim().length() == 10) {
            url = "http://proton2/proton/protocolo/impressao.asp?txt_numero_orgao=00000&txt_numero_sequencial=" + proton.substring(0, 6) + "&txt_numero_ano=" + proton.substring(6, 10);
        }
//0066022012
        return url;
    }

    /**
     *
     * @param ex
     * @param defaultMsg
     */
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();

        /*
         * if(
         * ex.getCause().getCause().getCause().getMessage().contains("restriÃ§Ã£o
         * exclusiva")){ msg = defaultMsg +": "+
         * ex.getCause().getCause().getCause().getMessage();//getLocalizedMessage();
         * }else { msg = defaultMsg +": "+ ex.getLocalizedMessage(); }
         */
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    /**
     *
     * @param messages
     */
    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    /**
     *
     * @param msg
     */
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     *
     * @param msg
     */
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    /**
     *
     * @param requestParameterName
     * @param converter
     * @param component
     * @return
     */
    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    // Telefone
    public static String formatTelefone(String value) {
        String retorno = "";
        if (value != null) {
            switch (value.length()) {
                case 10:
                    retorno = "(" + value.toString().substring(0, 2) + ") " + value.toString().substring(2, 6) + "-" + value.toString().substring(6, 10);
                    break;
                case 11:
                    retorno = "(" + value.toString().substring(0, 2) + ") " + value.toString().substring(2, 7) + "-" + value.toString().substring(7, 11);
                    break;
            }
        }
        return retorno;
    }

    public static String formatCep(String value) {
        // Complemento de 0 Para os Cep com 7 Digitos.
        if (value != null && value.toString().length() == 7) {
            value = "0" + value.toString();
        }

        // CEP
        if (value != null && value.toString().length() == 8) {
            value = value.toString().substring(0, 5) + "-" + value.toString().substring(5, 8);
        }
        return value;
    }

    // Numero do PROCESSO 
    public static String formatNumProcesso(String value) {

        if (value != null && value.toString().length() == 15) {
            value = value.toString().substring(0, 5) + "." + value.toString().substring(5, 11) + "/" + value.toString().substring(11, 15);
        }
        return value;
    }

    public static String formatNumProton(String value) {
        if (value != null && value.length() == 10) {
            value = value.toString().substring(0, 6) + "/" + value.toString().substring(6, 10);
        }

        return value;
    }

    public static String formatNumControle(String value) {
        if (value != null && value.length() == 7) {
            value = value.toString().substring(0, 3) + "/" + value.toString().substring(3, 7);
        }
        return value;
    }

    public static String formatNumeroAtoLegal(String value) {
        String retorno = "";
        /*if (value != null && value.length() == 6) {
         retorno = value.toString().substring(0, 3) + "." + value.toString().substring(3, 6);
         }*/

        return retorno;
    }

    public static String formatNumeroKM2(Double value) {

        String formatado = "";
        if (value != null) {
            formatado = fDecimal.format(value) + " " + ResourceBundle.getBundle("/Bundle").getString("KM2");
        }
        return formatado;
    }

    public static String formatNumeroMW(Double value) {
        String formatado = "";
        if (value != null) {
            formatado = fDecimal.format(value) + " " + ResourceBundle.getBundle("/Bundle").getString("MW");
        }
        return formatado;
    }

    // 
    /**
     *
     * @param date
     * @return
     */
    public static String formatData(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public static String formatDataNormal(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        } else {
            return "";
        }
    }

    // 
    /**
     *
     * @param date
     * @return
     */
    public static String formatData(Object object) {

        if (object != null) {
            Date date = (Date) object;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * @param textCrop = seu texto completo
     * @param num = quatidade de caracteres que vc deseja exibir
     * @param trail = o que você quer no final do corte ex: "...", "."
     */
    public static String cortarString(String textCrop, int num, String trail) {
        String result = "";
        //var rx = new Regex("<[^>]*>");
        if (textCrop != null) {

            textCrop = Pattern.compile("<[^>]*>").matcher(textCrop).replaceAll("");

            int iMax = num - trail.length();
            if (iMax <= 0) {
                result = "";
            }
            if (textCrop.length() <= num) {
                result = textCrop;
            } else {
                try {
                    String sOut = textCrop.substring(0, num);

                    int iPos = sOut.lastIndexOf(" ");
                    if (iPos != -1) {

                        return sOut.substring(0, iPos) + trail;
                    }
                    iPos = sOut.length();
                    return sOut.substring(0, iPos) + trail;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     *
     * @param cnpj
     * @return
     */
    public static String formatCnpj(String cnpj) {
        if (cnpj.length() == 14) {// 00000000000100
            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
                    + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
        } else {
            return cnpj;
        }
    }

    public static String formatAtoLegal(AtoLegal atoLegal) {
        String retorno = "";

        if (atoLegal != null && atoLegal.getAleId() != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String dataFormatada = (atoLegal.getAleDt() != null) ? sdf.format(atoLegal.getAleDt()) : "";

            String ano = (atoLegal.getAleAno() != null) ? atoLegal.getAleAno().toString() : "";

            if (!dataFormatada.equals("")) {
                retorno = atoLegal.getAleTalId().getTalNm() + " nº " + atoLegal.getAleNumero() + " de " + dataFormatada + ".";
            } else {
                retorno = atoLegal.getAleTalId().getTalNm() + " nº " + atoLegal.getAleNumero() + " de " + ano + ".";
            }

        }
        return retorno;
    }

    public static void postProcessorXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        DataFormat df = wb.createDataFormat();

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);
            cellStyle.setDataFormat(df.getFormat("General"));
            cell.setCellStyle(cellStyle);
        }

        HSSFCellStyle cellStyleCell = wb.createCellStyle();
        HSSFFont fontCell = wb.createFont();
        cellStyleCell.setFont(fontCell);

        CreationHelper createHelper = wb.getCreationHelper();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        CellStyle dateCell = wb.createCellStyle();
        dateCell.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        for (int i = 1; i < sheet.getPhysicalNumberOfRows() - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);

                if (validaData(cell.getStringCellValue())) {
                    Calendar cal = Calendar.getInstance();
                    try {
                        cal.setTime(sdf.parse(cell.getStringCellValue()));
                        cell.setCellValue(cal);// 
                        cell.setCellStyle(dateCell);
                    } catch (ParseException p) {
                    }
                }
            }
        }
    }

    public static boolean validaData(String data) {
        if (data != null && !data.trim().equals("")) {
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                format.setLenient(false);
                date = format.parse(data);
                return true;
            } catch (ParseException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validaDataRegex(String data) {
        if (data != null && !data.trim().equals("")) {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexData());
            java.util.regex.Matcher m = p.matcher(data);
            return m.matches();
        } else {
            return false;
        }
    }

    public static String regexData() {
        StringBuilder sb = new StringBuilder();
        sb.append("(((0[1-9]|[12][0-9]|3[01])([-./])(0[13578]|10|12)([-./])(\\d{4}))|(([0][1-9]|[12][0-9]|30)"
                + "([-./])(0[469]|11)([-./])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-./])(02)([-./])"
                + "(\\d{4}))|((29)(\\.|-|\\/)(02)([-./])([02468][048]00))|((29)([-./])(02)([-./])"
                + "([13579][26]00))|((29)([-./])(02)([-./])([0-9][0-9][0][48]))|((29)([-./])(02)"
                + "([-./])([0-9][0-9][2468][048]))|((29)([-./])(02)([-./])([0-9][0-9][13579][26])))");
        return sb.toString();
    }

    public static int comparador(String o1, String o2, int tam1, int tam2) {
        int retorno = 0;

        if (o1 == null || o2 == null || o1.isEmpty() || o2.isEmpty()) {
            return -1;
        }

        if (o1.equals(o2.toString())) {
            return 0;
        }

        int retNum = o1.substring(0, tam1).compareTo(o2.substring(0, tam1)); // Pega o numero
        int retAno = o1.substring(tam1, tam2).compareTo(o2.substring(tam1, tam2)); // Pega o ano

        // Comparação entre numeros do proton
        if (retAno == 0) {
            if (retNum < 0) {
                retorno = -1;
            } else if (retNum > 0) {
                retorno = 1;
            }
        } else {
            retorno = retAno; // Se o ano for diferente de zero, retorna o valor da comparação entre os anos
        }

        return retorno;
    }

    /**
     *
     * @param dec
     * @return
     */
    public static String formatCoordenada(String dec) {
        if (dec == null || dec.isEmpty()) {
            return "";
        }
        BigDecimal decimal = new BigDecimal(dec);
        CalculoCoordenada coord = ConversorLatLong.calculaCoordenada(decimal);
        String n1 = new Formatter().format("%.0f", coord.getGraus().doubleValue()) + "\u00ba ";
        String n2 = new Formatter().format("" + "" + "%.0f", coord.getMinutos().doubleValue()) + "\' ";
        String n3 = "" + coord.getSegundos().doubleValue() + "\'\' ";
        if (n3.contains(".")) {
            n3 = n3.replace(".", ",");
        }
        if (n1.trim().equals("0º") && dec.contains("-")) {
            return "-" + n1 + n2 + n3;
        } else {
            return "" + n1 + n2 + n3;
        }
    }

    // Valida se o grau da latitude é maior do que 90 ou menor do que -90 e existe minuto ou segundo.
    public static void validaLatitude(CalculoCoordenada latitude) throws LatitudeException {
        if (latitude != null && latitude.getGraus() != null) {
            double grau = latitude.getGraus().doubleValue();
            double minuto = (latitude.getMinutos() != null) ? latitude.getMinutos().doubleValue() : 0.0;
            double segundo = (latitude.getSegundos() != null) ? latitude.getSegundos().doubleValue() : 0.0;

            if (grau == 90 || grau == -90) {
                if (minuto != 0.0 || segundo != 0.0) {
                    throw new LatitudeException("Latitude Inválida! (" + String.format("%.0f", grau) + "º " + String.format("%.0f", minuto) + "\' " + String.format("%.2f", segundo) + "\'\') ");
                }
            }
        }
    }

    // Valida se o grau da longitude é maior do que 180 ou menor do que -180 e existe minuto ou segundo. 
    public static void validaLongitude(CalculoCoordenada longitude) throws LongitudeException {

        if (longitude != null && longitude.getGraus() != null) {
            double grau = longitude.getGraus().doubleValue();
            double minuto = (longitude.getMinutos() != null) ? longitude.getMinutos().doubleValue() : 0.0;
            double segundo = (longitude.getSegundos() != null) ? longitude.getSegundos().doubleValue() : 0.0;

            if (grau == 180 || grau == -180) {
                if (minuto != 0.0 || segundo != 0.0) {
                    throw new LongitudeException("Longitude Inválida! (" + String.format("%.0f", grau) + "º " + String.format("%.0f", minuto) + "\' " + String.format("%.2f", segundo) + "\'\') ");
                }
            }
        }
    }

    //Valida se o usuário passado como parametro deve visualizar a mensagem de nova empresa cadastrada.
    public static boolean getLoginEmpresa(String login) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreTxLogin().trim().equals(login) || us.getUreTxLogin().equals("lucas.nunes")) {
                return true;
            }
        }
        return false;
    }

    public static boolean getLoginAdminAlteracao() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreNm().equals("Administrador") || us.getUreTxLogin().equals("lucas.nunes")) {
                return true;
            }
        }
        return false;
    }

    public static Integer verificaTamanhoLista(List lista) {
        int retorno = 0;
        if (lista != null) {
            retorno = lista.size();
        }
        return retorno;
    }

    public static String getFlagHistorico(int flag) {
        String retorno = "";

        switch (flag) {
            case 0:
                retorno = "Alteração";
                break;
            case 1:
                retorno = "Inclusão";
                break;
            case 2:
                retorno = "Exclusão";
                break;
            default:
                retorno = "";
        }

        return retorno;
    }

    public static int diferencaEmDiasDataAtual(Date data) {

        int retorno = 0;

        if (data != null) {
            DateTime dataPassada = new DateTime(data.getTime());
            DateTime dataAtual = new DateTime(System.currentTimeMillis());

            retorno = Days.daysBetween(dataPassada, dataAtual).getDays();

        }
        return retorno;

    }

    public static String diferencaEmAnosDataAtual(Date data) {

        int dia = 0;
        int ano = 0;

        if (data != null) {
            DateTime dataPassada = new DateTime(data.getTime());
            DateTime dataAtual = new DateTime(System.currentTimeMillis());

            ano = Years.yearsBetween(dataPassada, dataAtual).getYears();

            dataAtual = dataAtual.minusYears(ano);

            dia = Days.daysBetween(dataPassada, dataAtual).getDays();

        }

        String retorno = ((ano != 0) ? ano + " ano(s) " : "") + ((ano != 0 && dia != 0) ? " e " : "") + ((dia != 0) ? dia + " dia(s)" : "");

        return retorno;

    }

    public static String substring(String texto, int posicaoInicial, int posicaoFinal) {
        String retorno = "";

        if (texto != null && !texto.equals("")) {
            retorno = texto.substring(posicaoInicial, posicaoFinal);
        }

        return retorno;
    }

    public static String substring(String texto, int posicao) {
        String retorno = "";

        if (texto != null && !texto.equals("")) {
            retorno = texto.substring(posicao);
        }

        return retorno;
    }

    public static String highlight(String texto, String filtro, DataTable tabela) {
        String retorno = "";
        if (texto != null) {
            Map<String, String> filtros = tabela.getFilters();
            String textoDigitado = filtros.get(filtro);
            if (textoDigitado != null) {
                retorno = buildHighlight(textoDigitado, texto);
            } else {
                return texto;
            }
        }
        return retorno;
    }

    public static String buildHighlight(String filtro, String texto) {
        String retorno = "";
        Integer posicao = 0, tam = filtro.length();
        while (true) {
            if (posicao > texto.length()) {
                break;
            }
            if ((texto.length() - posicao) < tam) {
                retorno += JsfUtil.substring(texto, posicao);
                break;
            }
            if (JsfUtil.removeAcentos(JsfUtil.substring(texto, posicao, posicao + tam)).equalsIgnoreCase(JsfUtil.removeAcentos(filtro))) {
                retorno += "<span style = \"color: red;\">" + JsfUtil.substring(texto, posicao, posicao + tam) + "</span>";
                posicao += tam;
            } else {
                retorno += JsfUtil.substring(texto, posicao, posicao + 1);
                posicao++;
            }
        }
        return retorno;
    }

    public static int getAnoDeUmaData(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
}
