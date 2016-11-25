import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import models.ItemPedido;
import models.Produto;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "relatorio")
@RequestScoped
public class RelatorioVendas implements Serializable {

    private PieChartModel pieModel1;
    @EJB
    private models.ItemPedidoFacade itensPedidoFacade;
    @EJB
    private models.ProdutoFacade produtoFacade;
    
    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }


    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
       
        List<Produto> produtos = produtoFacade.findAll();
        List<ItemPedido> itensPedido = itensPedidoFacade.findAll();
        
        HashMap<String, Integer> produtosQuantidadeVendida = new HashMap<>();
        for (Produto produto : produtos) {
            int count = 0;
            for (ItemPedido itemPedido : itensPedido) {
                if (itemPedido.getProduto().getId() == produto.getId()) {
                    count++;
                }
            }
            produtosQuantidadeVendida.put(produto.getDescricao(), count);
        }
        
        for (Map.Entry<String, Integer> item : produtosQuantidadeVendida.entrySet()) {
            pieModel1.set(item.getKey(), item.getValue());
        }
        pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("Estatística de Vendas por Produto");
        pieModel1.setLegendPosition("w");
            
    }
}
