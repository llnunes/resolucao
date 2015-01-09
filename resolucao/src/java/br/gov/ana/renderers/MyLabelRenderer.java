/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.renderers;

import com.sun.faces.renderkit.html_basic.LabelRenderer;
import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author lucas.nunes
 */
public class MyLabelRenderer extends LabelRenderer {

    /**
     * Esta metodo altera todos os componentes texto de umma pagina que tenham
     * um atributo 'for' que aponta para um outro componente que tenha um
     * atributo required 'true'. Colocando um asterisco vermelho na frente do
     * label.
     *
     * @param context Contexto JSF.
     * @param component Componente.
     * @throws IOException Caso nao consiga escrever na pagina.
     */
    @Override
    public final void encodeBegin(final FacesContext context,
            final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();

        final Map<String, Object> attrs = component.getAttributes();
        final String forAttr = (String) attrs.get("for");

        if (forAttr != null) {
            final UIComponent forComponent = component.findComponent(forAttr);
            final Map<String, Object> forAttrs = forComponent.getAttributes();
            final Boolean required = (Boolean) forAttrs.get("required");
            if (required != null && required) {
                writer.startElement("span", null);
                writer.writeAttribute("id",
                        component.getClientId(context) + "RequiredLabel", null);
                writer.writeAttribute("style", "color:red", null);
                writer.writeAttribute("styleClass", "requiredLabel", null);
                writer.writeText("*", null);
                writer.endElement("span");
            } /*else {
             attrs.remove("span");
                
             }*/

        }

        super.encodeBegin(context, component);
    }
}