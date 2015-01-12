/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.logic.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
/**
 *  https://mail.ana.gov.br/ews/Services.wsdl
 * @author lucas.nunes
 */
public class SendMail {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String message;
    private SimpleAuth auth = null;

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    /*
     * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL e
     * a porta usada por ele
     */
    /**
     *
     */
    public SendMail() { //Para o GMAIL 
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "465";
        
        //Cria um autenticador que sera usado a seguir
        auth = new SimpleAuth("", "");
    }
    /*
     * caso queira mudar o servidor e a porta, so enviar para o contrutor os
     * valor como string
     */

    /**
     *
     * @param mailSMTPServer
     * @param mailSMTPServerPort
     */
    public SendMail(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }

    /**
     *
     * @param nomeEmpresa
     * @param nomeTecnico
     * @param login
     * @param senha
     */
    public void setMsg(String nomeEmpresa, String nomeTecnico, String login, String senha) {
        this.message = "\n\nPrezado Técnico (a) Responsável pelas Ações no Âmbito da Resolução Conjunta ANA ANEEL nº 3/2010,\n"
                + "\nInformamos que atualizamos o documento: Procedimentos para Envio de Dados Hidrológicos em Tempo Real das Estações Telemétricas - Versão Mar/2012 (http://arquivos.ana.gov.br/infohidrologicas/cadastro/ProcedimentosParaEnvioDosDadosHidrologicosEmTempoRealDasEstacoesTelemetricas_RevisaoMar12.pdf ) tendo em vista que iniciaremos o recebimento dos dados hidrológicos em tempo real em ambiente de teste.\n"
                + "\nPara conexão ao ambiente de teste, estamos encaminhando neste e-mail, os dados para acesso ao sistema com o login (CNPJ da empresa) e a senha.\n\n\n"
                + "\nDADOS DA EMPRESA:\n\n"
                + "Nome: "+ nomeEmpresa +"\n"
                + "Técnico: "+ nomeTecnico +"\n"
                + "Login: "+ login +"\n"
                + "Senha: "+ senha +"\n"
                + "\n\nAdicionalmente destacamos que informações sobre a implantação da Resolução Conjunta ANA ANEEL podem ser obtidas no link http://www2.ana.gov.br/Paginas/servicos/informacoeshidrologicas/monitoramentohidro.aspx\n\n"
                + "Qualquer dificuldade nos contacte.\n"
                + "Estamos a disposição.\n\n\n"
                + "Atenciosamente,\n"
                + "Leny Simone Tavares Mendonça\n"
                + "Assessoria SGH\n"
                + "Superintendência de Gestão da Rede Hidrometeorológica - SGH\n\n";       
    }

    /**
     *
     * @param from
     * @param to
     * @param subject
     * @throws Exception
     */
    public void sendMail(String from, String to, String subject) throws Exception{

        Properties props = new Properties();

        // quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado
                /*
         * props.setProperty("proxySet","true");
         * props.setProperty("socksProxyHost","192.168.155.1"); // IP do
         * Servidor Proxy props.setProperty("socksProxyPort","1080"); // Porta
         * do servidor Proxy
         */

        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Session - objeto que ira realizar a conexão com o servidor
		/*
         * Como há necessidade de autenticação é criada uma autenticacao que é
         * responsavel por solicitar e retornar o usuário e senha para
         * autenticação
         */
        Session session = Session.getInstance(props, auth);
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email

        //Objeto que contém a mensagem
        Message msg = new MimeMessage(session);

        try {
            //Setando o destinatário
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setRecipient(Message.RecipientType.CC, new InternetAddress(from));
            //Setando a origem do email
            msg.setFrom(new InternetAddress(from));
            
            //Setando o assunto
            msg.setSubject(subject);
            //Setando o conteúdo/corpo do email
//			message.setContent(message,"text/plain");
            msg.setContent(getMessage(), "text/plain");


        } catch (Exception e) {
            //System.out.println(">> Erro: Completar Mensagem");
            //e.printStackTrace();
            throw new Exception(e);
        }

        //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte
			/*
             * 1 - define o servidor smtp 2 - seu nome de usuario do gmail 3 -
             * sua senha do gmail
             */
            tr.connect(mailSMTPServer, "", "");
            msg.saveChanges(); // don't forget this
            //envio da mensagem            
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            session = null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //System.out.println(">> Erro: Envio Mensagem");
            //e.printStackTrace();
            throw new Exception(e);
        }

    }
}
class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
