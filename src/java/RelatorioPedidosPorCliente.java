
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import models.Cliente;
import models.Pedido;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named(value = "relatorio_pedidos")
@RequestScoped
public class RelatorioPedidosPorCliente implements Serializable {

    private BarChartModel barModel;
    @EJB
    private models.ClienteFacade clienteFacade;
    @EJB
    private models.PedidoFacade pedidoFacade;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        model.setAnimate(true);
        
        ChartSeries chart = new ChartSeries();
        
        List<Cliente> clientes = clienteFacade.findAll();
        List<Pedido> pedidos = pedidoFacade.findAll();
        
        chart.setLabel("Clientes");
        
        for (Cliente cliente : clientes) {
            int qtdPedidos = 0;
            for (Pedido pedido : pedidos) {
                if (pedido.getCliente().getId() == cliente.getId()) {
                    qtdPedidos++;
                }
            }
            chart.set(cliente.toString(), qtdPedidos);
        }
        


        model.addSeries(chart);

        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Estatística de Pedidos por Cliente");
        //barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Clientes");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade de Pedidos");
        yAxis.setMin(0);
        yAxis.setMax(pedidoFacade.count());
    }
}
