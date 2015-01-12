/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.datamodels;

import br.gov.ana.entities.AtoLegal;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lucas.nunes
 */
public class AtoLegalDataModel extends ListDataModel<AtoLegal> implements SelectableDataModel<AtoLegal> {

    public AtoLegalDataModel() {
    }

    public AtoLegalDataModel(List<AtoLegal> list) {
        super(list);
    }

    @Override
    public Object getRowKey(AtoLegal atoLegal) {
        return atoLegal.getAleId();
    }

    @Override
    public AtoLegal getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<AtoLegal> atos = (List<AtoLegal>) getWrappedData();

        for (AtoLegal ato : atos) {
            if (ato.getAleId().equals(new BigDecimal(rowKey))) {
                return ato;
            }
        }

        return null;
    }
}
